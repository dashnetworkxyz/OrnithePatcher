package club.sk1er.patcher.mixins.performance;

import net.minecraft.entity.living.mob.passive.animal.tameable.WolfEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({
        BoatEntity.class,
        MinecartEntity.class,
        WolfEntity.class
})
public class EntityMixin_FloatRemoveBoxing {

    @Redirect(
            method = "registerSyncedData",
            at = @At(value = "NEW", target = "(F)Ljava/lang/Float;")
    )
    private Float patcher$floatUseValueOf(float value) {
        return value;
    }

}
