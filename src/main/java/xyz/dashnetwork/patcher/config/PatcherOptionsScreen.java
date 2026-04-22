package xyz.dashnetwork.patcher.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;

public class PatcherOptionsScreen extends Screen {

    private final String title = "Patcher Settings";

    private final Screen parent;
    private final PatcherConfig config;

    public PatcherOptionsScreen(Screen parent, PatcherConfig config) {
        this.parent = parent;
        this.config = config;
    }

    @Override
    public void init() {
        buttons.clear();
        buttons.add(new ButtonWidget(200, width / 2 - 100, height - 27, I18n.translate("gui.done")));
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        renderBackground();

        super.render(mouseX, mouseY, tickDelta);
    }

}
