package me.zero.client.load;

import com.google.gson.GsonBuilder;
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
public class ClientLoader {

    private File file;
    private JarFile jarFile;
    private Client client;

    ClientLoader(File file) throws IOException {
        this.file = file;
        this.jarFile = new JarFile(file);
        this.client = getClient();
    }

    void loadClient() {
        // Do client loading stuff (preInit, onInit, postInit, etc.)
    }

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

    public Client getDiscoveredClient() {
        return this.client;
    }
}
