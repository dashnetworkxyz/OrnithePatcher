package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.ChatMessage;
import net.minecraft.client.gui.chat.ChatGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChatGui.class)
public class ChatGuiMixin_NullChatLineFix {

    @Redirect(method = "removeMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ChatMessage;getId()I"))
    private int patcher$checkIfChatLineIsNull(ChatMessage message) {
        if (message == null)
            return -1;

        return message.getId();
    }

}
