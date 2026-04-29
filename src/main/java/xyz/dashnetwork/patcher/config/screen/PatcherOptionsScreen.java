package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;

public class PatcherOptionsScreen extends AbstractOptionsScreen {

    private int count = 0;

    public PatcherOptionsScreen(Screen parent) {
        super("Patcher Settings", parent);

        addButton(100, () -> "Test: " + count, "", () -> count++);
        addButton(99, () -> "Test2", "", () -> {});
    }

}
