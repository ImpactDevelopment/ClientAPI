package me.zero.client.load.transformer.wrapper.defaults;

import me.zero.client.wrapper.ICPacketPlayer;
import me.zero.client.load.transformer.LoadTransformer;
import me.zero.client.load.transformer.wrapper.ClassWrapper;

import static javassist.CtClass.*;
import static me.zero.client.load.transformer.reference.obfuscation.MCMappings.*;

/**
 * Wraps ICPacketPlayer to CPacketPlayer
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
 */
@LoadTransformer
public class WCPacketPlayer extends ClassWrapper {

    public WCPacketPlayer() {
        super(CPacketPlayer, ICPacketPlayer.class);
    }

    @Override
    protected void loadImplementations() {
        this.implementS("setX", doubleType, player_x);
        this.implementS("setY", doubleType, player_y);
        this.implementS("setZ", doubleType, player_z);
        this.implementS("setYaw", floatType, player_yaw);
        this.implementS("setPitch", floatType, player_pitch);
        this.implementS("setOnGround", booleanType, player_onground);
    }
}
