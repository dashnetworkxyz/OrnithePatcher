package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BookEditScreen.class)
public class BookEditScreenMixin_AddBackground extends Screen {

    @Inject(method = "render", at = @At("HEAD"))
    private void patcher$addBackground(CallbackInfo ci) {
        drawBackgroundTexture(1);
    }

}
