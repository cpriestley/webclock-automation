package org.webclock;

import org.webclock.automation.WebClockBot;
import org.webclock.config.ConfigLoader;

public class Main {

    public static void main(String[] args) {

        // Default config path
        String configPath = "config.yaml";

        // If user provided a path, use it
        if (args.length > 0) {
            configPath = args[0];
        }

        WebClockBot bot = new WebClockBot(ConfigLoader.load(configPath));
        bot.run();
    }
}