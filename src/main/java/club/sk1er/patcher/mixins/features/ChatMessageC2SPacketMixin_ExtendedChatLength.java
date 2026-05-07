package club.sk1er.patcher.mixins.features;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChatMessageC2SPacket.class)
public class ChatMessageC2SPacketMixin_ExtendedChatLength {

    @Redirect(
            method = "<init>(Ljava/lang/String;)V",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;length()I")
    )
    private int patcher$adjustLengthCheck(String string) {
        return string.length() - 156;
    }

}
