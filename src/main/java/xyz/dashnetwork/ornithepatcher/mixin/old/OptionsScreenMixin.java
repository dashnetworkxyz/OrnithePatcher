package xyz.dashnetwork.ornithepatcher.mixin.old;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.options.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public final class OptionsScreenMixin extends Screen {

    @Inject(method = "init", at = @At("TAIL"))
    public void onInit(CallbackInfo callback) {
        for (ButtonWidget button : buttons) {
            if (button.id == GameOptions.Option.REALMS_NOTIFICATIONS.getId()) {
                button.active = false;
                break;
            }
        }
    }

}
