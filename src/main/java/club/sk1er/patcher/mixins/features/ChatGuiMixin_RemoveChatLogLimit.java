package club.sk1er.patcher.mixins.features;

import net.minecraft.client.gui.chat.ChatGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(ChatGui.class)
public class ChatGuiMixin_RemoveChatLogLimit {

    @ModifyConstant(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", constant = @Constant(intValue = 100))
    private int patcher$removeLogLimit(int original) {
        if (Patcher.get().config().options().removeChatLogLimit.get())
            return Integer.MAX_VALUE;

        return original;
    }

}
