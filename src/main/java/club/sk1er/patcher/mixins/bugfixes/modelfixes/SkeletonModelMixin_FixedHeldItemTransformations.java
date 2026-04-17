package club.sk1er.patcher.mixins.bugfixes.modelfixes;

import net.minecraft.client.render.model.entity.HumanoidModel;
import net.minecraft.client.render.model.entity.SkeletonModel;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SkeletonModel.class)
public class SkeletonModelMixin_FixedHeldItemTransformations extends HumanoidModel {

    @Override
    public void translateRightArm(float scale) {
        rightArm.x++;
        rightArm.render(scale);
        rightArm.x--;
    }

}
