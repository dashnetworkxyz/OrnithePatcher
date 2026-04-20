package club.sk1er.patcher.mixins.accessors;

import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MathHelper.class)
public interface MathHelperAccessor {

    @Accessor("SINE_TABLE")
    static float[] getSineTable() { throw new AssertionError(); }

}
