package club.sk1er.patcher.mixins.bugfixes.crashes;

import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(GameOptions.class)
public class GameOptionsMixin_ResolveCrash {

    /**
     * @author asbyth
     * @reason Resolve Chat Key bound to a Unicode char causing crashes while creative inventory is opened (MC-102867)
     */
    @Overwrite
    public static boolean isPressed(KeyBinding keyBinding) {
        int keyCode = keyBinding.getKeyCode();

        if (keyCode != 0 && keyCode < 256)
            return keyCode < 0 ? Mouse.isButtonDown(keyCode + 100) : Keyboard.isKeyDown(keyCode);

        return false;
    }

}
