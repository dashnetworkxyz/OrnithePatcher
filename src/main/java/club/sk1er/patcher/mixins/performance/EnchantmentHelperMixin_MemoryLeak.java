package club.sk1er.patcher.mixins.performance;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin_MemoryLeak {

    @Shadow @Final private static EnchantmentHelper.ProtectionModifier PROTECTION_MODIFIER;
    @Shadow @Final private static EnchantmentHelper.DamageModifier DAMAGE_MODIFIER;
    @Shadow @Final private static EnchantmentHelper.ProtectionWildcard PROTECTION_WILDCARD;
    @Shadow @Final private static EnchantmentHelper.DamageWildcard DAMAGE_WILDCARD;

    @Inject(method = "modifyProtection", at = @At("TAIL"))
    private static void patcher$fixMemoryLeak(ItemStack[] armor, DamageSource source, CallbackInfoReturnable<Integer> cir) {
        PROTECTION_MODIFIER.source = null;
    }

    @Inject(method = "modifyDamage", at = @At("TAIL"))
    private static void patcher$fixMemoryLeak(ItemStack weapon, MobType target, CallbackInfoReturnable<Float> cir) {
        DAMAGE_MODIFIER.mobType = null;
    }

    @Inject(method = "applyProtectionWildcard", at = @At("TAIL"))
    private static void patcher$fixMemoryLeak(LivingEntity attacker, Entity target, CallbackInfo ci) {
        PROTECTION_WILDCARD.attacker = null;
        PROTECTION_WILDCARD.target = null;
    }

    @Inject(method = "applyDamageWildcard", at = @At("TAIL"))
    private static void patcher$fixMemoryLeak2(LivingEntity attacker, Entity target, CallbackInfo ci) {
        DAMAGE_WILDCARD.attacker = null;
        DAMAGE_WILDCARD.target = null;
    }

}
