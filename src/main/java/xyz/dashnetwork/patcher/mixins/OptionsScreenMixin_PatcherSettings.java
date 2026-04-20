package xyz.dashnetwork.patcher.mixins;

import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin_PatcherSettings<T> {

    @Redirect(
            method = "init",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z")
    )
    private boolean patcher$addPatcherSettings(List<T> list, T object) {
        if (object instanceof ButtonWidget button && button.id == 104)
            button.message = I18n.translate("patcher.options.button");

        return list.add(object);
    }

}
