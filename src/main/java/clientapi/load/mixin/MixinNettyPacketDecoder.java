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
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NettyPacketDecoder;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * @author Brady
 * @since 7/17/2017 2:22 PM
 */
@Mixin(NettyPacketDecoder.class)
public class MixinNettyPacketDecoder {

    private PacketEvent.Decode event;

    @Redirect(method = "decode", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/network/EnumConnectionState;getPacket(Lnet/minecraft/network/EnumPacketDirection;I)Lnet/minecraft/network/Packet;"))
    private Packet<?> mutatePacket(EnumConnectionState state, EnumPacketDirection direction, int id) throws IllegalAccessException, InstantiationException {
        event = new PacketEvent.Decode(state.getPacket(direction, id), state);
        ClientAPI.EVENT_BUS.post(event);
        return event.getPacket();
    }

    @Inject(method = "decode", at = @At(value = "JUMP", ordinal = 0))
    private void checkCancelled(ChannelHandlerContext ctx, ByteBuf in, List<Object> out, CallbackInfo ci) {
        if (event != null && event.isCancelled())
            ci.cancel();
        event = null;
    }
}
