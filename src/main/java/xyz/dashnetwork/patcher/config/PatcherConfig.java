package xyz.dashnetwork.patcher.config;

import com.google.gson.Gson;
import xyz.dashnetwork.patcher.Patcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public final class PatcherConfig {

    private PatcherConfig() {}

    private static File file;
    private static ConfigOptions options;

    public static void init(File directory) {
        file = new File(directory, "optionspatcher.json");
    }

    public static ConfigOptions options() {
        return options;
    }

    public static void load() {
        if (!file.exists()) {
            options = new ConfigOptions();
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            options = new Gson().fromJson(reader, ConfigOptions.class);
        } catch (IOException exception) {
            Patcher.LOGGER.error("Failed to load options", exception);
        }
    }

    public static void save() {
        if (options == null)
            return;

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            new Gson().toJson(file.toPath(), writer);
        } catch (IOException exception) {
            Patcher.LOGGER.error("Failed to save options", exception);
        }
    }

}
