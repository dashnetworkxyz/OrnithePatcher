package club.sk1er.patcher.mixins.bugfixes.optifine;

import club.sk1er.patcher.hooks.DebugCrosshairHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin_FixCrosshairZoom {

    @Shadow
    private Minecraft minecraft;

    @Shadow
    public abstract void setupGuiState();

    @Inject(
            method = "renderAxisIndicators",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getCamera()Lnet/minecraft/entity/Entity;"),
            cancellable = true
    )
    private void patcher$cancelDebugCrosshairDraw(CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(
            method = "render(FJ)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GameGui;render(F)V")
    )
    private void patcher$redrawDbugCrosshair(float partialTicks, long nanoTime, CallbackInfo ci) {
        setupGuiState();
        DebugCrosshairHook.renderDirections(partialTicks, minecraft);
    }

}
