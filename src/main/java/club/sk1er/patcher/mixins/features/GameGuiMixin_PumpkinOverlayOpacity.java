package club.sk1er.patcher.mixins.features;

import net.minecraft.client.gui.GameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(GameGui.class)
public class GameGuiMixin_PumpkinOverlayOpacity {

    @Inject(method = "renderPumpkinOverlay", at = @At("HEAD"), cancellable = true)
    private void patcher$cancelPumpkinOverlay(CallbackInfo ci) {
        if (Patcher.get().config().options().pumpkinOverlayOpacity.get() == 0)
            ci.cancel();
    }

    @ModifyArg(method = "renderPumpkinOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/platform/GlStateManager;color4f(FFFF)V"), index = 3)
    private float patcher$modifyPumpkinOverlayOpacity(float alpha) {
        return Patcher.get().config().options().pumpkinOverlayOpacity.get() / 100f;
    }

}
