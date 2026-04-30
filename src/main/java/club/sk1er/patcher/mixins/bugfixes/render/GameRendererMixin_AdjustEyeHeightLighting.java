package club.sk1er.patcher.mixins.bugfixes.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GameRenderer.class)
public class GameRendererMixin_AdjustEyeHeightLighting {

    @Shadow
    private Minecraft minecraft;

    @ModifyArg(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;getBrightness(Lnet/minecraft/util/math/BlockPos;)F")
    )
    private BlockPos patcher$accountForEyes(BlockPos pos) {
        return new BlockPos(minecraft.getCamera().getEyePosition(1.0F));
    }

}
