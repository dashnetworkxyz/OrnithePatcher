package xyz.dashnetwork.patcher.config.screen.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.util.math.MathHelper;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class PatcherSliderWidget extends PatcherButtonWidget {

    private final int segments;
    private float value;
    private boolean dragging;

    @SuppressWarnings("unchecked")
    public PatcherSliderWidget(int id, Supplier<String> label, String description, int segments, int initial, Consumer<? super PatcherSliderWidget> clickEvent) {
        super(id, label, description, (Consumer<? super PatcherButtonWidget>) clickEvent);
        this.segments = Math.max(2, segments);
        this.value = initial;

        segmentValue();
    }

    public int getSegment() {
        return MathHelper.clamp(Math.round(value * (segments - 1)), 0, segments - 1);
    }

    private void segmentValue() {
        value = (float) getSegment() / (float) (segments - 1);
    }

    @Override
    protected void onClick() {
        segmentValue();
        super.onClick();
    }

    @Override
    protected int getYImage(boolean hovered) {
        return 0;
    }

    @Override
    protected void renderBackground(Minecraft minecraft, int mouseX, int mouseY) {
        if (!visible)
            return;

        if (dragging) {
            this.value = (float) (mouseX - (x + 4)) / (float) (this.width - 8);
            this.value = MathHelper.clamp(value, 0.0F, 1.0F);
            onClick();
        }

        minecraft.getTextureManager().bind(WIDGETS_LOCATION);
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        drawTexture(x + (int) (value * (float) (width - 8)), y, 0, 66, 4, 20);
        drawTexture(x + (int) (value * (float) (width - 8)) + 4, y, 196, 66, 4, 20);
    }

    @Override
    public boolean mouseClicked(Minecraft minecraft, int mouseX, int mouseY) {
        if (super.mouseClicked(minecraft, mouseX, mouseY)) {
            this.value = (float) (mouseX - (x + 4)) / (float) (width - 8);
            this.value = MathHelper.clamp(value, 0.0F, 1.0F);
            this.dragging = true;
            return true;
        }

        return false;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.dragging = false;
    }

}
