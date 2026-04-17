package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import net.minecraft.inventory.slot.InventorySlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenuScreen.class)
public abstract class InventoryMenuScreen_MouseBindFix extends Screen {

    @Shadow
    private InventorySlot hoveredSlot;

    @Shadow protected abstract void clickSlot(InventorySlot actualSlot, int slot, int clickData, int actionType);
    @Shadow protected abstract boolean moveHoveredSlotToHotbar(int key);

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    private void patcher$checkCloseClick(int mouseX, int mouseY, int button, CallbackInfo ci) {
        int keyCode = button - 100;

        if (keyCode == minecraft.options.inventoryKey.getKeyCode())
            minecraft.player.closeMenu();

        if (hoveredSlot != null && hoveredSlot.hasItem()) {
            if (keyCode == minecraft.options.pickItemKey.getKeyCode())
                clickSlot(hoveredSlot, hoveredSlot.index, 0, 3);
            else if (keyCode == minecraft.options.dropKey.getKeyCode())
                clickSlot(hoveredSlot, hoveredSlot.index, isControlDown() ? 1 : 0, 4);
        }
    }

    @Inject(method = "mouseClicked", at = @At("TAIL"))
    private void patcher$checkHotbarClicks(int mouseX, int mouseY, int button, CallbackInfo ci) {
        moveHoveredSlotToHotbar(button - 100);
    }

}
