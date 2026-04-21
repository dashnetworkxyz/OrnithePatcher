package xyz.dashnetwork.patcher.hooks;

import net.minecraft.client.render.vertex.BufferBuilder;
import net.minecraft.client.render.vertex.DefaultVertexFormat;
import net.minecraft.client.render.vertex.Tesselator;
import net.minecraft.util.math.Box;
import org.lwjgl.opengl.GL11;

public class WorldRendererHook {

    public static void renderSolidShape(Box shape, int r, int g, int b, int a) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buf = tesselator.getBuffer();

        buf.begin(GL11.GL_QUADS, DefaultVertexFormat.POSITION_COLOR);
        buf.vertex(shape.minX, shape.minY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.minY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.minY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.minY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.maxY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.maxY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.maxY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.maxY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.minY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.maxY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.maxY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.minY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.minY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.minY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.maxY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.maxY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.minY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.minY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.maxY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.minX, shape.maxY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.minY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.maxY, shape.minZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.maxY, shape.maxZ).color(r,g,b,a).nextVertex();
        buf.vertex(shape.maxX, shape.minY, shape.maxZ).color(r,g,b,a).nextVertex();
        tesselator.end();
    }

}
