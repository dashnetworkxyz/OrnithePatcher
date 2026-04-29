package xyz.dashnetwork.patcher.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import xyz.dashnetwork.patcher.Patcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class PatcherConfig {

    private final File file;
    private final Gson gson;
    private ConfigOptions options;

    public PatcherConfig(File directory) {
        file = new File(directory, "optionspatcher.json");
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public ConfigOptions options() {
        return options;
    }

    public void load() {
        if (file.exists()) {
            try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {
                options = gson.fromJson(reader, ConfigOptions.class);
            } catch (IOException exception) {
                Patcher.get().logger().error("Failed to load options", exception);
            }
        }

        if (options == null)
            options = new ConfigOptions();
    }

    public void save() {
        if (options == null)
            return;

        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            gson.toJson(options, writer);
        } catch (IOException exception) {
            Patcher.get().logger().error("Failed to save options", exception);
        }
    }

}
