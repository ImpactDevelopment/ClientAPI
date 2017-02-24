package me.zero.client.load.transformer.wrapper.defaults;

import javassist.CtPrimitiveType;
import me.zero.client.api.wrapper.ICPacketPlayer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Used to create Setters for CPacketPlayer
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
 */
public class WCPacketPlayer extends ClassWrapper {

    public WCPacketPlayer() {
        super(CPacketPlayer, ICPacketPlayer.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementS("setX", CtPrimitiveType.doubleType, player_x);
        this.implementS("setY", CtPrimitiveType.doubleType, player_y);
        this.implementS("setZ", CtPrimitiveType.doubleType, player_z);
        this.implementS("setYaw", CtPrimitiveType.floatType, player_yaw);
        this.implementS("setPitch", CtPrimitiveType.floatType, player_pitch);
        this.implementS("setOnGround", CtPrimitiveType.booleanType, player_onground);
    }
}
