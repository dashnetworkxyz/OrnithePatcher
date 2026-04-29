package club.sk1er.patcher.mixins.performance;

import net.minecraft.entity.living.mob.ambient.BatEntity;
import net.minecraft.entity.living.mob.monster.BlazeEntity;
import net.minecraft.entity.living.mob.monster.EndermanEntity;
import net.minecraft.entity.living.mob.monster.SkeletonEntity;
import net.minecraft.entity.living.mob.monster.SpiderEntity;
import net.minecraft.entity.living.mob.passive.animal.SheepEntity;
import net.minecraft.entity.living.mob.passive.animal.tameable.WolfEntity;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({
        BatEntity.class,
        BlazeEntity.class,
        EndermanEntity.class,
        FurnaceMinecartEntity.class,
        SheepEntity.class,
        SkeletonEntity.class,
        SpiderEntity.class,
        WolfEntity.class
})
public class EntityMixin_ByteRemoveBoxing {

    @Redirect(
            method = "registerSyncedData",
            at = @At(value = "NEW", target = "(B)Ljava/lang/Byte;")
    )
    private Byte patcher$byteUseValueOf(byte value) {
        return value;
    }

}
