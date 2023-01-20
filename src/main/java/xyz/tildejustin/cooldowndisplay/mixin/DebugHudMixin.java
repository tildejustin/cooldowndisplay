package xyz.tildejustin.cooldowndisplay.mixin;

import it.unimi.dsi.fastutil.longs.LongSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xyz.tildejustin.cooldowndisplay.CooldownDisplay;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(DebugHud.class)
public abstract class DebugHudMixin extends DrawableHelper {
    @Inject(method = "getLeftText", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void addCooldownDebugText(CallbackInfoReturnable<List> cir, String string, IntegratedServer integratedServer, ClientConnection clientConnection, float f, float g, BlockPos blockPos, Entity entity, Direction direction, String string2, ChunkPos chunkPos, World world, LongSet longSet, List<String> list) {
        if (CooldownDisplay.showCooldown) {
            assert MinecraftClient.getInstance().interactionManager != null;
            list.add("Block Breaking Cooldown: " + ((ClientPlayerInteractionManagerMixin) MinecraftClient.getInstance().interactionManager).getBlockBreakingCooldown());
        }
    }
}
