package xyz.dashnetwork.patcher.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.world.WorldRenderer;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.hooks.WorldRendererHook;

@Mixin(GameRenderer.class)
public class GameRendererMixin_DebugCrosshairFixes {

    @Shadow
    private Minecraft minecraft;

    @Inject(method = "renderAxisIndicators", at = @At("HEAD"), cancellable = true)
    private void patcher$cancelAxisInThirdPerson(CallbackInfo ci) {
        if (minecraft.options.perspective > 0)
            ci.cancel();
    }

    @Redirect(
            method = "renderAxisIndicators",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/world/WorldRenderer;renderOutlineShape(Lnet/minecraft/util/math/Box;IIII)V")
    )
    private void patcher$drawCube(Box shape, int r, int g, int b, int a) {
        WorldRendererHook.renderSolidShape(shape, r, g, b, a);
        WorldRenderer.renderOutlineShape(shape, r, g, b, a);
    }

}
