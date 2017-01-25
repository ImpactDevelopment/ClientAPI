package me.zero.client.load;

import com.google.gson.GsonBuilder;
import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;
import me.zero.client.api.exception.ActionNotValidException;
import me.zero.client.api.transformer.ITransformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static me.zero.client.load.ClientAPI.Stage.PRE;

/**
 * Used to get Client instances from Files
 *
 * @since 1.0
 *
 * Created by Brady on 1/24/2017.
 */
public class ClientLoader {

    /**
     * The file of the Client Jar
     */
    private File file;

    /**
     * Jar File representing the Client Jar
     */
    private JarFile jarFile;

    /**
     * Client itself
     */
    private Client client;

    /**
     * ClientInfo for Client
     */
    private ClientInfo info;

    /**
     * The list of Transformers loaded by the Client
     */
    private List<ITransformer> transformers = new ArrayList<>();

    ClientLoader(File file) throws IOException {
        this.file = file;
        this.jarFile = new JarFile(file);
        this.client = getClient();
    }

    /**
     * Loads the Client
     *
     * @since 1.0
     */
    void loadClient() {
        if (client == null)
            throw new ActionNotValidException("A Client cannot be loaded if it is Null");

        client.preInit();
        client.onInit();
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
            ClassLoader classLoader = URLClassLoader.newInstance(new URL[] { file.toURL() });

            Client client = (Client) classLoader.loadClass(info.getMain()).newInstance();
            client.setInfo(info);

            return client;
        } catch (ClassNotFoundException | MalformedURLException | InstantiationException | IllegalAccessException e) {
            // Shouldn't happen
        }

        return null;
    }

    /**
     * Grabs the client.json from the client file
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
     * @return Client discovered by this Client Loader
     */
    Client getDiscoveredClient() {
        return this.client;
    }

    /**
     * @return Client Info discovered by this Client Loader
     */
    public ClientInfo getDiscoveredInfo() {
        return this.info;
    }

    /**
     * Regsiters Multiple Transformers
     *
     * @param transformers Transformers being registered
     */
    public void registerTransformer(ITransformer... transformers) {
        for (ITransformer transformer : transformers) {
            this.registerTransformer(transformers);
        }
    }

    /**
     * Registers a Transformer
     *
     * @since 1.0
     *
     * @param transformer Transformer being registered
     */
    public void registerTransformer(ITransformer transformer) {
        ClientAPI.getAPI().check(PRE, "Transformer Registration creation after Pre-Initialization");

        if (!transformers.contains(transformer))
            transformers.add(transformer);
    }

    /**
     * Returns a copy of all of the Transformers
     *
     * @since 1.0
     *
     * @return The list of transformers
     */
    public List<ITransformer> getTransformers() {
        return new ArrayList<>(transformers);
    }
}
