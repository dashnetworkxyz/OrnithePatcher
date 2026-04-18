package xyz.dashnetwork.patcher.mixins.twitch;

import net.minecraft.client.options.KeyBinding;
import net.minecraft.util.Int2ObjectHashMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Set;

@Mixin(KeyBinding.class)
public class KeyBindingMixin_DisableTwitch<T> {

    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z")
    )
    private boolean patcher$skipTwitchCategoryList(List<T> instance, T object) {
        if (!object.equals("key.categories.stream"))
            return instance.add(object);

        return false;
    }

    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Int2ObjectHashMap;put(ILjava/lang/Object;)V")
    )
    private void patcher$skipTwitchCategoryMap(Int2ObjectHashMap<T> instance, int key, T value) {
        if (!value.equals("key.categories.stream"))
            instance.put(key, value);
    }

    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z")
    )
    private boolean patcher$skipTwitchCategorySet(Set<T> instance, T object) {
        if (!object.equals("key.categories.stream"))
            return instance.add(object);

        return false;
    }

}
