package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.locale.I18n;
import xyz.dashnetwork.patcher.config.screen.widget.PatcherButtonWidget;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractOptionsScreen extends Screen {

    private final List<ButtonWidget> queued = new ArrayList<>();
    private final String title;
    private final Screen parent;

    public AbstractOptionsScreen(String title, Screen parent) {
        this.title = title;
        this.parent = parent;
    }

    protected void addButton(Supplier<String> label, String description, Consumer<PatcherButtonWidget> callback) {
        queued.add(new PatcherButtonWidget(queued.size() + 100, label, description, callback));
    }

    /*
    protected void addSlider(Supplier<String> label, String description, float initial, Consumer<Float> callback) {
        descriptors.add(new SliderDescriptor(label, description, initial, callback));
    }
     */

    @Override
    public void init() {
        int rows = (queued.size() + 1) / 2;
        int totalHeight = rows * 20 + (rows - 1) * 4;

        int startY = (height - totalHeight) / 2;
        int leftX = width / 2 - 155;
        int rightX = width / 2 + 5;
        int index = 0;

        for (ButtonWidget button : queued) {
            int row = index / 2;
            int column = index % 2;

            button.x = column == 0 ? leftX : rightX;
            button.y = startY + row * 24;

            buttons.add(button);
            index++;
        }

        int doneY = startY + rows * 24 + 8;
        buttons.add(new ButtonWidget(200, width / 2 - 100, doneY, I18n.translate("gui.done")));
    }

    @Override
    public void buttonClicked(ButtonWidget button) {
        if (button.id == 200)
            minecraft.openScreen(parent);
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        renderBackground();
        drawCenteredString(textRenderer, title, width / 2, 15, 16777215);

        super.render(mouseX, mouseY, tickDelta);
    }

}
