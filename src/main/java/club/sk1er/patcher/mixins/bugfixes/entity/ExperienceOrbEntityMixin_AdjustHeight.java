package club.sk1er.patcher.mixins.bugfixes.entity;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin_AdjustHeight {

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/living/player/PlayerEntity;getEyeHeight()F"))
    private float patcher$lowerHeight(PlayerEntity entity) {
        return entity.getEyeHeight() / 2F;
    }

}
