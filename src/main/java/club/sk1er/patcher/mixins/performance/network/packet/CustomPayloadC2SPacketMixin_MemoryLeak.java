package club.sk1er.patcher.mixins.performance.network.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.server.network.handler.ServerPlayPacketHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomPayloadC2SPacket.class)
public class CustomPayloadC2SPacketMixin_MemoryLeak {

    @Shadow
    private PacketByteBuf data;

    @Inject(
            method = "handle(Lnet/minecraft/server/network/handler/ServerPlayPacketHandler;)V",
            at = @At("TAIL")
    )
    private void patcher$releaseData(ServerPlayPacketHandler handler, CallbackInfo ci) {
        if (data != null)
            data.release();
    }

}
