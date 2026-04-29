package club.sk1er.patcher.mixins.performance.vanilla;

import me.jellysquid.mods.lithium.common.util.math.CompactSineLUT;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MathHelper.class)
public class MathHelperMixin_CompactLUT {

    @Mutable @Shadow @Final
    private static float[] SINE_TABLE;

    @SuppressWarnings("InstantiationOfUtilityClass")
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void clearSineTable(CallbackInfo ci) {
        new CompactSineLUT(); // Force class initialization
        SINE_TABLE = null;
    }

    /**
     * @author Wyvest
     * @reason Use a compact LUT for sine calculations
     */
    @Overwrite
    public static float sin(float f) {
        return CompactSineLUT.sin(f);
    }

    /**
     * @author Wyvest
     * @reason Use a compact LUT for cosine calculations
     */
    @Overwrite
    public static float cos(float f) {
        return CompactSineLUT.cos(f);
    }

}
