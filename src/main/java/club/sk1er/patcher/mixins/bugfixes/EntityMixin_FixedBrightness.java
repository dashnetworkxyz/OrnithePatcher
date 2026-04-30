package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityMixin_FixedBrightness {

    @SuppressWarnings("SuspiciousNameCombination")
    @Redirect(
            method = "getLightLevel",
            at = @At(value = "NEW", target = "(DDD)Lnet/minecraft/util/math/BlockPos;")
    )
    private BlockPos patcher$clampVerticalRange(double x, double y, double z) {
        return new BlockPos(x, MathHelper.clamp(y, 0, 255), z);
    }

}
