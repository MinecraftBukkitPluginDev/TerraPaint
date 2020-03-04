package terrainpaint.processor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import terrainpaint.Main;

import java.util.Random;

public class TerraProcessor {
    public static void process(Location eye, int radius, String type, Player player){
        Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInst(), new Runnable() {
            @Override
            public void run() {
                double startMS = System.nanoTime();
                int info_block_analyzed = 0;
                int r = radius;
                Block b;
                for(int x = (r * -1); x <= r; x++) {
                    for(int y = (r * -1); y <= r; y++) {
                        for(int z = (r * -1); z <= r; z++) {
                            // Grab the current block
                            b = eye.getWorld().getBlockAt(eye.getBlockX() + x, eye.getBlockY() + y, eye.getBlockZ() + z);
                            info_block_analyzed ++;
                            if (type.equalsIgnoreCase("SNOWY")) {
                                b.setBiome(Biome.ICE_MOUNTAINS);
                                if (b.getType() == Material.GRASS || b.getType() == Material.DIRT)
                                    b.setType(Material.SNOW_BLOCK);
                                if (b.getType() == Material.WATER || b.getType() == Material.STATIONARY_WATER)
                                    b.setType(Material.PACKED_ICE);
                                if (b.getType() == Material.LONG_GRASS)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.LOG || b.getType() == Material.LOG_2)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.VINE)
                                    b.setType(Material.AIR);
                            } else if (type.equalsIgnoreCase("NUCLEAR_DISASTER")) {
                                Random rand = new Random();
                                Material random_material;
                                int random_index;
                                Material[] materials =
                                        {
                                                Material.COBBLESTONE,
                                                Material.GRAVEL,
                                                Material.DIRT
                                        };
                                b.setBiome(Biome.HELL);
                                if (b.getType() == Material.GRAVEL || b.getType() == Material.SAND)
                                    b.setType(Material.LAVA);
                                if (b.getType() == Material.DIRT || b.getType() == Material.GRASS){
                                    random_index = rand.nextInt(2);
                                    random_material = materials[random_index];
                                    b.setType(random_material);
                                }
                                if (b.getType() == Material.WATER || b.getType() == Material.STATIONARY_WATER || b.getType() == Material.ICE || b.getType() == Material.PACKED_ICE)
                                    b.setType(Material.LAVA);
                                if (b.getType() == Material.LONG_GRASS)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.LOG || b.getType() == Material.LOG_2)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.VINE)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.OBSIDIAN)
                                    b.setType(Material.LAVA);
                            } else if (type.equalsIgnoreCase("RICH")) {
                                b.setBiome(Biome.SKY);
                                if (b.getType() == Material.LOG || b.getType() == Material.LOG_2) {
                                    b.setType(Material.LOG);
                                } else if (b.getType() != Material.AIR){
                                    b.setType(Material.DIAMOND_BLOCK);
                                }
                            }  else if (type.equalsIgnoreCase("APOCALYPSE")) {
                                b.setBiome(Biome.MESA);
                                if (b.getType() == Material.LONG_GRASS)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.LOG || b.getType() == Material.LOG_2)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.VINE)
                                    b.setType(Material.AIR);
                            }  else if (type.equalsIgnoreCase("DRY")) {
                                b.setBiome(Biome.DESERT);
                                if (b.getType() == Material.LONG_GRASS)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.LOG || b.getType() == Material.LOG_2)
                                    b.setType(Material.LOG);
                                if (b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.VINE)
                                    b.setType(Material.AIR);
                                if (b.getType() == Material.GRASS)
                                    b.setType(Material.DIRT);
                                if (b.getType() == Material.WATER || b.getType() == Material.STATIONARY_WATER)
                                    b.setType(Material.AIR);
                            } else if (type.equalsIgnoreCase("NOOB")) {
                                Random rand = new Random();
                                Material random_material;
                                int random_index;
                                Material[] materials =
                                        {
                                                Material.COBBLESTONE,
                                                Material.SPONGE,
                                                Material.STONE,
                                                Material.DIRT
                                        };
                                if (b.getType() == Material.LOG || b.getType() == Material.LOG_2 || b.getType() == Material.LEAVES_2 || b.getType() == Material.LEAVES){
                                    b.setType(Material.AIR);
                                }
                                if (b.getType() != Material.AIR){
                                    random_index = rand.nextInt(3);
                                    random_material = materials[random_index];
                                    b.setType(random_material);
                                }
                            } else if (type.equalsIgnoreCase("NETHER")) {
                                Random rand = new Random();
                                Material random_material;
                                int random_index;
                                Material[] materials =
                                        {
                                                Material.NETHERRACK,
                                                Material.NETHERRACK,
                                                Material.QUARTZ_ORE,
                                                Material.NETHERRACK,
                                                Material.NETHERRACK,
                                                Material.NETHERRACK
                                        };

                                if (b.getType() == Material.LOG || b.getType() == Material.LOG_2 || b.getType() == Material.LEAVES_2 || b.getType() == Material.LEAVES || b.getType() == Material.VINE)
                                    b.setType(Material.AIR);

                                if (b.getType() == Material.WATER || b.getType() == Material.STATIONARY_WATER)
                                    b.setType(Material.STATIONARY_LAVA);

                                if (b.getType() != Material.AIR && b.getType() != Material.LOG_2 && b.getType() != Material.LOG && b.getType() != Material.LEAVES_2 && b.getType() != Material.LEAVES && b.getType() != Material.STATIONARY_WATER && b.getType() != Material.WATER && b.getType() != Material.STATIONARY_LAVA && b.getType() != Material.LAVA && b.getType() != Material.OBSIDIAN && b.getType() != Material.VINE){
                                    rand = new Random();
                                    random_index = rand.nextInt(5);
                                    random_material = materials[random_index];
                                    b.setType(random_material);
                                }

                                if (b.getType() == Material.OBSIDIAN)
                                    b.setType(Material.STATIONARY_LAVA);
                            }
                        }
                    }
                }

                // Second attemp
                if (type.equalsIgnoreCase("NETHER") || type.equalsIgnoreCase("NUCLEAR_DISASTER")){
                    for(int x2 = (r * -1); x2 <= r; x2++) {
                        for (int y2 = (r * -1); y2 <= r; y2++) {
                            for (int z2 = (r * -1); z2 <= r; z2++) {
                                b = eye.getWorld().getBlockAt(eye.getBlockX() + x2, eye.getBlockY() + y2, eye.getBlockZ() + z2);
                                if (b.getType() == Material.OBSIDIAN)
                                    b.setType(Material.STATIONARY_LAVA);
                                b.setBiome(Biome.VOID);
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
