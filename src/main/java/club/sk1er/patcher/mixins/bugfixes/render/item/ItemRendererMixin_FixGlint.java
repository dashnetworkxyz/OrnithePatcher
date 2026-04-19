package club.sk1er.patcher.mixins.bugfixes.render.item;

import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.render.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin_FixGlint {

    @Inject(
            method = "renderGuiItem",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/ItemRenderer;renderGuiItemModel(Lnet/minecraft/item/ItemStack;II)V")
    )
    private void patcher$correctGlint(CallbackInfo ci) {
        GlStateManager.enableDepthTest();
    }

}
