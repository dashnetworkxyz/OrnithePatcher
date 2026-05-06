package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import xyz.dashnetwork.patcher.Patcher;
import xyz.dashnetwork.patcher.config.ConfigOptions;

public class PatcherPerformanceOptionsScreen extends AbstractOptionsScreen {

    public PatcherPerformanceOptionsScreen(Screen parent) {
        super("Patcher Performance Settings", parent);
        final ConfigOptions options = Patcher.get().config().options();

        addToggle("Batch Model Render", null, options.batchModelRendering);
    }

}
