package club.sk1er.patcher.mixins.bugfixes.crashes;

import net.minecraft.client.render.item.ItemModelShaper;
import net.minecraft.client.resource.model.BakedModel;
import net.minecraft.client.resource.model.ModelManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemModelShaper.class)
public class ItemModelShaperMixin_ResolveCrash {

    @Shadow @Final
    private ModelManager manager;

    @Inject(
            method = "getModel(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/client/resource/model/BakedModel;",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"),
            locals = LocalCapture.CAPTURE_FAILSOFT,
            cancellable = true
    )
    private void patcher$returnMissingModel(ItemStack stack, CallbackInfoReturnable<BakedModel> cir, Item item) {
        if (item == null)
            cir.setReturnValue(manager.getMissingModel());
    }

}
