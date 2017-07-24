/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.command;

import me.zero.client.api.command.exception.CommandInitException;
import me.zero.client.api.util.ClientAPIUtils;

/**
 * @author Brady
 * @since 5/31/2017 8:55 AM
 */
public abstract class Command implements ICommand {

    private String[] headers;
    private String description;
    private String[] syntax;

    public Command() {
        if (!this.getClass().isAnnotationPresent(Cmd.class))
            throw new RuntimeException(new CommandInitException(this, "@Cmd annotation must be present if required parameters aren't passed through constructor"));

        Cmd data = this.getClass().getAnnotation(Cmd.class);
        setup(data.headers(), data.description(), data.syntax());
    }

    public Command(String[] headers, String description, String[] syntax) {
        setup(headers, description, syntax);
    }

    private void setup(String[] headers, String description, String[] syntax) {
        this.headers = headers;
        this.description = description;
        this.syntax = syntax;

        if (ClientAPIUtils.containsNull(headers, description, syntax))
            throw new NullPointerException("One or more Command members were null!");
    }

    @Override
    public String[] headers() {
        return this.headers;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String[] syntax() {
        return this.syntax;
    }
}
