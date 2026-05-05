package club.sk1er.patcher.mixins.features;

import net.minecraft.client.render.ItemInHandRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin_RemoveWaterOverlay {

    @Inject(method = "renderInWaterEffect", at = @At("HEAD"), cancellable = true)
    private void patcher$removeWaterOverlay(CallbackInfo ci) {
        if (Patcher.get().config().options().removeWaterOverlay.get())
            ci.cancel();
    }

}
