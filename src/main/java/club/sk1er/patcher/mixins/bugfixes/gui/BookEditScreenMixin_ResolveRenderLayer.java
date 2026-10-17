package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BookEditScreen.class)
public class BookEditScreenMixin_ResolveRenderLayer extends Screen {

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/inventory/BookEditScreen;renderTextHoverEffect(Lnet/minecraft/text/Text;II)V"))
    private void patcher$callSuper(int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
        super.render(mouseX, mouseY, tickDelta);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/inventory/BookEditScreen;renderTextHoverEffect(Lnet/minecraft/text/Text;II)V", shift = At.Shift.AFTER), cancellable = true)
    private void patcher$cancelFurtherRendering(CallbackInfo ci) {
        ci.cancel();
    }

}
