package xyz.dashnetwork.patcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.dashnetwork.patcher.config.PatcherConfig;

import java.io.File;

public class Patcher {

    private static Patcher instance;
    private static Logger logger;
    private static PatcherConfig config;

    public static Patcher get() { return instance; }

    public Logger logger() { return logger; }

    public PatcherConfig config() { return config; }

    public Patcher(File gameDirectory) {
        instance = this;

        logger = LogManager.getLogger("Ornithe Patcher");

        config = new PatcherConfig(gameDirectory);
        config.load();
    }

}
