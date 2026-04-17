package club.sk1er.patcher.mixins.bugfixes.modelfixes;

import net.minecraft.client.render.model.entity.VillagerModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(VillagerModel.class)
public class VillagerModelMixin_ResolveRobeHeight {

    @ModifyConstant(method = "<init>(FFII)V", constant = @Constant(intValue = 18))
    private int patcher$changeTextureHeight(int original) {
        return 20;
    }

}
