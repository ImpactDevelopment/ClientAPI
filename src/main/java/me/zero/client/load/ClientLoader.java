package me.zero.client.load;

import com.google.gson.GsonBuilder;
import javassist.bytecode.Descriptor;
import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;
import me.zero.client.api.exception.ActionNotValidException;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.util.ClientUtils;
import me.zero.client.load.inject.transformer.ITransformer;
import me.zero.client.load.inject.transformer.LoadTransformer;
import me.zero.client.load.inject.transformer.Transformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;;

/**
 * Used to get Client instances from Files
 *
 * @since 1.0
 *
 * Created by Brady on 1/24/2017.
 */
public final class ClientLoader {

    /**
     * The file of the Client Jar
     */
    private File file;

    /**
     * Jar File representing the Client Jar
     */
    private JarFile jarFile;

    /**
     * ClientInfo for Client
     */
    private ClientInfo info;

    /**
     * Instance of the ClientLoader for the Game ClassLoader
     */
    private static ClientLoader loader;

    ClientLoader(File file) throws IOException {
        this.file = file;
        this.jarFile = new JarFile(file);
    }

    /**
     * Creates the Game ClientLoader
     */
    public static void getGameLoader(String[] args) {
        if (loader != null)
            throw new ActionNotValidException("Game Loader has already been created");

        try {
            loader = new ClientLoader(new File(ClientUtils.getClientPath(args)));
        } catch (IOException e) {
            throw new UnexpectedOutcomeException("ClientLoader unable to Create. Suspending rocess.");
        }
    }

    /**
     * Loads the Game ClientLoader
     */
    public static void initGameLoader() {
        if (loader == null)
            throw new ActionNotValidException("Unable to load client if the loader is null");

        Client client = loader.getClient();
        if (client == null)
            throw new UnexpectedOutcomeException("Client found by Game ClientLoader is null!");

        client.onInit(loader);
        client.postInit();
    }

    /**
     * Loads the Client from the file
     * and then returns it.
     *
     * @since 1.0
     *
     * @return Client found from File
     */
    private Client getClient() {
        ClientInfo info = getClientInfo();

        if (info == null)
            return null;

        this.info = info;

        try {
            ClassLoader classLoader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL() });

            Class<?> clientClass = classLoader.loadClass(info.getMain());
            Client client = (Client) clientClass.newInstance();
            client.setInfo(info);

            return client;
        } catch (ClassNotFoundException | MalformedURLException | InstantiationException | IllegalAccessException e) {
            // Shouldn't happen
        }

        return null;
    }

    /**
     * Grabs the "client.json" from the client file
     *
     * @since 1.0
     *
     * @return Client info from file
     */
    private ClientInfo getClientInfo() {
        try {
            JarEntry entry = jarFile.getJarEntry("client.json");

            if (entry == null)
                return null;

            BufferedReader reader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(entry)));

            return new GsonBuilder().setPrettyPrinting().create().fromJson(reader, ClientInfo.class);
        } catch (IOException e) {
            // Client can't load b/c no JSON
        }
        return null;
    }

    /**
     * @return Client Info discovered by this Client Loader
     */
    public ClientInfo getDiscoveredInfo() {
        return this.info;
    }

    /**
     * Returns a copy of all of the Transformers
     *
     * @since 1.0
     *
     * @return The list of transformers
     */
    public List<ITransformer> getTransformers() {
        List<ITransformer> transformers = new ArrayList<>();
        try {
            ClassLoader classLoader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL() });
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry e = entries.nextElement();
                String name = e.getName();
                if (name.endsWith(".class")) {
                    try {
                        Class clazz = Class.forName(Descriptor.toJavaName(name.substring(0, name.length() - 6)), true, classLoader);
                        if (clazz != null && !clazz.isInterface() && clazz.getSuperclass().equals(Transformer.class) && clazz.isAnnotationPresent(LoadTransformer.class)) {
                            transformers.add((ITransformer) clazz.newInstance());
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {

                    }
                }
            }
        } catch (MalformedURLException e) {

        }
        return transformers;
    }
}
