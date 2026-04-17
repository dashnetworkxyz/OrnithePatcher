package xyz.dashnetwork.ornithepatcher.mixin.old;

import net.minecraft.client.gui.screen.SnooperScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnooperScreen.class)
public final class SnooperScreenMixin {

    @Shadow
    private ButtonWidget snooperToggleButton;

    @Inject(method = "init", at = @At("TAIL"))
    public void onInit(CallbackInfo callback) {
        snooperToggleButton.active = false;
    }

}
