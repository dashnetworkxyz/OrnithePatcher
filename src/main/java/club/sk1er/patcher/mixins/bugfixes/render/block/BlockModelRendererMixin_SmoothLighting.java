package club.sk1er.patcher.mixins.bugfixes.render.block;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.client.render.block.BlockModelRenderer$AmbientOcclusionFace")
public class BlockModelRendererMixin_SmoothLighting {

    @Redirect(
            method = "compute",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;isTranslucent()Z")
    )
    private boolean patcher$betterSmoothLighting(Block block) {
        return block.isViewBlocking() || block.getOpacity() == 0;
    }

}
