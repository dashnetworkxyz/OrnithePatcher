package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin_SaveSettings extends Screen {

    @Override
    public void removed() {
        minecraft.options.save();
    }

}
