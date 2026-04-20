package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(value = GameRenderer.class, priority = 1001)
public abstract class GameRendererMixin_ParallaxFix {

    @ModifyConstant(method = "transformCamera", constant = @Constant(floatValue = -0.1F))
    private float patcher$modifyParallax(float original) {
        // Normally breaks debug crosshair, but this is already re-rendered in GameRendererMixin_FixCrosshairZoom
        return Patcher.get().config().options().parallaxFix ? 0.05F : original;
    }

}
