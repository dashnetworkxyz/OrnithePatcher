package club.sk1er.patcher.mixins.performance.network.packet;

import net.minecraft.client.network.handler.ClientPlayPacketHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomPayloadS2CPacket.class)
public class CustomPayloadS2CPacketMixin_MemoryLeak {

    @Shadow
    private PacketByteBuf data;

    @Inject(
            method = "handle(Lnet/minecraft/client/network/handler/ClientPlayPacketHandler;)V",
            at = @At("TAIL")
    )
    private void patcher$releaseData(ClientPlayPacketHandler handler, CallbackInfo ci) {
        if (data != null)
            data.release();
    }

}
