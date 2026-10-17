package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.ChatGui;
import net.minecraft.client.render.Window;
import net.minecraft.text.Text;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ChatGui.class)
public abstract class ChatGuiMixin_ChatComponentBoundary {

    @Shadow @Final
    private Minecraft minecraft;

    @Shadow
    public abstract int getVisibleLineCount();

    @Inject(
            method = "getMessageAt",
            at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/chat/ChatGui;scroll:I", opcode = Opcodes.GETFIELD),
            cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void patcher$stopEventsOutsideWindow(int x, int y, CallbackInfoReturnable<Text> cir, Window window, int i, float f, int j, int k, int l) {
        int line = k / minecraft.textRenderer.fontHeight;

        if (line >= getVisibleLineCount())
            cir.setReturnValue(null);
    }

}
