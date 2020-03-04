package terrainpaint.processor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import terrainpaint.Main;

import java.util.List;

public class CustomTerraProcessor {
    public static void processCustom(Location eye, int radius, Player player,
                                     String presetname){
        FileConfiguration config = Main.getCustomPresets();
        List<String> sBlocks = config.getStringList(presetname+".block");
        List<String> sReplacements = config.getStringList(presetname+".replacement");
        if (sBlocks == null || sReplacements == null || sBlocks.size() != sReplacements.size()) {
            player.sendMessage("§9TerraPaint§8:§c We are sorry, something went wrong.");
            return;
        }
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInst(), new Runnable() {
            @Override
            public void run() {
                double startMS = System.nanoTime();
                Block b;
                for (int i = 0; i < sBlocks.size(); i ++){
                    int r = radius;
                    for(int x = (r * -1); x <= r; x++) {
                        for (int y = (r * -1); y <= r; y++) {
                            for (int z = (r * -1); z <= r; z++) {
                                // Grab the current block
                                b = eye.getWorld().getBlockAt(eye.getBlockX() + x, eye.getBlockY() + y, eye.getBlockZ() + z);
                                if (sBlocks.contains(b.getType().name().toUpperCase())) {
                                    int index = sBlocks.indexOf(b.getType().name().toUpperCase());
                                    b.setType(Material.valueOf(sReplacements.get(index)));
                                }
                            }
                        }
                    }
                }
                double elapsed = System.nanoTime() - startMS;
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING,100, 1);
                player.sendMessage("§9TerraPaint§8:§a Done ("+((int)elapsed / 1000000)+"ms).");
                b = null;
            }
        }, 0L);
    }
}
