package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.inventory.slot.InventorySlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenuScreen.class)
public class InventoryMenuScreenMixin_Alpha {

    @Inject(
            method = "render",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/inventory/menu/InventoryMenuScreen;renderMenuBackground(FII)V")
    )
    private void alpha(CallbackInfo ci) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
    }

    @Inject(
            method = "renderSlot",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/inventory/menu/InventoryMenuScreen;drawSprite(IILnet/minecraft/client/render/texture/TextureAtlasSprite;II)V")
    )
    private void slot(CallbackInfo ci) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
    }

}
