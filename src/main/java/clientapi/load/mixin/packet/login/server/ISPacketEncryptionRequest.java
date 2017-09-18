/*
 * Copyright 2017 ImpactDevelopment
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

package clientapi.load.mixin.packet.login.server;

import net.minecraft.network.login.server.SPacketEncryptionRequest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.security.PublicKey;

/**
 * @author Brady
 * @since 8/24/2017 7:39 AM
 */
@Mixin(SPacketEncryptionRequest.class)
public interface ISPacketEncryptionRequest {

    @Accessor String getHashedServerId();

    @Accessor void setHashedServerId(String hashedServerId);

    @Accessor PublicKey getPublicKey();

    @Accessor void setPublicKey(PublicKey publicKey);

    @Accessor byte[] getVerifyToken();

    @Accessor void setVerifyToken(byte[] verifyToken);
}
