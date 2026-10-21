package xyz.dashnetwork.patcher.config.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.locale.I18n;

public class PatcherGUIOptionsScreen extends AbstractOptionsScreen {

    public PatcherGUIOptionsScreen(Screen parent) {
        super("Patcher GUI Settings", parent);

        addToggle("Remove Water Overlay", """
                Remove the water texture overlay
                when underwater.
                """, options().removeWaterOverlay);
        addToggle("Fixed Inventory Pos", """
                Stop potion effects from shifting your
                inventory to the right.
                """, options().fixedInventoryPosition);
        addToggle("Extend Chat Length", """
                Extends the chat length to 256
                
                ON - Always use extended chat length
                Auto - Only use on servers known to
                    support this and singleplayer
                OFF - Use regular 100 chat length
                
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
                Remove the limit on how many messages can
                show up in chat.
                """, options().removeChatLogLimit);
        addToggle("Scoreboard Numbers", """
                Toggles the red numbers on the right side
                of the scoreboard.
                """, options().scoreboardNumbers);
    }

}
