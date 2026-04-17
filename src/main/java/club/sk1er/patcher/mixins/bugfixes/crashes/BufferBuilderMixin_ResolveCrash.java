package club.sk1er.patcher.mixins.bugfixes.crashes;

import net.minecraft.client.render.vertex.BufferBuilder;
import net.minecraft.client.render.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.IntBuffer;

@Mixin(BufferBuilder.class)
public class BufferBuilderMixin_ResolveCrash {

    @Shadow private IntBuffer intBuffer;
    @Shadow private VertexFormat format;

    @Inject(
            method = "end",
            at = @At(value = "INVOKE", target = "Ljava/nio/ByteBuffer;limit(I)Ljava/nio/Buffer;", remap = false)
    )
    private void patcher$resetBuffer(CallbackInfo ci) {
        intBuffer.position(0);
    }

    @Inject(method = "nextVertex", at = @At("HEAD"))
    private void patcher$adjustBuffer(CallbackInfo ci) {
        intBuffer.position(intBuffer.position() + format.getIntSize());
    }

}
