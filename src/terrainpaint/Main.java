package terrainpaint;

import com.sun.scenario.Settings;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import terrainpaint.commands.PaintCommand;
import terrainpaint.commands.SettingsCommand;
import terrainpaint.utils.Prep;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    static Main instance;
    static FileConfiguration custom_presets;
    static File custom_presets_file;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        instance = this;

        setup();

        Prep.prepConfig(this);

        System.out.println("Enabling TerraPaint");

        getCommand("terrapaint").setExecutor(new PaintCommand());
        getCommand("terrasettings").setExecutor(new SettingsCommand());

    }

    private void setup(){
        custom_presets_file = new File(getDataFolder(),"presets.yml");
        if (!custom_presets_file.exists()) {
            custom_presets_file.getParentFile().mkdirs();
            saveResource("presets.yml", false);
        }

        custom_presets = new YamlConfiguration();
        try {
            custom_presets.load(custom_presets_file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();

        System.out.println("Disabling TerraPaint");
    }

    public static Main getInst() {
        return instance;
    }

    public static FileConfiguration getCustomPresets(){
        return custom_presets;
    }
}
