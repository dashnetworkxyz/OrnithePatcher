package club.sk1er.patcher.mixins.performance;

import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(WorldChunk.class)
public class WorldChunkMixin_Optimization {

    @ModifyArg(
            method = "setBlockState",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/chunk/WorldChunk;updateHeightMap(III)V", ordinal = 0),
            index = 1
    )
    private int patcher$subtractOneFromY(int y) {
        return y - 1;
    }

}
