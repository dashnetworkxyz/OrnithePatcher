package club.sk1er.patcher.mixins.bugfixes.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.resource.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin_SpectatorShader {

    @Shadow
    private Minecraft minecraft;

    @Shadow
    protected abstract void loadShader(Identifier location);

    @Redirect(
            method = "updateShader",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;loadShader(Lnet/minecraft/resource/Identifier;)V")
    )
    private void patcher$fixSpectatorShader(GameRenderer renderer, Identifier identifier) {
        if (minecraft.options.perspective == 0)
            loadShader(identifier);
    }

}
