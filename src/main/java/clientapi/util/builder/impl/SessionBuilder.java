/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.util.builder.impl;

import clientapi.util.builder.Builder;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.util.Session;

import java.net.Proxy;

/**
 * Builder used to create a {@link Session} object.
 *
 * @author Brady
 * @since 8/13/2017
 */
public final class SessionBuilder implements Builder<Session> {

    /**
     * Session username
     */
    private String username;

    /**
     * Session password
     */
    private String password;

    /**
     * Optional authentication proxy
     */
    private Proxy proxy = Proxy.NO_PROXY;

    /**
     * Sets the User Authentication username
     *
     * @param username The username of the account
     * @return This builder
     */
    public final SessionBuilder username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Sets the User Authentication password
     *
     * @param password The password of the account
     * @return This builder
     */
    public final SessionBuilder password(String password) {
        this.password = password;
        return this;
    }

    /**
     * Defines a proxy to authenticate with. Defaults to
     * {@link Proxy#NO_PROXY} if not specified.
     *
     * @param proxy The proxy to use on login
     * @return This builder
     */
    public final SessionBuilder proxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }

    /**
     * Attempts to login with the specified username and password.
     * If the login is successful then a session will be created. If
     * not, {@code null} will be returned
     *
     * @return A valid session, if able to login, otherwise {@code null}
     */
    @Override
    public Session build() {
        UserAuthentication auth = new YggdrasilAuthenticationService(this.proxy, "").createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(this.username);
        auth.setPassword(this.password);

        try {
            auth.logIn();
        } catch (AuthenticationException e) {
            return null;
        }

        GameProfile profile = auth.getSelectedProfile();
        return new Session(profile.getName(), profile.getId().toString(), auth.getAuthenticatedToken(), "MOJANG");
    }
}
