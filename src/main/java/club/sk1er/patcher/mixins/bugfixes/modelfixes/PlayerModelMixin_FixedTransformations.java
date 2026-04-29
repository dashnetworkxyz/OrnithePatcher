package club.sk1er.patcher.mixins.bugfixes.modelfixes;

import net.minecraft.client.render.model.entity.HumanoidModel;
import net.minecraft.client.render.model.entity.PlayerModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerModel.class)
public class PlayerModelMixin_FixedTransformations extends HumanoidModel {

    @Shadow
    private boolean thinArms;

    @ModifyConstant(method = "<init>", constant = @Constant(floatValue = 2.5F))
    private float patcher$fixAlexArmHeight(float original) {
        return 2.0F;
    }

    /**
     * @author asbyth
     * @reason Resolve item positions being incorrect on Alex models (MC-72397)
     */
    @Overwrite
    public void translateRightArm(float scale) {
        if (thinArms) {
            rightArm.x += 0.5F;
            rightArm.transform(scale);
            rightArm.z -= 0.5F;
        } else {
            rightArm.transform(scale);
        }
    }

}
