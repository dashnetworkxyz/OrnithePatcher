package club.sk1er.patcher.hooks;

import club.sk1er.patcher.mixins.accessors.MinecraftAccessor;
import net.minecraft.client.Minecraft;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import xyz.dashnetwork.patcher.Patcher;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.reflect.Method;
import java.nio.IntBuffer;

public class MinecraftHook {

    public static void test() {
        try {
            Class<?> glfw = Class.forName("org.lwjgl.glfw.GLFW");

            Method getHandle = Display.class.getMethod("getHandle");
            long window = (long) getHandle.invoke(null);

            Method glfwGetPrimaryMonitor = glfw.getMethod("glfwGetPrimaryMonitor");
            long monitor = (long) glfwGetPrimaryMonitor.invoke(null);

            IntBuffer mx = BufferUtils.createIntBuffer(1);
            IntBuffer my = BufferUtils.createIntBuffer(1);

            Method glfwGetMonitorPos = glfw.getMethod("glfwGetMonitorPos", long.class, IntBuffer.class, IntBuffer.class);
            glfwGetMonitorPos.invoke(null, monitor, mx, my);

            Method glfwGetVideoMode = glfw.getMethod("glfwGetVideoMode", long.class);
            Object mode = glfwGetVideoMode.invoke(null, monitor);

            Method glfwHideWindow = glfw.getMethod("glfwHideWindow", long.class);
            glfwHideWindow.invoke(null, window);

            Method glfwSetWindowAttrib = glfw.getMethod("glfwSetWindowAttrib", long.class, int.class, int.class);
            glfwSetWindowAttrib.invoke(null, window, 131077, 0);

            Method glfwSetWindowPos = glfw.getMethod("glfwSetWindowPos", long.class, int.class, int.class);
            glfwSetWindowPos.invoke(null, window, mx.get(0), my.get(0));

            Method width = mode.getClass().getMethod("width");
            Method height = mode.getClass().getMethod("height");

            Method glfwSetWindowSize = glfw.getMethod("glfwSetWindowSize", long.class, int.class, int.class);
            glfwSetWindowSize.invoke(null, window, (int) width.invoke(mode), (int) height.invoke(mode));

            Method glfwShowWindow = glfw.getMethod("glfwShowWindow", long.class);
            glfwShowWindow.invoke(null, window);

            Method glfwMakeContextCurrent = glfw.getMethod("glfwMakeContextCurrent", long.class);
            glfwMakeContextCurrent.invoke(null, window);
        } catch (ReflectiveOperationException exception) {
            exception.printStackTrace();
        }
    }

    public static boolean toggleBorderlessFullscreen(Minecraft minecraft) {
        try {
            Display.class.getMethod("setLocation", int.class, int.class);
        } catch (NoSuchMethodException exception) {
            Patcher.get().logger().info("Cannot use borderless fullscreen. This feature requires LWJGL2.");
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
