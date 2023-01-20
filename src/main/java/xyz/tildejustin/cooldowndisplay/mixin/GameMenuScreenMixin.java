package xyz.tildejustin.cooldowndisplay.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.tildejustin.cooldowndisplay.CooldownDisplay;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "initWidgets")
    private void addCooldownButton(CallbackInfo ci) {
        this.addButton(
                new ButtonWidget(
                        0,
                        0,
                        120,
                        20,
                        new TranslatableText("Display Block Cooldown"),
                        ButtonWidget ->
                                CooldownDisplay.showCooldown = !CooldownDisplay.showCooldown
                )
        );
        CooldownDisplay.log(Level.INFO, "Adding cooldown button");
    }
}
