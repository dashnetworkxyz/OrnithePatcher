package xyz.dashnetwork.ornithepatcher;

import net.minecraft.client.options.KeyBinding;
import net.ornithemc.osl.keybinds.api.KeyBindingEvents;
import net.ornithemc.osl.lifecycle.api.client.ClientWorldEvents;

import java.util.HashMap;
import java.util.Map;

public class KeyBindings {

    private final Map<KeyBinding, Runnable> keyBindings = new HashMap<>();

    public void add(KeyBinding keyBinding, Runnable runnable) {
        keyBindings.put(keyBinding, runnable);
    }

    public void registerAll() {
        KeyBindingEvents.REGISTER_KEYBINDS.register(registry -> {
            for (KeyBinding keyBinding : keyBindings.keySet()) {
                registry.register(keyBinding);
            }
        });

        ClientWorldEvents.TICK_END.register(world -> keyBindings.forEach((key, value) -> {
            while (key.consumeClick()) {
                value.run();
            }
        }));
    }

}
