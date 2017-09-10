/*
 * Copyright 2017 ZeroMemes
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

package clientapi.load.mixin.packet.play.server;

import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 9/10/2017 12:35 PM
 */
@Mixin(SPacketOpenWindow.class)
public interface ISPacketOpenWindow {

    @Accessor int getWindowId();

    @Accessor void setWindowId(int windowId);

    @Accessor String getInventoryType();

    @Accessor void setInventoryType(String inventoryType);

    @Accessor ITextComponent getWindowTitle();

    @Accessor void setWindowTitle(ITextComponent windowTitle);

    @Accessor int getSlotCount();

    @Accessor void setSlotCount(int slotCount);

    @Accessor int getEntityId();

    @Accessor void setEntityId();
}
