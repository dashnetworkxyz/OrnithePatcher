package club.sk1er.patcher.mixins.bugfixes.crashes;

import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(LivingEntity.class)
public class LivingEntityMixin_ResolveCrash {

    @Inject(
            method = "tickStatusEffects",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/living/effect/StatusEffectInstance;tick(Lnet/minecraft/entity/living/LivingEntity;)Z"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true
    )
    public void patcher$checkPotionEffect(CallbackInfo ci, Iterator<Integer> iterator, Integer integer, StatusEffectInstance effect) {
        if (effect == null)
            ci.cancel();
    }

}
