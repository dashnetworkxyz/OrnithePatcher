package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityMixin_FixedBrightness {

    @Redirect(
            method = "getLightLevel",
            at = @At(value = "NEW", target = "(DDD)Lnet/minecraft/util/math/BlockPos;")
    )
    private BlockPos patcher$clampVerticalRange(double x, double y, double z) {
        return new BlockPos(x, Math.clamp(y, 0, 255), z);
    }

}
