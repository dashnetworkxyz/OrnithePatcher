package xyz.dashnetwork.patcher.config.screen;

import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectSortedMap;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.locale.I18n;

import java.util.Map;
import java.util.function.Supplier;

public abstract class AbstractOptionsScreen extends Screen {

    private static final byte COLUMNS = 2;
    private static final short BUTTON_WIDTH = 150;
    private static final byte BUTTON_HEIGHT = 20;
    private static final byte ROW_GAP = 4;

    private record ButtonDescriptor(Supplier<String> label, String description, Runnable runnable) {}

    private final Int2ObjectLinkedOpenHashMap<ButtonDescriptor> descriptorMap = new Int2ObjectLinkedOpenHashMap<>();
    private final String title;
    private final Screen parent;

    public AbstractOptionsScreen(String title, Screen parent) {
        this.title = title;
        this.parent = parent;
    }

    protected void addButton(int id, Supplier<String> label, String description, Runnable runnable) {
        descriptorMap.put(id, new ButtonDescriptor(label, description, runnable));
    }

    @Override
    public void init() {
        buttons.clear();

        Int2ObjectSortedMap.FastSortedEntrySet<ButtonDescriptor> set = descriptorMap.int2ObjectEntrySet();

        int rows = (set.size() + COLUMNS - 1) / 2;
        int totalHeight = rows * BUTTON_HEIGHT + (rows - 1) * ROW_GAP;

        int startY = (height - totalHeight) / 2;
        int leftX = width / 2 - 155;
        int rightX = width / 2 + 5;
        int index = 0;

        for (Map.Entry<Integer, ButtonDescriptor> entry : set) {
            ButtonDescriptor descriptor = entry.getValue();
            int row = index / COLUMNS;
            int column = index % COLUMNS;
            int x = column == 0 ? leftX : rightX;
            int y = startY + row * (BUTTON_HEIGHT + ROW_GAP);

            buttons.add(new ButtonWidget(entry.getKey(), x, y, BUTTON_WIDTH, BUTTON_HEIGHT, descriptor.label().get()));
            index++;
        }

        int doneY = startY + rows * (20 + ROW_GAP) + 8;
        buttons.add(new ButtonWidget(200, width / 2 - 100, doneY, I18n.translate("gui.done")));
    }

    @Override
    protected void buttonClicked(ButtonWidget button) {
        if (!button.active)
            return;

        if (button.id == 200)
            minecraft.openScreen(parent);
        else {
            ButtonDescriptor descriptor = descriptorMap.get(button.id);

            if (descriptor.runnable() != null)
                descriptor.runnable().run();

            if (descriptor.label() != null)
                button.message = descriptor.label().get();
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float tickDelta) {
        renderBackground();
        drawCenteredString(textRenderer, title, width / 2, 15, 16777215);

        super.render(mouseX, mouseY, tickDelta);
    }

}
