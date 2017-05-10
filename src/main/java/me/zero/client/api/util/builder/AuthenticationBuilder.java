package me.zero.client.api.util.builder;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.util.Session;

import java.net.Proxy;

/**
 * Used as a means for logging in to an
 * account with ease.
 *
 * @author Brady
 * @since 2/10/2017 12:00 PM
 */
public final class AuthenticationBuilder implements Helper {

    /**
     * Authentication object
     */
    private final YggdrasilUserAuthentication auth;

    private AuthenticationBuilder() {
        this.auth = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "")
                .createUserAuthentication(Agent.MINECRAFT);
    }

    /**
     * Creates an authentication factory
     *
     * @return The created factory
     */
    public static AuthenticationBuilder builder() {
        return new AuthenticationBuilder();
    }

    /**
     * Sets the Username for Authentication
     *
     * @param username The username
     * @return The Factory
     */
    public final AuthenticationBuilder username(String username) {
        auth.setUsername(username);
        return this;
    }

    /**
     * Sets the Password for Authentication
     *
     * @param password The username
     * @return The Factory
     */
    public final AuthenticationBuilder password(String password) {
        auth.setPassword(password);
        return this;
    }

    /**
     * Creates a Session from the Factory
     *
     * @return The session
     */
    public final Session session() {
        try {
            auth.logIn();
            GameProfile profile = auth.getSelectedProfile();
            return new Session(profile.getName(), profile.getId().toString(), auth.getAuthenticatedToken(), "MOJANG");
        } catch (AuthenticationException e) {
            return null;
        }
    }

    /**
     * Logs in using the credentials provided
     *
     * @return Whether or not the login was successful
     */
    public final boolean login() {
        Session session = session();
        if (session == null)
            return false;

        mca.setSession(session);
        return true;
    }
}
