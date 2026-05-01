package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import xyz.dashnetwork.patcher.Patcher;
import xyz.dashnetwork.patcher.config.ConfigOptions;

public class PatcherMiscellaneousOptionsScreen extends AbstractOptionsScreen {

    public PatcherMiscellaneousOptionsScreen(Screen parent) {
        super("Patcher Miscellaneous Settings", parent);
        ConfigOptions options = Patcher.get().config().options();

        addToggle("Borderless Fullscreen", null, options.borderlessFullscreen);
        addToggle("F3N Keybind", """
                        Toggles the backported F3+N keybind.
                        Changes gamemode between spectator and creative.
                        """, options.f3nKeybind);
    }

}
