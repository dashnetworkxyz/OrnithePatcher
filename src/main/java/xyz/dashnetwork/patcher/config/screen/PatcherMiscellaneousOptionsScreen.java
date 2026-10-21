package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.Utils;

public class PatcherMiscellaneousOptionsScreen extends AbstractOptionsScreen {

    public PatcherMiscellaneousOptionsScreen(Screen parent) {
        super("Patcher Miscellaneous Settings", parent);

        addToggle("Borderless Fullscreen", """
                Implement Borderless Fullscreen in Minecraft.
                This allows you to drag your mouse outside the window.
                
                Windows only and requires LWJGL2.
                Incompatible with legacy-lwjgl3
                """, Utils.getOS() == Utils.OS.WINDOWS, options().borderlessFullscreen);
        addToggle("F3N Keybind", """
                Toggles the backported F3+N keybind.
                Changes gamemode between spectator and creative.
                """, options().f3nKeybind);
    }

}
