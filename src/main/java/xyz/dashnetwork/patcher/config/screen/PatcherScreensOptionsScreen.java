package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import xyz.dashnetwork.patcher.Patcher;
import xyz.dashnetwork.patcher.config.ConfigOptions;

public class PatcherScreensOptionsScreen extends AbstractOptionsScreen {

    public PatcherScreensOptionsScreen(Screen parent) {
        super("Patcher Screen Settings", parent);
        final ConfigOptions options = Patcher.get().config().options();

        addToggle("Remove Water Overlay", """
                        Remove the water texture overlay
                        when underwater.
                        """, options.removeWaterOverlay);
        addToggle("Fixed Inventory Pos", """
                        Stop potion effects from shifting your
                        inventory to the right.
                        """, options.fixedInventoryPosition);
        addToggle("Extended Chat Length", """
                        Extended Chat Length
                         ON - Always use extended chat length
                         AUTO - Only use on servers known to
                             support this and singleplayer
                         OFF - Use regular 100 chat length
                        Uses the 1.11 chat length of 256.
                        Servers may kick you for using this even if
                        they support 1.11+
                        """, options.extendedChatLength, "OFF", "Auto", "ON");
    }

}
