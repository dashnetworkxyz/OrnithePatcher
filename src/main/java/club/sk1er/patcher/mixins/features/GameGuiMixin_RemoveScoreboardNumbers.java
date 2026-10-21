package club.sk1er.patcher.mixins.features;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GameGui;
import net.minecraft.client.render.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(GameGui.class)
public class GameGuiMixin_RemoveScoreboardNumbers {

    @WrapOperation(method = "renderScoreboardObjective", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TextRenderer;draw(Ljava/lang/String;III)I", ordinal = 1))
    private int cancelScoreDraw(TextRenderer renderer, String text, int x, int y, int color, Operation<Integer> original) {
        if (Patcher.get().config().options().scoreboardNumbers.get())
            return original.call(renderer, text, x, y, color);

        return 0;
    }

    @ModifyArg(method = "renderScoreboardObjective", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TextRenderer;getWidth(Ljava/lang/String;)I", ordinal = 1), index = 0)
    private String adjustTextMeasure(String text) {
        if (Patcher.get().config().options().scoreboardNumbers.get())
            return text;

        return text.substring(0, text.lastIndexOf(": "));
    }

    @ModifyArg(method = "renderScoreboardObjective", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TextRenderer;draw(Ljava/lang/String;III)I"), index = 1)
    private int adjustTextPos(int x) {
        if (Patcher.get().config().options().scoreboardNumbers.get())
            return x;

        return x + 1;
    }

    @ModifyArg(method = "renderScoreboardObjective", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GameGui;fill(IIIII)V"), index = 0)
    private int adjustBackgroundLength(int x) {
        if (Patcher.get().config().options().scoreboardNumbers.get())
            return x;

        return x + 1;
    }

}
