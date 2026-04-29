package club.sk1er.patcher.mixins.performance;

import net.minecraft.entity.living.mob.monster.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin_ShortRemoveBoxing {

    @Redirect(
            method = "registerSyncedData",
            at = @At(value = "NEW", target = "(S)Ljava/lang/Short;")
    )
    private Short patcher$shortUseValueOf(short value) {
        return value;
    }

}
