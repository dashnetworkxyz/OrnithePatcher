package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import xyz.dashnetwork.patcher.Patcher;

public class PatcherOptionsScreen extends AbstractOptionsScreen {

    public PatcherOptionsScreen(Screen parent) {
        super("Patcher Settings", parent);

        addButton(() -> "Miscellaneous...", "", true, () -> minecraft.openScreen(new PatcherMiscellaneousOptionsScreen(this)));
        addButton(() -> "GUI...", "", true, () -> minecraft.openScreen(new PatcherGUIOptionsScreen(this)));
        addButton(() -> "Performance...", "", true, () -> minecraft.openScreen(new PatcherPerformanceOptionsScreen(this)));
    }

    @Override
    public void removed() {
        Patcher.get().config().save();
    }

}
