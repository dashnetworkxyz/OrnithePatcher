package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.GameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameGui.class)
public class GameGuiMixin_ScoreboardTextTransparency {

    @ModifyConstant(method = "renderScoreboardObjective", constant = @Constant(intValue = 553648127))
    private int patcher$fixTextBlending(int original) {
        return -1;
    }

}
