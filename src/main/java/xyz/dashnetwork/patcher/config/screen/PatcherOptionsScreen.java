package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import xyz.dashnetwork.patcher.Patcher;

public class PatcherOptionsScreen extends AbstractOptionsScreen {

    private float value;

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
        //addSlider(110, () -> "Test Slider: " + value, null, (value) -> this.value = value, 0);
    }

    @Override
    public void removed() {
        Patcher.get().config().save();
    }

}
