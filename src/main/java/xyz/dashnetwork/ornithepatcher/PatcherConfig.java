package xyz.dashnetwork.ornithepatcher;

import net.ornithemc.osl.config.api.ConfigManager;
import net.ornithemc.osl.config.api.ConfigScope;
import net.ornithemc.osl.config.api.LoadingPhase;
import net.ornithemc.osl.config.api.config.BaseConfig;
import net.ornithemc.osl.config.api.config.option.BooleanOption;
import net.ornithemc.osl.config.api.serdes.FileSerializerType;
import net.ornithemc.osl.config.api.serdes.SerializerTypes;

public class PatcherConfig extends BaseConfig {

    private static PatcherConfig instance;

    public static final BooleanOption FIXED_ALEX_ARMS;

    static {
        FIXED_ALEX_ARMS = new BooleanOption(
                "Alex Arm Position",
                "Resolve Alex-model arms being shifted down further than Steve-model arms.",
                true
        );
    }

    public static void initialize() {
        if (instance == null)
            ConfigManager.register(instance = new PatcherConfig());
    }

    @Override
    public void init() {
        registerOptions("Bug Fixes", FIXED_ALEX_ARMS);
    }

    @Override
    public String getNamespace() { return null; }

    @Override
    public String getName() { return "Ornithe Patcher"; }

    @Override
    public String getSaveName() { return "ornithe-patcher.json"; }

    @Override
    public ConfigScope getScope() { return ConfigScope.GLOBAL; }

    @Override
    public LoadingPhase getLoadingPhase() { return LoadingPhase.READY; }

    @Override
    public FileSerializerType<?> getType() { return SerializerTypes.JSON; }

    @Override
    public int getVersion() { return 0; }

}
