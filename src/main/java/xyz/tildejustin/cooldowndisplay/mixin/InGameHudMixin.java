package xyz.tildejustin.cooldowndisplay.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {

    @Final
    @Shadow
    private Minecraft field_1166;

    @Inject(at = @At("TAIL"), method = "method_979")
    void BlockCooldownDebug(CallbackInfo ci) {
        if (this.field_1166.options.debugEnabled) {
            GL11.glPushMatrix();
            this.field_1166.textRenderer.method_956(
                "Block breaking cooldown: " + ((ClientPlayerInteractionManagerMixin) field_1166.interactionManager).getBlockBreakingCooldown(),
                2,
                124, //increment by 10
                0xFFFFFF
            );
            GL11.glPopMatrix();
        }
    }
}
