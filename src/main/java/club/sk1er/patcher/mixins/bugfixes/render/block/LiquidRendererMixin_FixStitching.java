package club.sk1er.patcher.mixins.bugfixes.render.block;

import net.minecraft.client.render.block.LiquidRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(LiquidRenderer.class)
public class LiquidRendererMixin_FixStitching {

    @ModifyConstant(method = "render", constant = @Constant(floatValue = 0.001F))
    private float patcher$fixFluidStitching(float original) {
        return 0.0F;
    }

}
