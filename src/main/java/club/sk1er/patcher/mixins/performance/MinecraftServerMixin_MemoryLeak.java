package club.sk1er.patcher.mixins.performance;

import io.netty.buffer.ByteBuf;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin_MemoryLeak {

    @ModifyVariable(
            method = "setStatus",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/ServerStatus;setFavicon(Ljava/lang/String;)V", shift = At.Shift.AFTER),
            ordinal = 1
    )
    private ByteBuf patcher$releaseByteBuf(ByteBuf buf) {
        buf.release();
        return buf;
    }

}
