package me.zero.client.api.bot.handler;

import me.zero.client.api.bot.Bot;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Handles the Network Play State for Bots
 *
 * @since 1.0
 *
 * Created by Brady on 3/6/2017.
 */
public class BotPlay implements INetHandlerPlayClient {

    private final Bot bot;

    public BotPlay(Bot bot) {
        this.bot = bot;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnObject(SPacketSpawnObject packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnExperienceOrb(SPacketSpawnExperienceOrb packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnGlobalEntity(SPacketSpawnGlobalEntity packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnMob(SPacketSpawnMob packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleScoreboardObjective(SPacketScoreboardObjective packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnPainting(SPacketSpawnPainting packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnPlayer(SPacketSpawnPlayer packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleAnimation(SPacketAnimation packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleStatistics(SPacketStatistics packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleBlockBreakAnim(SPacketBlockBreakAnim packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSignEditorOpen(SPacketSignEditorOpen packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleUpdateTileEntity(SPacketUpdateTileEntity packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleBlockAction(SPacketBlockAction packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleBlockChange(SPacketBlockChange packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleChat(SPacketChat packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleTabComplete(SPacketTabComplete packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleMultiBlockChange(SPacketMultiBlockChange packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleMaps(SPacketMaps packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleConfirmTransaction(SPacketConfirmTransaction packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleCloseWindow(SPacketCloseWindow packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleWindowItems(SPacketWindowItems packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleOpenWindow(SPacketOpenWindow packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleWindowProperty(SPacketWindowProperty packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSetSlot(SPacketSetSlot packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleCustomPayload(SPacketCustomPayload packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleDisconnect(SPacketDisconnect packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleUseBed(SPacketUseBed packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityStatus(SPacketEntityStatus packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityAttach(SPacketEntityAttach packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSetPassengers(SPacketSetPassengers packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleExplosion(SPacketExplosion packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleChangeGameState(SPacketChangeGameState packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleKeepAlive(SPacketKeepAlive packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleChunkData(SPacketChunkData packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void processChunkUnload(SPacketUnloadChunk packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEffect(SPacketEffect packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleJoinGame(SPacketJoinGame packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityMovement(SPacketEntity packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handlePlayerPosLook(SPacketPlayerPosLook packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleParticles(SPacketParticles packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handlePlayerAbilities(SPacketPlayerAbilities packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handlePlayerListItem(SPacketPlayerListItem packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleDestroyEntities(SPacketDestroyEntities packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleRemoveEntityEffect(SPacketRemoveEntityEffect packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleRespawn(SPacketRespawn packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityHeadLook(SPacketEntityHeadLook packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleHeldItemChange(SPacketHeldItemChange packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleDisplayObjective(SPacketDisplayObjective packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityMetadata(SPacketEntityMetadata packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityVelocity(SPacketEntityVelocity packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityEquipment(SPacketEntityEquipment packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSetExperience(SPacketSetExperience packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleUpdateHealth(SPacketUpdateHealth packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleTeams(SPacketTeams packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleUpdateScore(SPacketUpdateScore packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSpawnPosition(SPacketSpawnPosition packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleTimeUpdate(SPacketTimeUpdate packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleSoundEffect(SPacketSoundEffect packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleCustomSound(SPacketCustomSound packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleCollectItem(SPacketCollectItem packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityTeleport(SPacketEntityTeleport packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityProperties(SPacketEntityProperties packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleEntityEffect(SPacketEntityEffect packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleCombatEvent(SPacketCombatEvent packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleServerDifficulty(SPacketServerDifficulty packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleCamera(SPacketCamera packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleWorldBorder(SPacketWorldBorder packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleTitle(SPacketTitle packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handlePlayerListHeaderFooter(SPacketPlayerListHeaderFooter packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleResourcePack(SPacketResourcePackSend packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleUpdateBossInfo(SPacketUpdateBossInfo packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleCooldown(SPacketCooldown packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void handleMoveVehicle(SPacketMoveVehicle packetIn) {

    }

    @Override
    @ParametersAreNonnullByDefault
    public void onDisconnect(ITextComponent reason) {

    }
}
