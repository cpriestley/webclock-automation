package org.webclock.config;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.InputStream;

public class ConfigLoader {

    private ConfigLoader() {
    }

    public static Config load(String path) {
        Yaml yaml = new Yaml();
        try (InputStream input = new FileInputStream(path)) {
            return yaml.loadAs(input, Config.class);
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Failed to load configuration from %s", path), e);
        }
    }
}
