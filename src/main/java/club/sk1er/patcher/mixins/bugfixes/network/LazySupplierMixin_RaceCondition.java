package club.sk1er.patcher.mixins.bugfixes.network;

import net.minecraft.util.LazySupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LazySupplier.class)
public abstract class LazySupplierMixin_RaceCondition<T> {

    @Shadow private boolean loaded;
    @Shadow private T value;

    @Shadow
    protected abstract T load();

    /**
     * @author LlamaLad7
     * @reason Fix race condition
     */
    @Overwrite
    public T get() {
        //noinspection DoubleCheckedLocking
        if (!loaded) {
            synchronized (this) {
                if (!loaded) {
                    value = load();
                    loaded = true;
                }
            }
        }

        return value;
    }

}
