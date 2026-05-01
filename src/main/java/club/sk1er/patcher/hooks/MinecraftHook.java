package club.sk1er.patcher.hooks;

import club.sk1er.patcher.mixins.accessors.MinecraftAccessor;
import net.minecraft.client.Minecraft;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import xyz.dashnetwork.patcher.Patcher;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MinecraftHook {

    public static boolean toggleBorderlessFullscreen(Minecraft minecraft) {
        try {
            Display.class.getMethod("setLocation", int.class, int.class);
        } catch (NoSuchMethodException exception) {
            Patcher.get().logger().warn("Failed to toggle borderless fullscreen. LWJGL2 is required.");
            return false;
        }

        MinecraftAccessor accessor = (MinecraftAccessor) minecraft;
        accessor.setFullscreen(!minecraft.isFullscreen());

        boolean grabbed = Mouse.isGrabbed();

        if (grabbed)
            Mouse.setGrabbed(false);

        try {
            DisplayMode displayMode = Display.getDesktopDisplayMode();

            if (minecraft.isFullscreen()) {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
                Display.setResizable(false);
                Display.setDisplayMode(displayMode);
                Display.setLocation(0, 0);
            } else {
                System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
                displayMode = new DisplayMode(accessor.getInitWidth(), accessor.getInitHeight());
                Display.setDisplayMode(displayMode);
                Display.setResizable(false);
                Display.setResizable(true);

                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) (dimension.getWidth() - Display.getWidth()) / 2;
                int y = (int) (dimension.getHeight() - Display.getHeight()) / 2;
                Display.setLocation(x, y);
            }

            Display.setFullscreen(false);

            minecraft.width = displayMode.getWidth();
            minecraft.height = displayMode.getHeight();

            System.out.println("WIDTH: " + Display.getWidth() + " HEIGHT: " + Display.getHeight());

            if (minecraft.screen == null)
                accessor.invokeOnResolutionChanged();
            else
                minecraft.resize(minecraft.width, minecraft.height);

            minecraft.updateDisplay();
            Mouse.setCursorPosition((Display.getX() + Display.getWidth()) >> 1, (Display.getY() + Display.getHeight()) >> 1);
            Display.setResizable(!minecraft.isFullscreen());
        } catch (LWJGLException exception) {
            Patcher.get().logger().error("Failed to toggle fullscreen.", exception);
            return false;
        }

        if (grabbed)
            Mouse.setGrabbed(true);

        return true;
    }

}
