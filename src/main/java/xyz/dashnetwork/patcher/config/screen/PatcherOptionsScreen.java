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
        addButton(() -> "Miscellaneous...", "", () -> {});
        addButton(() -> "Performance...", "", () -> {});
        addButton(() -> "Screens...", "", () -> {});
        addButton(() -> "Screenshots...", "", () -> {});
        addButton(() -> "Experimental...", "This is a test tooltip message.\nLine 2 test", () -> {});
        addSlider(() -> "Test Slider: " + test, null, 5, 0, segment -> test = segment);
        addToggle("F3N Keybind", null, options.f3nKeybind);
    }

    @Override
    public void removed() {
        Patcher.get().config().save();
    }

}
