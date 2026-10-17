package club.sk1er.patcher.mixins.bugfixes.render;

import net.minecraft.client.render.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextRenderer.class)
public abstract class TextRendererMixin_ResetStyles {

    @Shadow
    protected abstract void reset();

    @Inject(method = "draw(Ljava/lang/String;FFIZ)I",
            at = @At(
                    value = "INVOKE", target = "Lnet/minecraft/client/render/TextRenderer;drawLayer(Ljava/lang/String;FFIZ)I",
                    ordinal = 0, shift = At.Shift.AFTER
            )
    )
    private void patcher$resetStyle(CallbackInfoReturnable<Integer> cir) {
        this.reset();
    }
}
