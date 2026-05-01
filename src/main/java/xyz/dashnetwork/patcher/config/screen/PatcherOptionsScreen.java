package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import xyz.dashnetwork.patcher.Patcher;

public class PatcherOptionsScreen extends AbstractOptionsScreen {

    public PatcherOptionsScreen(Screen parent) {
        super("Patcher Settings", parent);

        addButton(() -> "Miscellaneous...", "", () -> minecraft.openScreen(new PatcherMiscellaneousOptionsScreen(this)));
        addButton(() -> "Screens...", "", () -> minecraft.openScreen(new PatcherScreensOptionsScreen(this)));
    }

    @Override
    public void removed() {
        Patcher.get().config().save();
    }

}
