package me.zero.client.api.bot.handler;

import me.zero.client.api.bot.Bot;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.server.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameType;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Handles the Network Play State for Bots.
 * This class is essentially just NetHandlerPlayClient,
 * but only handles things that effect the Client Player.
 *
 * @since 1.0
 *
 * Created by Brady on 3/6/2017.
 */
public class BotPlay implements INetHandlerPlayClient, Helper {

    /**
     * Bot that represents this Play Network Handler
     */
    private final Bot bot;

    /**
     * Bot NetworkManager
     */
    private final NetworkManager netManager;

    public BotPlay(Bot bot) {
        this.bot = bot;
        this.netManager = bot.getNetworkManager();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnObject(SPacketSpawnObject packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnExperienceOrb(SPacketSpawnExperienceOrb packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnGlobalEntity(SPacketSpawnGlobalEntity packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnMob(SPacketSpawnMob packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleScoreboardObjective(SPacketScoreboardObjective packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnPainting(SPacketSpawnPainting packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnPlayer(SPacketSpawnPlayer packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleAnimation(SPacketAnimation packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleStatistics(SPacketStatistics packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleBlockBreakAnim(SPacketBlockBreakAnim packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSignEditorOpen(SPacketSignEditorOpen packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleUpdateTileEntity(SPacketUpdateTileEntity packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleBlockAction(SPacketBlockAction packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleBlockChange(SPacketBlockChange packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleChat(SPacketChat packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleTabComplete(SPacketTabComplete packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleMultiBlockChange(SPacketMultiBlockChange packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleMaps(SPacketMaps packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleConfirmTransaction(SPacketConfirmTransaction packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);
        Container container = null;
        EntityPlayer entityplayer = this.bot.getPlayer();

        if (packetIn.getWindowId() == 0) {
            container = entityplayer.inventoryContainer;
        } else if (packetIn.getWindowId() == entityplayer.openContainer.windowId) {
            container = entityplayer.openContainer;
        }

        if (container != null && !packetIn.wasAccepted()) {
            this.netManager.sendPacket(new CPacketConfirmTransaction(packetIn.getWindowId(), packetIn.getActionNumber(), true));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleCloseWindow(SPacketCloseWindow packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);
        this.bot.getPlayer().closeScreenAndDropStack();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleWindowItems(SPacketWindowItems packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);
        EntityPlayer entityplayer = this.bot.getPlayer();

        if (packetIn.getWindowId() == 0) {
            entityplayer.inventoryContainer.setAll(packetIn.getItemStacks());
        } else if (packetIn.getWindowId() == entityplayer.openContainer.windowId) {
            entityplayer.openContainer.setAll(packetIn.getItemStacks());
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleOpenWindow(SPacketOpenWindow packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleWindowProperty(SPacketWindowProperty packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSetSlot(SPacketSetSlot packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);

        EntityPlayer entityplayer = this.bot.getPlayer();
        ItemStack itemstack = packetIn.getStack();
        int i = packetIn.getSlot();

        switch (packetIn.getWindowId()) {
            case -1: {
                entityplayer.inventory.setItemStack(itemstack);
                break;
            }
            case -2: {
                entityplayer.inventory.setInventorySlotContents(i, itemstack);
                break;
            }
            default: {
                if (packetIn.getWindowId() == 0 && packetIn.getSlot() >= 36 && i < 45) {
                    if (!itemstack.isEmpty()) {
                        ItemStack is = entityplayer.inventoryContainer.getSlot(i).getStack();

                        if (is.isEmpty() || is.getCount() < itemstack.getCount()) {
                            itemstack.setAnimationsToGo(5);
                        }
                    }

                    entityplayer.inventoryContainer.putStackInSlot(i, itemstack);
                } else if (packetIn.getWindowId() == entityplayer.openContainer.windowId) {
                    entityplayer.openContainer.putStackInSlot(i, itemstack);
                }
                break;
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleCustomPayload(SPacketCustomPayload packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleDisconnect(SPacketDisconnect packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleUseBed(SPacketUseBed packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityStatus(SPacketEntityStatus packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityAttach(SPacketEntityAttach packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSetPassengers(SPacketSetPassengers packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleExplosion(SPacketExplosion packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleChangeGameState(SPacketChangeGameState packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);
        int i = packetIn.getGameState();
        float f = packetIn.getValue();
        int j = MathHelper.floor(f + 0.5F);

        if (i == 3) {
            this.bot.getPlayerController().setGameType(GameType.getByID(j));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleKeepAlive(SPacketKeepAlive packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleChunkData(SPacketChunkData packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void processChunkUnload(SPacketUnloadChunk packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEffect(SPacketEffect packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleJoinGame(SPacketJoinGame packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityMovement(SPacketEntity packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handlePlayerPosLook(SPacketPlayerPosLook packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);

        EntityPlayer player = this.bot.getPlayer();
        double d0 = packetIn.getX();
        double d1 = packetIn.getY();
        double d2 = packetIn.getZ();
        float f = packetIn.getYaw();
        float f1 = packetIn.getPitch();

        if (packetIn.getFlags().contains(SPacketPlayerPosLook.EnumFlags.X))
            d0 += player.posX;
        else
            player.motionX = 0.0D;

        if (packetIn.getFlags().contains(SPacketPlayerPosLook.EnumFlags.Y))
            d1 += player.posY;
        else
            player.motionY = 0.0D;

        if (packetIn.getFlags().contains(SPacketPlayerPosLook.EnumFlags.Z))
            d2 += player.posZ;
        else
            player.motionZ = 0.0D;

        if (packetIn.getFlags().contains(SPacketPlayerPosLook.EnumFlags.X_ROT))
            f1 += player.rotationPitch;

        if (packetIn.getFlags().contains(SPacketPlayerPosLook.EnumFlags.Y_ROT))
            f += player.rotationYaw;

        player.setPositionAndRotation(d0, d1, d2, f, f1);
        this.netManager.sendPacket(new CPacketConfirmTeleport(packetIn.getTeleportId()));
        this.netManager.sendPacket(new CPacketPlayer.PositionRotation(player.posX, player.getEntityBoundingBox().minY, player.posZ, player.rotationYaw, player.rotationPitch, false));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleParticles(SPacketParticles packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handlePlayerAbilities(SPacketPlayerAbilities packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);

        PlayerCapabilities capabilities = this.bot.getPlayer().capabilities;
        capabilities.isFlying = packetIn.isFlying();
        capabilities.isCreativeMode = packetIn.isCreativeMode();
        capabilities.disableDamage = packetIn.isInvulnerable();
        capabilities.allowFlying = packetIn.isAllowFlying();
        capabilities.setFlySpeed(packetIn.getFlySpeed());
        capabilities.setPlayerWalkSpeed(packetIn.getWalkSpeed());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handlePlayerListItem(SPacketPlayerListItem packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleDestroyEntities(SPacketDestroyEntities packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleRemoveEntityEffect(SPacketRemoveEntityEffect packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleRespawn(SPacketRespawn packetIn) {
        // TODO: Implement
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityHeadLook(SPacketEntityHeadLook packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleHeldItemChange(SPacketHeldItemChange packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);

        if (InventoryPlayer.isHotbar(packetIn.getHeldItemHotbarIndex())) {
            this.bot.getPlayer().inventory.currentItem = packetIn.getHeldItemHotbarIndex();
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleDisplayObjective(SPacketDisplayObjective packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityMetadata(SPacketEntityMetadata packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityVelocity(SPacketEntityVelocity packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityEquipment(SPacketEntityEquipment packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSetExperience(SPacketSetExperience packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);
        this.bot.getPlayer().setXPStats(packetIn.getExperienceBar(), packetIn.getTotalExperience(), packetIn.getLevel());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleUpdateHealth(SPacketUpdateHealth packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);
        this.bot.getPlayer().setPlayerSPHealth(packetIn.getHealth());
        this.bot.getPlayer().getFoodStats().setFoodLevel(packetIn.getFoodLevel());
        this.bot.getPlayer().getFoodStats().setFoodSaturationLevel(packetIn.getSaturationLevel());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleTeams(SPacketTeams packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleUpdateScore(SPacketUpdateScore packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnPosition(SPacketSpawnPosition packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);
        this.bot.getPlayer().setSpawnPoint(packetIn.getSpawnPos(), true);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleTimeUpdate(SPacketTimeUpdate packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleSoundEffect(SPacketSoundEffect packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleCustomSound(SPacketCustomSound packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleCollectItem(SPacketCollectItem packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityTeleport(SPacketEntityTeleport packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityProperties(SPacketEntityProperties packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityEffect(SPacketEntityEffect packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleCombatEvent(SPacketCombatEvent packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);

        if (packetIn.eventType == SPacketCombatEvent.Event.ENTITY_DIED) {
            Entity e = this.bot.getPlayer();
            if (e.getEntityId() == packetIn.playerId) {
                e.setDead();
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleServerDifficulty(SPacketServerDifficulty packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleCamera(SPacketCamera packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleWorldBorder(SPacketWorldBorder packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleTitle(SPacketTitle packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handlePlayerListHeaderFooter(SPacketPlayerListHeaderFooter packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleResourcePack(SPacketResourcePackSend packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleUpdateBossInfo(SPacketUpdateBossInfo packetIn) {}

    @Override
    @ParametersAreNonnullByDefault
    public void handleCooldown(SPacketCooldown packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);

        EntityPlayer player = this.bot.getPlayer();
        if (packetIn.getTicks() == 0) {
            player.getCooldownTracker().removeCooldown(packetIn.getItem());
        } else {
            player.getCooldownTracker().setCooldown(packetIn.getItem(), packetIn.getTicks());
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleMoveVehicle(SPacketMoveVehicle packetIn) {
        PacketThreadUtil.checkThreadAndEnqueue(packetIn, this, mc);
        Entity entity = this.bot.getPlayer().getLowestRidingEntity();

        if (entity != this.bot.getPlayer() && entity.canPassengerSteer()) {
            entity.setPositionAndRotation(packetIn.getX(), packetIn.getY(), packetIn.getZ(), packetIn.getYaw(), packetIn.getPitch());
            this.netManager.sendPacket(new CPacketVehicleMove(entity));
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onDisconnect(ITextComponent reason) {}
}
