package club.sk1er.patcher.mixins.bugfixes.optifine;

import net.minecraft.block.state.BlockState;
import net.minecraft.client.render.world.RenderChunkRegion;
import net.minecraft.util.math.BlockPos;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(RenderChunkRegion.class)
public class RenderChunkRegionMixin_FixConnectedTextures {

    @Shadow @Final private static BlockState AIR_STATE;
    @Shadow private BlockState[] blocks;

    @Inject(
            method = "getBlockState",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/world/RenderChunkRegion;blocks:[Lnet/minecraft/block/state/BlockState;", ordinal = 0, shift = At.Shift.AFTER, opcode = Opcodes.GETFIELD),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true
    )
    private void patcher$connectedTexturesBoundsCheck(BlockPos pos, CallbackInfoReturnable<BlockState> cir, int i) {
        if (i < 0 || i >= blocks.length)
            cir.setReturnValue(AIR_STATE);
    }
}
