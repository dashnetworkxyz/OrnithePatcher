package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import xyz.dashnetwork.patcher.Patcher;
import xyz.dashnetwork.patcher.config.ConfigOptions;

public class PatcherScreensOptionsScreen extends AbstractOptionsScreen {

    public PatcherScreensOptionsScreen(Screen parent) {
        super("Patcher Screen Settings", parent);
        final ConfigOptions options = Patcher.get().config().options();
    }

}
