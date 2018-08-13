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

package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.network.PacketEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NettyPacketEncoder;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 7/16/2017
 */
@Mixin(NettyPacketEncoder.class)
public class MixinNettyPacketEncoder {

    private PacketEvent event;

    @ModifyVariable(method = "encode", at = @At("HEAD"), index = 1, remap = false)
    private Packet<?> mutatePacket(Packet<?> msg) {
        event = new PacketEvent.Encode(msg);
        ClientAPI.EVENT_BUS.post(event);
        return event.getPacket();
    }

    @Inject(method = "encode", at = @At(value = "JUMP", ordinal = 0), cancellable = true)
    private void checkCancelled(ChannelHandlerContext ctx, Packet<?> msg, ByteBuf out, CallbackInfo ci) {
        if (event != null && (event.isCancelled() || event.getPacket() == null))
            ci.cancel();
        event = null;
    }
}
