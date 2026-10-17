package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Screen.class)
public class ScreenMixin_SaveSettings extends Screen {

    @Override
    public void removed() {
        minecraft.options.save();
    }

}
