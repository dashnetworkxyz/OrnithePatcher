package club.sk1er.patcher.mixins.performance;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIo;
import net.minecraft.world.chunk.storage.AnvilChunkStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.DataInputStream;
import java.io.IOException;

@Mixin(AnvilChunkStorage.class)
public class AnvilChunkStorageMixin_UnclosedStream {

    @Redirect(
            method = "loadChunk(Lnet/minecraft/world/World;II)Lnet/minecraft/world/chunk/WorldChunk;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtIo;read(Ljava/io/DataInputStream;)Lnet/minecraft/nbt/NbtCompound;")
    )
    private NbtCompound patcher$closeStream(DataInputStream stream) throws IOException {
        NbtCompound read = NbtIo.read(stream);
        stream.close();
        return read;
    }

}
