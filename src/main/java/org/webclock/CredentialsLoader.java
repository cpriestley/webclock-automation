package org.webclock;

import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class CredentialsLoader {


    private CredentialsLoader() {
    }

    public static Map<String, String> loadCredentials() {
        Yaml yaml = new Yaml();
        try (InputStream in = CredentialsLoader.class.getClassLoader().getResourceAsStream("credentials.yaml")) {
            if (in == null) {
                throw new FileNotFoundException("credentials.yaml not found in resources!");
            }
            Map<?, ?> obj = yaml.load(in); // raw map
            Map<?, ?> rawCreds = (Map<?, ?>) obj.get("credentials");

            // Convert to typed map safely
            Map<String, String> creds = new HashMap<>();
            for (Map.Entry<?, ?> entry : rawCreds.entrySet()) {
                creds.put(entry.getKey().toString(), entry.getValue().toString());
            }

            return creds;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load credentials", e);
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = loadCredentials();
        System.out.println(map);
    }

}
