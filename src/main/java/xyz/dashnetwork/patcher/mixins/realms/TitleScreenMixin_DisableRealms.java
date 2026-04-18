package xyz.dashnetwork.patcher.mixins.realms;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin_DisableRealms {

    @Shadow
    private ButtonWidget realmsButton;

    @Inject(method = "initWidgetsNormal", at = @At("TAIL"))
    private void patcher$disableRealmsButton(CallbackInfo ci) {
        realmsButton.visible = false;
    }

}
