package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.LanguageOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LanguageOptionsScreen.class)
public class LanguageOptionsScreenMixin_ResetUnicodeFont extends Screen {

    @Override
    public void removed() {
        minecraft.gui.getChat().reset();
    }

}
