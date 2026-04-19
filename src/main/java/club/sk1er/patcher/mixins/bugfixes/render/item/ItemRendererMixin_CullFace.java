package club.sk1er.patcher.mixins.bugfixes.render.item;

import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.render.model.block.ModelTransformation;
import net.minecraft.client.render.model.block.ModelTransformations;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.resource.model.BakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin_CullFace {

    @Shadow
    protected abstract boolean shouldCullFrontFace(ModelTransformation transformation);

    @Inject(
            method = "renderItemInHand(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/resource/model/BakedModel;Lnet/minecraft/client/render/model/block/ModelTransformations$Type;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/ItemRenderer;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/resource/model/BakedModel;)V")
    )
    private void patcher$cullFace(ItemStack item, BakedModel model, ModelTransformations.Type transform, CallbackInfo ci) {
        if (shouldCullFrontFace(model.getTransformations().get(transform)))
            GlStateManager.cullFace(1028);
    }

}
