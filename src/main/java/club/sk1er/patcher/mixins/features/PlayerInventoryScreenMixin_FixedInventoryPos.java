package club.sk1er.patcher.mixins.features;

import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import net.minecraft.client.gui.screen.inventory.menu.PlayerInventoryScreen;
import net.minecraft.inventory.menu.InventoryMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.dashnetwork.patcher.Patcher;

@Mixin(PlayerInventoryScreen.class)
public abstract class PlayerInventoryScreenMixin_FixedInventoryPos extends InventoryMenuScreen {

    public PlayerInventoryScreenMixin_FixedInventoryPos(InventoryMenu menu) {
        super(menu);
    }

    @Inject(method = "checkStatusEffects", at = @At("TAIL"))
    private void patcher$setX(CallbackInfo ci) {
        if (Patcher.get().config().options().fixedInventoryPosition.get())
            this.x = (this.width - this.backgroundWidth) / 2;
    }

}
