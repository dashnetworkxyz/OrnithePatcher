package xyz.dashnetwork.ornithepatcher;

import net.ornithemc.osl.config.api.ConfigScope;
import net.ornithemc.osl.config.api.LoadingPhase;
import net.ornithemc.osl.config.api.config.BaseConfig;
import net.ornithemc.osl.config.api.serdes.FileSerializerType;
import net.ornithemc.osl.config.api.serdes.SerializerTypes;

public class PatcherConfig extends BaseConfig {

    @Override
    public void init() {
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
