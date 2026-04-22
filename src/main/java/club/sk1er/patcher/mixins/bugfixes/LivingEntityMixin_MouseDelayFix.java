package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.client.entity.living.player.LocalClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin_MouseDelayFix extends Entity {

    public LivingEntityMixin_MouseDelayFix(World world) {
        super(world);
    }

    @Inject(method = "getRotationVec", at = @At("HEAD"), cancellable = true)
    private void patcher$mouseDelayFix(float partialTicks, CallbackInfoReturnable<Vec3d> cir) {
        if ((LivingEntity) (Object) this instanceof LocalClientPlayerEntity)
            cir.setReturnValue(super.getRotationVec(partialTicks));
    }

}
