package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.menu.AddServerScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AddServerScreen.class)
public class AddServerScreenMixin_RemoveSpaces {

    @Redirect(method = "buttonClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;getText()Ljava/lang/String;"))
    private String patcher$removeSpaces(TextFieldWidget widget) {
        return widget.getText().trim();
    }

}
