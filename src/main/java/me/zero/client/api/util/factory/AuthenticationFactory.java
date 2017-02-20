package me.zero.client.api.util.factory;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.util.Session;

import java.net.Proxy;

/**
 * Used as a means for logging in to an
 * account with ease.
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
public class AuthenticationFactory {

    /**
     * Authentication object
     */
    private YggdrasilUserAuthentication auth;

    private AuthenticationFactory() {
        this.auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "")
                .createUserAuthentication(Agent.MINECRAFT);
    }

    /**
     * Creates an authentication factory
     *
     * @since 1.0
     *
     * @return The created factory
     */
    public static AuthenticationFactory create() {
        return new AuthenticationFactory();
    }

    /**
     * Sets the Username for Authentication
     *
     * @since 1.0
     *
     * @param username The username
     * @return The Factory
     */
    public AuthenticationFactory username(String username) {
        auth.setUsername(username);
        return this;
    }

    /**
     * Sets the Password for Authentication
     *
     * @since 1.0
     *
     * @param password The username
     * @return The Factory
     */
    public AuthenticationFactory password(String password) {
        auth.setPassword(password);
        return this;
    }

    /**
     * Logs in using the credentials provided
     *
     * @since 1.0
     *
     * @return Whether or not the login was successful
     */
    public boolean login() {
        try {
            auth.logIn();
            GameProfile profile = auth.getSelectedProfile();
            Session session = new Session(profile.getName(), profile.getId().toString(), auth.getAuthenticatedToken(), "MOJANG");
            // Set session via reflection
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }
}
