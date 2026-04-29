package club.sk1er.patcher.mixins.performance;

import net.minecraft.entity.living.mob.monster.boss.WitherEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({
        BoatEntity.class,
        MinecartEntity.class,
        WitherEntity.class
})
public class EntityMixin_IntegerRemoveBoxing {

    @Redirect(
            method = "registerSyncedData",
            at = @At(value = "NEW", target = "(I)Ljava/lang/Integer;")
    )
    private Integer patcher$integerUseValueOf(int value) {
        return value;
    }

}
