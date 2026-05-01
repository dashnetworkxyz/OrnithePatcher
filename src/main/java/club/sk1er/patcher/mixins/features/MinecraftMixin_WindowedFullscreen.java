package club.sk1er.patcher.mixins.features;

import club.sk1er.patcher.mixins.accessors.MinecraftAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Utils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.Patcher;

import java.awt.*;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin_WindowedFullscreen {

    @Shadow public Screen screen;
    @Shadow public int width;
    @Shadow public int height;
    @Shadow private boolean fullscreen;

    @Shadow
    protected abstract void resize(int width, int height);

    @Shadow
    public abstract void updateDisplay();

    @Inject(method = "toggleFullscreen", at = @At("HEAD"), cancellable = true)
    private void patcher$windowedFullscreen(CallbackInfo ci) {
        if (Patcher.get().config().options().windowedFullscreen.get() && Utils.getOS() == Utils.OS.WINDOWS) {
            ci.cancel();

            MinecraftAccessor accessor = (MinecraftAccessor) this;
            accessor.setFullscreen(!fullscreen);

            boolean grabbed = Mouse.isGrabbed();

            if (grabbed)
                Mouse.setGrabbed(false);

            try {
                DisplayMode displayMode = Display.getDesktopDisplayMode();

                if (fullscreen) {
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

                width = displayMode.getWidth();
                height = displayMode.getHeight();

                if (screen == null)
                    accessor.invokeOnResolutionChanged();
                else
                    resize(width, height);

                updateDisplay();
                Mouse.setCursorPosition((Display.getX() + Display.getWidth()) >> 1, (Display.getY() + Display.getHeight()) >> 1);

                if (grabbed)
                    Mouse.setGrabbed(true);

                Display.setResizable(false);
                Display.setResizable(true);
                Display.setResizable(!fullscreen);
            } catch (LWJGLException exception) {
                Patcher.get().logger().error("Failed to toggle fullscreen.", exception);
            }
        }
    }

}
