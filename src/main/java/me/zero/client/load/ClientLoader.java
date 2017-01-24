package me.zero.client.load;

import com.google.gson.GsonBuilder;
import com.sun.xml.internal.ws.addressing.model.ActionNotSupportedException;
import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Used to get Client instances from Files
 *
 * @since 1.0
 *
 * Created by Brady on 1/24/2017.
 */
class ClientLoader {

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
        // Do client loading stuff (preInit, onInit, postInit, etc.)
        if (client != null)
            throw new ActionNotSupportedException("A Client cannot be loaded if it is Null");
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

    Client getDiscoveredClient() {
        return this.client;
    }
}
