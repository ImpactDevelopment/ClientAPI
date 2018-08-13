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

package clientapi.load;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ClientTweaker that leaves the argument list alone
 * so that optifine's tweaker does not cause any conflictions.
 *
 * @author Brady
 * @since 2/21/2018
 */
public final class ClientTweakerOptifine extends ClientTweaker {

    @Override
    public final void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.args = new ArrayList<>();
    }
}
