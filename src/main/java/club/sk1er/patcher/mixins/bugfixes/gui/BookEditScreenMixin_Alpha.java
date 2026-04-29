package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.inventory.BookEditScreen;
import net.minecraft.client.render.platform.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BookEditScreen.class)
public class BookEditScreenMixin_Alpha {

    @Inject(method = "render", at = @At("HEAD"))
    private void alpha(CallbackInfo ci) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.blendFuncSeparate(770, 771, 1, 0);
    }

    @Inject(method = "render", at = @At(value = "TAIL"))
    private void end(CallbackInfo ci) {
        GlStateManager.disableAlphaTest();
        GlStateManager.disableBlend();
    }

}
