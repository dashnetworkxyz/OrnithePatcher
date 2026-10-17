package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.locale.I18n;

public class PatcherScreensOptionsScreen extends AbstractOptionsScreen {

    public PatcherScreensOptionsScreen(Screen parent) {
        super("Patcher Screen Settings", parent);

        addToggle("Remove Water Overlay", """
                Remove the water texture overlay
                when underwater.
                """, options().removeWaterOverlay);
        addToggle("Fixed Inventory Pos", """
                Stop potion effects from shifting your
                inventory to the right.
                """, options().fixedInventoryPosition);
        addToggle("Extended Chat Length", """
                Extended Chat Length
                ON - Always use extended chat length
                Auto - Only use on servers known to
                    support this and singleplayer
                OFF - Use regular 100 chat length
                Uses the 1.11 chat length of 256.
                Servers may kick you for using this even if
                they support 1.11+
                """, options().extendedChatLength,
                I18n.translate("options.off"),
                I18n.translate("options.guiScale.auto"),
                I18n.translate("options.on"));
        addToggle("Disable Toasts", """
                Remove achievement notifications.
                """, options().disableToast);
        addToggle("Remove Chat Log Limit", """
                Remove the limit on how many
                messages can show up in chat.
                """, options().removeChatLogLimit);
    }

}
