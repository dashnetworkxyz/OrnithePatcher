package xyz.dashnetwork.patcher.mixins;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.config.screen.PatcherOptionsScreen;

import java.util.List;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin_PatcherSettings<T> extends Screen {

    @Redirect(
            method = "init",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z")
    )
    private boolean patcher$addPatcherSettings(List<T> list, T object) {
        if (object instanceof ButtonWidget button && button.id == 104)
            button.message = "Patcher Settings...";

        return list.add(object);
    }

    @Inject(method = "buttonClicked", at = @At("HEAD"), cancellable = true)
    private void patcher$openPatcherSettings(ButtonWidget button, CallbackInfo ci) {
        if (button.active && button.id == 104) {
            minecraft.options.save();
            minecraft.openScreen(new PatcherOptionsScreen((OptionsScreen) (Object) this));
            ci.cancel();
        }
    }

}
