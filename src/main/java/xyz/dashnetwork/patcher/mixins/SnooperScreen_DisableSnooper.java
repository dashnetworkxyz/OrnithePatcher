package xyz.dashnetwork.patcher.mixins;

import net.minecraft.client.gui.screen.SnooperScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnooperScreen.class)
public class SnooperScreen_DisableSnooper {

    @Shadow
    private ButtonWidget snooperToggleButton;

    @Inject(method = "init", at = @At("TAIL"))
    private void patcher$disableSnooperButton(CallbackInfo ci) {
        snooperToggleButton.active = false;
    }

}
