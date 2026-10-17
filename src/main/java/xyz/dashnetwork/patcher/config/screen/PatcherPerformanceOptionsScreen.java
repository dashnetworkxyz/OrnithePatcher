package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;

public class PatcherPerformanceOptionsScreen extends AbstractOptionsScreen {

    public PatcherPerformanceOptionsScreen(Screen parent) {
        super("Patcher Performance Settings", parent);

        addToggle("Batch Model Render", "Render models in a single draw call.", options().batchModelRendering);
    }

}
