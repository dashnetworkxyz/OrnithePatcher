package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import xyz.dashnetwork.patcher.Patcher;
import xyz.dashnetwork.patcher.config.ConfigOptions;

public class PatcherOptionsScreen extends AbstractOptionsScreen {

    private ConfigOptions options;
    private int test;

    public PatcherOptionsScreen(Screen parent) {
        super("Patcher Settings", parent);
        options = Patcher.get().config().options();

        addButton(() -> "Bug Fixes...", "", () -> {});
        addButton(() -> "Miscellaneous...", "", () -> minecraft.openScreen(new PatcherMiscellaneousOptionsScreen(this)));
        addButton(() -> "Performance...", "", () -> {});
        addButton(() -> "Screens...", "", () -> {});
        addButton(() -> "Screenshots...", "", () -> {});
        addToggle("Windowed Fullscreen", null, options.windowedFullscreen);
    }

    @Override
    public void removed() {
        Patcher.get().config().save();
    }

}
