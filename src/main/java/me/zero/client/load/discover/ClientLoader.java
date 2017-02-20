package me.zero.client.load.discover;

import com.google.gson.GsonBuilder;
import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.handle.ClientHandler;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.util.Messages;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import me.zero.client.load.transformer.ITransformer;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.Transformer;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Used to load all elements of a Client
 *
 * @since 1.0
 *
 * Created by Brady on 2/15/2017.
 */
public class ClientLoader {

    /**
     * Retrieves the ClientInfo from the Client.json
     *
     * @since 1.0
     *
     * @return The ClientInfo
     */
    public static ClientInfo getInfo() {
        InputStream stream = ClientLoader.class.getResourceAsStream("/client.json");

        if (stream == null)
            return null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return new GsonBuilder().setPrettyPrinting().create().fromJson(reader, ClientInfo.class);
    }

    /**
     * Retrieves the list of Class Transformers from the
     * specified class in the Client.json
     *
     * @since 1.0
     *
     * @return The list of Transformers
     */
    public static List<ITransformer> getTransformers() {
        ClientInfo info = getInfo();
        if (info == null)
            throw new UnexpectedOutcomeException("Unable to get Transformers, ClientInfo not found!");

        List<ITransformer> transformers = new ArrayList<>();
        if (info.getTransformers().length() == 0)
            return transformers;

        new Reflections(info.getTransformers()).getSubTypesOf(Transformer.class).forEach(transformer -> {
            try {
                if (transformer.isAnnotationPresent(LoadTransformer.class))
                    transformers.add(transformer.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                Logger.instance.logf(Level.SEVERE, Messages.TRANSFORM_INSTANTIATION, transformer);
            }
        });
        return transformers;
    }

    /**
     * Instantiates a Client from the ClientInfo
     *
     * @since 1.0
     *
     * @param info Client Info
     * @return Client instance
     */
    private static Client getClient(ClientInfo info) {
        if (info == null)
            throw new UnexpectedOutcomeException("Unable to create Client, ClientInfo not found!");

        try {
            Class<?> clientClass = Class.forName(info.getMain());
            if (clientClass != null && clientClass.getSuperclass().equals(Client.class)) {
                try {
                    return (Client) clientClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    Logger.instance.logf(Level.SEVERE, Messages.CLIENT_INSTANTIATION, info.getMain());
                }
            }
        } catch (ClassNotFoundException e) {
            Logger.instance.logf(Level.SEVERE, Messages.CLIENT_NOT_FOUND, info.getMain());
        }
        return null;
    }

    /**
     * Runs the client, this method call is injected.
     *
     * @since 1.0
     */
    public static void runClient() {
        ClientInfo info = getInfo();
        Client client = getClient(info);
        if (client == null)
            throw new UnexpectedOutcomeException("Client loaded is null");

        client.onInit(info);
        EventManager.subscribe(new ClientHandler());
    }
}
