package club.sk1er.patcher.mixins.bugfixes.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenuScreen.class)
public class InventoryMenuScreenMixin_SplitRemnants extends Screen {

    @Shadow private int clickDragButton;
    @Shadow private int draggedItemRemainder;

    @Inject(method = "updateDraggedStackRemainder", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;copy()Lnet/minecraft/item/ItemStack;"), cancellable = true)
    private void patcher$fixRemnants(CallbackInfo ci) {
        if (this.clickDragButton == 2) {
            this.draggedItemRemainder = minecraft.player.inventory.getCursorItem().getMaxSize();
            ci.cancel();
        }
    }

}
