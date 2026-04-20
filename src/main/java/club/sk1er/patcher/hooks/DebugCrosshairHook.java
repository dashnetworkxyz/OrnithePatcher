package club.sk1er.patcher.hooks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Window;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.render.vertex.BufferBuilder;
import net.minecraft.client.render.vertex.DefaultVertexFormat;
import net.minecraft.client.render.vertex.Tesselator;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class DebugCrosshairHook {

    public static void renderDirections(float partialTicks, Minecraft minecraft) {
        Window window = new Window(minecraft);
        double scale = window.getScale();
        Entity camera = minecraft.getCamera();
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuffer();

        GlStateManager.pushMatrix();
        GlStateManager.translatef((float)(window.getScaledWidth() / 2), (float)(window.getScaledHeight() / 2), 0);
        GlStateManager.rotatef(camera.lastPitch + (camera.pitch - camera.lastPitch) * partialTicks, -1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(camera.lastYaw + (camera.yaw - camera.lastYaw) * partialTicks, 0.0F, 1.0F, 0.0F);
        GlStateManager.scalef(-1.0F, -1.0F, -1.0F);
        GlStateManager.scaled(1 / scale, 1 / scale, 1 / scale);
        GlStateManager.disableTexture();
        GlStateManager.depthMask(false);

        GL11.glLineWidth(2.0F);
        buffer.begin(1, DefaultVertexFormat.POSITION_COLOR);
        drawLine(buffer, 38, Color.RED, 1, 0, 0);
        drawLine(buffer, 38, Color.BLUE, 0, 0, 1);
        drawLine(buffer, 27, Color.GREEN, 0, 1, 0);
        tesselator.end();

        GL11.glLineWidth(1.0F);
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture();
        GlStateManager.popMatrix();
    }

    private static void drawLine(BufferBuilder buffer, double length, Color color, int x, int y, int z) {
        buffer.vertex(0, 0, 0).color(color.getRed(), color.getGreen(), color.getBlue(), 255).nextVertex();
        buffer.vertex(length * x, length * y, length * z).color(color.getRed(), color.getGreen(), color.getBlue(), 255).nextVertex();
    }

}
