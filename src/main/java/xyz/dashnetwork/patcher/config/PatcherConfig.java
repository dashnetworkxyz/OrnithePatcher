package xyz.dashnetwork.patcher.config;

import com.google.gson.Gson;
import xyz.dashnetwork.patcher.Patcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class PatcherConfig {

    private static File file;
    private static ConfigOptions options;

    public PatcherConfig(File directory) {
        file = new File(directory, "optionspatcher.json");
    }

    public ConfigOptions options() {
        return options;
    }

    public void load() {
        if (!file.exists()) {
            options = new ConfigOptions();
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
            options = new Gson().fromJson(reader, ConfigOptions.class);
        } catch (IOException exception) {
            Patcher.get().logger().error("Failed to load options", exception);
        }
    }

    public void save() {
        if (options == null)
            return;

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            new Gson().toJson(file.toPath(), writer);
        } catch (IOException exception) {
            Patcher.get().logger().error("Failed to save options", exception);
        }
    }

}
