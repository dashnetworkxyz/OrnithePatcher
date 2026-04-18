package xyz.dashnetwork.patcher.mixins.realms;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin_DisableRealms extends Screen {

    @Inject(method = "init", at = @At("TAIL"))
    private void patcher$removeRealmsNotifications(CallbackInfo ci) {
        buttons.removeIf(button -> button.id == GameOptions.Option.REALMS_NOTIFICATIONS.getId());
    }

}
