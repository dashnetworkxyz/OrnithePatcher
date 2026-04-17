package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import net.minecraft.client.render.platform.GlStateManager;
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
    private void alpha(int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
    }

    @Inject(
            method = "renderSlot",
            at = @At(value = "INVOKE", target = "")
    )
    private void slot() {

    }

}
