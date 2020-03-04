package terrainpaint.commands;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import terrainpaint.Main;
import terrainpaint.processor.CustomTerraProcessor;
import terrainpaint.processor.TerraProcessor;

public class PaintCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("terrapaint")) {
            if (!(sender instanceof Player)){
                sender.sendMessage("§9TerraPaint§8:§c Sorry, you can't execute this command from the console.");
                return false;
            }
            if (sender.hasPermission("terrapaint.use")) {
                if (args.length != 2) {
                    sender.sendMessage(new String[]{
                            "§c/terrapaint ...",
                            "§c    SNOWY",
                            "§c    NUCLEAR_DISASTER",
                            "§c    RICH",
                            "§c    APOCALYPSE",
                            "§c    DRY",
                            "§c    NOOB",
                            "§c    NETHER",
                            "§c    <custom>",
                            "§c    ... <radius>"
                    });
                } else {
                    int radius = Integer.parseInt(args[1]);
                    int max_radius = Integer.parseInt(Main.getInst().getConfig().getString("max-radius"));
                    if (radius > max_radius) {
                        sender.sendMessage("§9TerraPaint§8:§c You are attempting to use a radius, that is higher, than the radius limit.");
                    } else {
                        Player p = (Player) sender;
                        FileConfiguration presets = Main.getCustomPresets();
                        if (presets.get("presets") != null) {
                            if (presets.getStringList("presets").contains(args[0])) {
                                CustomTerraProcessor.processCustom(p.getLocation(), radius, p, args[0]);
                                ((Player) sender).playSound(((Player) sender).getLocation(), Sound.BLOCK_NOTE_BASS, 100, 1);
                                sender.sendMessage("§9TerraPaint§8:§7 Processing ...");
                            }
                        }
                        ((Player)sender).playSound(((Player)sender) .getLocation(), Sound.BLOCK_NOTE_BASS, 100, 1);
                        sender.sendMessage("§9TerraPaint§8:§7 Processing ...");
                        TerraProcessor.process(p.getLocation(), radius, args[0], p);
                    }
                }
            } else {
                sender.sendMessage("§cPermissions denied.");
            }
        }
        return false;
    }
}
