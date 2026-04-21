package xyz.dashnetwork.patcher.mixins;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.world.WorldRenderer;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import xyz.dashnetwork.patcher.hooks.WorldRendererHook;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin_FixDebugCrosshairWireframe {

    @Redirect(
            method = "renderAxisIndicators",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/world/WorldRenderer;renderOutlineShape(Lnet/minecraft/util/math/Box;IIII)V")
    )
    private void patcher$drawCube(Box shape, int r, int g, int b, int a) {
        Box box = new Box(shape.minX * 10, shape.minY * 10, shape.minZ * 10, shape.maxX * 10, shape.maxY * 10, shape.maxZ * 10);

        WorldRendererHook.renderSolidShape(box, r, g, b, a);
        WorldRenderer.renderOutlineShape(box, r, g, b, a);
    }

}
