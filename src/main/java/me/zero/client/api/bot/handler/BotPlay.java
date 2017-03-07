package me.zero.client.api.bot.handler;

import me.zero.client.api.bot.Bot;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.ITextComponent;

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
    public void handleSpawnObject(SPacketSpawnObject packetIn) {

    }

    @Override
    public void handleSpawnExperienceOrb(SPacketSpawnExperienceOrb packetIn) {

    }

    @Override
    public void handleSpawnGlobalEntity(SPacketSpawnGlobalEntity packetIn) {

    }

    @Override
    public void handleSpawnMob(SPacketSpawnMob packetIn) {

    }

    @Override
    public void handleScoreboardObjective(SPacketScoreboardObjective packetIn) {

    }

    @Override
    public void handleSpawnPainting(SPacketSpawnPainting packetIn) {

    }

    @Override
    public void handleSpawnPlayer(SPacketSpawnPlayer packetIn) {

    }

    @Override
    public void handleAnimation(SPacketAnimation packetIn) {

    }

    @Override
    public void handleStatistics(SPacketStatistics packetIn) {

    }

    @Override
    public void handleBlockBreakAnim(SPacketBlockBreakAnim packetIn) {

    }

    @Override
    public void handleSignEditorOpen(SPacketSignEditorOpen packetIn) {

    }

    @Override
    public void handleUpdateTileEntity(SPacketUpdateTileEntity packetIn) {

    }

    @Override
    public void handleBlockAction(SPacketBlockAction packetIn) {

    }

    @Override
    public void handleBlockChange(SPacketBlockChange packetIn) {

    }

    @Override
    public void handleChat(SPacketChat packetIn) {

    }

    @Override
    public void handleTabComplete(SPacketTabComplete packetIn) {

    }

    @Override
    public void handleMultiBlockChange(SPacketMultiBlockChange packetIn) {

    }

    @Override
    public void handleMaps(SPacketMaps packetIn) {

    }

    @Override
    public void handleConfirmTransaction(SPacketConfirmTransaction packetIn) {

    }

    @Override
    public void handleCloseWindow(SPacketCloseWindow packetIn) {

    }

    @Override
    public void handleWindowItems(SPacketWindowItems packetIn) {

    }

    @Override
    public void handleOpenWindow(SPacketOpenWindow packetIn) {

    }

    @Override
    public void handleWindowProperty(SPacketWindowProperty packetIn) {

    }

    @Override
    public void handleSetSlot(SPacketSetSlot packetIn) {

    }

    @Override
    public void handleCustomPayload(SPacketCustomPayload packetIn) {

    }

    @Override
    public void handleDisconnect(SPacketDisconnect packetIn) {

    }

    @Override
    public void handleUseBed(SPacketUseBed packetIn) {

    }

    @Override
    public void handleEntityStatus(SPacketEntityStatus packetIn) {

    }

    @Override
    public void handleEntityAttach(SPacketEntityAttach packetIn) {

    }

    @Override
    public void handleSetPassengers(SPacketSetPassengers packetIn) {

    }

    @Override
    public void handleExplosion(SPacketExplosion packetIn) {

    }

    @Override
    public void handleChangeGameState(SPacketChangeGameState packetIn) {

    }

    @Override
    public void handleKeepAlive(SPacketKeepAlive packetIn) {

    }

    @Override
    public void handleChunkData(SPacketChunkData packetIn) {

    }

    @Override
    public void processChunkUnload(SPacketUnloadChunk packetIn) {

    }

    @Override
    public void handleEffect(SPacketEffect packetIn) {

    }

    @Override
    public void handleJoinGame(SPacketJoinGame packetIn) {

    }

    @Override
    public void handleEntityMovement(SPacketEntity packetIn) {

    }

    @Override
    public void handlePlayerPosLook(SPacketPlayerPosLook packetIn) {

    }

    @Override
    public void handleParticles(SPacketParticles packetIn) {

    }

    @Override
    public void handlePlayerAbilities(SPacketPlayerAbilities packetIn) {

    }

    @Override
    public void handlePlayerListItem(SPacketPlayerListItem packetIn) {

    }

    @Override
    public void handleDestroyEntities(SPacketDestroyEntities packetIn) {

    }

    @Override
    public void handleRemoveEntityEffect(SPacketRemoveEntityEffect packetIn) {

    }

    @Override
    public void handleRespawn(SPacketRespawn packetIn) {

    }

    @Override
    public void handleEntityHeadLook(SPacketEntityHeadLook packetIn) {

    }

    @Override
    public void handleHeldItemChange(SPacketHeldItemChange packetIn) {

    }

    @Override
    public void handleDisplayObjective(SPacketDisplayObjective packetIn) {

    }

    @Override
    public void handleEntityMetadata(SPacketEntityMetadata packetIn) {

    }

    @Override
    public void handleEntityVelocity(SPacketEntityVelocity packetIn) {

    }

    @Override
    public void handleEntityEquipment(SPacketEntityEquipment packetIn) {

    }

    @Override
    public void handleSetExperience(SPacketSetExperience packetIn) {

    }

    @Override
    public void handleUpdateHealth(SPacketUpdateHealth packetIn) {

    }

    @Override
    public void handleTeams(SPacketTeams packetIn) {

    }

    @Override
    public void handleUpdateScore(SPacketUpdateScore packetIn) {

    }

    @Override
    public void handleSpawnPosition(SPacketSpawnPosition packetIn) {

    }

    @Override
    public void handleTimeUpdate(SPacketTimeUpdate packetIn) {

    }

    @Override
    public void handleSoundEffect(SPacketSoundEffect packetIn) {

    }

    @Override
    public void handleCustomSound(SPacketCustomSound packetIn) {

    }

    @Override
    public void handleCollectItem(SPacketCollectItem packetIn) {

    }

    @Override
    public void handleEntityTeleport(SPacketEntityTeleport packetIn) {

    }

    @Override
    public void handleEntityProperties(SPacketEntityProperties packetIn) {

    }

    @Override
    public void handleEntityEffect(SPacketEntityEffect packetIn) {

    }

    @Override
    public void handleCombatEvent(SPacketCombatEvent packetIn) {

    }

    @Override
    public void handleServerDifficulty(SPacketServerDifficulty packetIn) {

    }

    @Override
    public void handleCamera(SPacketCamera packetIn) {

    }

    @Override
    public void handleWorldBorder(SPacketWorldBorder packetIn) {

    }

    @Override
    public void handleTitle(SPacketTitle packetIn) {

    }

    @Override
    public void handlePlayerListHeaderFooter(SPacketPlayerListHeaderFooter packetIn) {

    }

    @Override
    public void handleResourcePack(SPacketResourcePackSend packetIn) {

    }

    @Override
    public void handleUpdateBossInfo(SPacketUpdateBossInfo packetIn) {

    }

    @Override
    public void handleCooldown(SPacketCooldown packetIn) {

    }

    @Override
    public void handleMoveVehicle(SPacketMoveVehicle packetIn) {

    }

    @Override
    public void onDisconnect(ITextComponent reason) {

    }
}
