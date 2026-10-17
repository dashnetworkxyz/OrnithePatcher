package club.sk1er.patcher.mixins.features;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.options.ServerListEntry;
import net.minecraft.server.integrated.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.Patcher;

import java.util.Arrays;

@Mixin(ChatScreen.class)
public class ChatScreenMixin_ExtendedChatLength {

    @Shadow
    protected TextFieldWidget chatField;

    @Unique
    private final String[] KNOWN_SERVERS = new String[] { // TODO: User customizable
            "hypixel.net",
            "oc.tc",
            "dashnetwork.xyz"
    };

    @Inject(method = "init", at = @At("TAIL"))
    private void patcher$useExtendedChatLength(CallbackInfo ci) {
        int mode = Patcher.get().config().options().extendedChatLength.get();

        if (mode == 0)
            return;

        ServerListEntry entry = Minecraft.getInstance().getCurrentServerEntry();

        if (mode == 1 && entry != null) {
            final String address = entry.ip;

            if (Arrays.stream(KNOWN_SERVERS).noneMatch(
                    each -> address.equalsIgnoreCase(each) || address.toLowerCase().endsWith("." + each.toLowerCase())
            ))
                return;
        }

        chatField.setMaxLength(256);
    }

}
