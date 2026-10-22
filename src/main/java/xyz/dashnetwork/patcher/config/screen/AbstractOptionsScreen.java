package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.locale.I18n;
import net.minecraft.util.math.MathHelper;
import xyz.dashnetwork.patcher.Patcher;
import xyz.dashnetwork.patcher.config.ConfigOptions;
import xyz.dashnetwork.patcher.config.screen.widget.PatcherButtonWidget;
import xyz.dashnetwork.patcher.config.screen.widget.PatcherSliderWidget;
import xyz.dashnetwork.patcher.mixins.accessors.ButtonWidgetAccessor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public abstract class AbstractOptionsScreen extends Screen {

    private static final byte TOOLTIP_PADDING = 5;

    private final List<ButtonWidget> queued = new ArrayList<>();
    private final String title;
    private final Screen parent;
    private long mouseStillTime = 0;
    private int lastMouseX = -1;
    private int lastMouseY = -1;

    public AbstractOptionsScreen(String title, Screen parent) {
        this.title = title;
        this.parent = parent;
    }

    protected ConfigOptions options() {
        return Patcher.get().config().options();
    }

    protected void addButton(Supplier<String> label, String tooltip, boolean active, final Runnable callback) {
        queued.add(new PatcherButtonWidget(queued.size() + 100, label, tooltip, active,
                ignored -> callback.run())
        );
    }

    protected void addToggle(String label, String tooltip, boolean active, AtomicBoolean atomic) {
        addButton(
                () -> label + ": " + (atomic.get() ? I18n.translate("options.on") : I18n.translate("options.off")),
                tooltip,
                active,
                () -> atomic.set(!atomic.get())
        );
    }

    protected void addToggle(String label, String tooltip, AtomicBoolean atomic) {
        addToggle(label, tooltip, true, atomic);
    }

    protected void addToggle(String label, String tooltip, boolean active, AtomicInteger atomic, String... values) {
        addButton(
                () -> label + ": " + values[atomic.get()],
                tooltip,
                active,
                () -> {
                    int value = atomic.get() + 1;

                    if (value >= values.length)
                        atomic.set(0);
                    else
                        atomic.set(value);
                }
        );
    }

    protected void addToggle(String label, String tooltip, AtomicInteger atomic, String... values) {
        addToggle(label, tooltip, true, atomic, values);
    }

    protected void addSlider(Supplier<String> label, String tooltip, int segments, int initial, boolean active, final IntConsumer callback) {
        queued.add(new PatcherSliderWidget(queued.size() + 100, label, tooltip, segments, initial, active,
                slider -> callback.accept(slider.getSegment()))
        );
    }

    protected void addRange(Supplier<String> label, String tooltip, int min, int max, boolean active, AtomicInteger atomic) {
        addSlider(
                label,
                tooltip,
                max - min + 1,
                atomic.get() - min,
                active,
                segment -> atomic.set(segment + min)
        );
    }

    protected void addRange(Supplier<String> label, String tooltip, int min, int max, AtomicInteger atomic) {
        addRange(label, tooltip, min, max, true, atomic);
    }

    protected void addRange(String label, String tooltip, int min, int max, boolean active, AtomicInteger atomic) {
        addRange(() -> label + ": " + atomic.get(), tooltip, min, max, active, atomic);
    }

    protected void addRange(String label, String tooltip, int min, int max, AtomicInteger atomic) {
        addRange(label, tooltip, min, max, true, atomic);
    }

    private void renderTooltip(int mouseX, int mouseY) {
        if (MathHelper.abs(mouseX - lastMouseX) > 5 || MathHelper.abs(mouseY - lastMouseY) > 5) {
            mouseStillTime = System.currentTimeMillis();
            lastMouseX = mouseX;
            lastMouseY = mouseY;
        } else if (mouseStillTime + 700 < System.currentTimeMillis()) {
            if (getSelectedButton(mouseX, mouseY) instanceof PatcherButtonWidget button) {
                String tooltip = button.getTooltip();

                if (tooltip == null)
                    return;

                String[] lines = tooltip.split("\n");
                Rectangle rec = getTooltipRectangle(mouseY, lines.length);

                fill(rec.x, rec.y, rec.width + rec.x, rec.height + rec.y, -536870912);

                for (int i = 0; i < lines.length; i++)
                    minecraft.textRenderer.drawWithShadow(
                            lines[i],rec.x + TOOLTIP_PADDING, rec.y + TOOLTIP_PADDING + i * 11, 14540253
                    );
            }
        }
    }

    private Rectangle getTooltipRectangle(int mouseY, int size) {
        int x = width / 2 - 150;
        int y = height / 6 - 7;

        if (mouseY <= y + 98)
            y += 105;

        return new Rectangle(x, y, 300, (size * 11) + TOOLTIP_PADDING + 2);
    }

    private ButtonWidget getSelectedButton(int mouseX, int mouseY) {
        for (ButtonWidget button : buttons) {
            if (!button.visible)
                continue;

            int x2 = button.x + button.getWidth();
            int y2 = button.y + ((ButtonWidgetAccessor) button).getHeight();

            if (button.x <= mouseX && button.y <= mouseY && x2 > mouseX && y2 > mouseY)
                return button;
        }

        return null;
    }

    @Override
    public void init() {
        int rows = (queued.size() + 1) / 2;
        int startY = height / 6 - 12 + 24;
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
        renderTooltip(mouseX, mouseY);
    }

}
