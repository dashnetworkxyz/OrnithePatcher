package club.sk1er.patcher.mixins.features;

import net.minecraft.client.gui.ToastGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(ToastGui.class)
public class ToastGuiMixin_DisableToast {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void patcher$disableToast(CallbackInfo ci) {
        if (Patcher.get().config().options().disableToast.get())
            ci.cancel();
    }

}
