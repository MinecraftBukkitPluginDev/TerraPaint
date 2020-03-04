package terrainpaint.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Prep {
    public static void prepConfig(Plugin plugin){
        FileConfiguration config = plugin.getConfig();

        if (config.get("max-radius") == null) {
            config.set("max-radius", "100");
            plugin.saveConfig();
        }
    }
}
