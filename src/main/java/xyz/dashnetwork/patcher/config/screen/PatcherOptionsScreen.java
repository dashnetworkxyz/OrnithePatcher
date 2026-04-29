package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;

public class PatcherOptionsScreen extends AbstractOptionsScreen {

    public PatcherOptionsScreen(Screen parent) {
        super("Patcher Settings", parent);

        addButton(100, () -> "Bug Fixes...", "", () -> {});
        addButton(101, () -> "Miscellaneous...", "", () -> {});
        addButton(102, () -> "Performance...", "", () -> {});
        addButton(103, () -> "Screens...", "", () -> {});
        addButton(104, () -> "Screenshots...", "", () -> {});
        addButton(105, () -> "Experimental...", "", () -> {});
    }

}
