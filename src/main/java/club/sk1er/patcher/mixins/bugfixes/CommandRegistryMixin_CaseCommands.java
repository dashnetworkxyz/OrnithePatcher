package club.sk1er.patcher.mixins.bugfixes;

import net.minecraft.server.command.handler.CommandRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Locale;

@Mixin(CommandRegistry.class)
public class CommandRegistryMixin_CaseCommands {

    @ModifyArg(
            method = {"run(Lnet/minecraft/server/command/source/CommandSource;Ljava/lang/String;)I", "getSuggestions"},
            at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;", remap = false)
    )
    private Object patcher$makeLowercaseForGet(Object object) {
        if (object instanceof String string)
            return string.toLowerCase(Locale.ENGLISH);

        return object;
    }

    @ModifyArg(
            method = "register",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", remap = false),
            index = 0
    )
    private Object patcher$makeLowercaseForPut(Object object) {
        if (object instanceof String string)
            return string.toLowerCase(Locale.ENGLISH);

        return object;
    }

    @ModifyArg(
            method = "getSuggestions",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/command/AbstractCommand;doesStringStartWith(Ljava/lang/String;Ljava/lang/String;)Z"),
            index = 0
    )
    private String patcher$makeLowercaseForSuggestions(String string) {
        return string != null ? string.toLowerCase(Locale.ENGLISH) : null;
    }

}
