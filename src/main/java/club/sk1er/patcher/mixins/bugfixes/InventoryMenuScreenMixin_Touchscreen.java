package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InventoryMenuScreen.class)
public class InventoryMenuScreenMixin_Touchscreen {

    @Redirect(
            method = "mouseClicked",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;openScreen(Lnet/minecraft/client/gui/screen/Screen;)V")
    )
    private void patcher$closeScreen(Minecraft minecraft, Screen screen) {
        minecraft.player.closeMenu();
    }

}
