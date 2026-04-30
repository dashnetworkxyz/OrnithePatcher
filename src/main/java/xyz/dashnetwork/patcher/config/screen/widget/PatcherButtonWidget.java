package xyz.dashnetwork.patcher.config.screen.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.ButtonWidget;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PatcherButtonWidget extends ButtonWidget {

    private final Supplier<String> label;
    private final String tooltip;
    private final Consumer<? super PatcherButtonWidget> clickEvent;
    private long mouseStillTime = 0;
    private int lastMouseX = -1;
    private int lastMouseY = -1;
    private boolean pressed = false;

    public PatcherButtonWidget(int id, Supplier<String> label, String tooltip, Consumer<? super PatcherButtonWidget> clickEvent) {
        super(id, 0, 0, 150, 20, label.get());
        this.label = label;
        this.tooltip = tooltip;
        this.clickEvent = clickEvent;
    }

    public String getTooltip() {
        return tooltip;
    }

    protected void onClick() {
        clickEvent.accept(this);
        message = label.get();
    }

    @Override
    public boolean mouseClicked(Minecraft minecraft, int mouseX, int mouseY) {
        boolean clicked = super.mouseClicked(minecraft, mouseX, mouseY);

        if (clicked && !pressed) {
            onClick();
            pressed = true;
        }

        return clicked;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        super.mouseReleased(mouseX, mouseY);
        pressed = false;
    }

}
