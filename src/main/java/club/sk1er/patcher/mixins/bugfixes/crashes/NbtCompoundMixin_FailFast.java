package club.sk1er.patcher.mixins.bugfixes.crashes;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NbtCompound.class)
public class NbtCompoundMixin_FailFast {

    @Inject(method = "put", at = @At("HEAD"))
    private void patcher$failFast(String key, NbtElement element, CallbackInfo ci) {
        if (element == null)
            throw new IllegalArgumentException("Invalid null NBT value with key " + key);
    }

}
