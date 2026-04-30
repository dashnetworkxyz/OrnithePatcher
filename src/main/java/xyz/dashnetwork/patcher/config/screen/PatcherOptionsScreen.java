package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import xyz.dashnetwork.patcher.Patcher;

public class PatcherOptionsScreen extends AbstractOptionsScreen {

    private int test;

    public PatcherOptionsScreen(Screen parent) {
        super("Patcher Settings", parent);

        addButton(() -> "Bug Fixes...", "", (button) -> {
            System.out.println("TESTY TEST TEST");
        });
        addButton(() -> "Miscellaneous...", "", (button) -> {});
        addButton(() -> "Performance...", "", (button) -> {});
        addButton(() -> "Screens...", "", (button) -> {});
        addButton(() -> "Screenshots...", "", (button) -> {});
        addButton(() -> "Experimental...", "", (button) -> {});
        addSlider(() -> "Test Slider: " + test, null, 5, 0, (slider) -> this.test = slider.getSegment());
    }

    @Override
    public void removed() {
        Patcher.get().config().save();
    }

}
