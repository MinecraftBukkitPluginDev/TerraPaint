package terrainpaint.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import terrainpaint.Main;

public class SettingsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("terrasettings")) {
            Plugin plugin = Main.getInst();
            FileConfiguration config = plugin.getConfig();
            if (sender.hasPermission("terrapaint.settings")) {
                if (args.length == 0) {
                    sender.sendMessage(new String[]{
                            "§c/terrasettings ...",
                            "§c    max-radius ... <int|value>"
                    });
                } else if (args.length == 1) {
                    if (config.get(args[0]) != null) {
                        Object value = config.get(args[0]);
                        sender.sendMessage("§6TerraSettings§8: §aThe current value of §7"+args[0]+"§a is §7"+value);
                    } else {
                        sender.sendMessage("§cThere is no such setting named §4"+args[0]+"§c.");
                    }
                } else if (args.length == 2) {
                    if (config.get(args[0]) != null) {
                        Object newValue = args[1];
                        config.set(args[0], args[1]);
                        plugin.saveConfig();
                        sender.sendMessage("§6TerraSettings§8: §aThe value of §7"+args[0]+"§a was changed to §7"+newValue);
                    } else {
                        sender.sendMessage("§cThere is no such setting named §4"+args[0]+"§c.");
                    }
                } else {
                    sender.sendMessage("§cThe arguments should be in range §40-2§c.");
                }
            }
        }
        return false;
    }
}
