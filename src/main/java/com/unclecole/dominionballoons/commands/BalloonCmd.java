package com.unclecole.dominionballoons.commands;

import com.unclecole.dominionballoons.DominionBalloons;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class BalloonCmd implements CommandExecutor {

    private DominionBalloons plugin;

    public BalloonCmd() {
        plugin = DominionBalloons.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String string, String[] args) {
        Player player = (Player) s;

        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(),EntityType.ARMOR_STAND);
        Slime silverfish = (Slime) player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
        silverfish.setSize(0);

        silverfish.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,Integer.MAX_VALUE,5,false,false));
        NBTEntity silverfishEntity = new NBTEntity(silverfish);
        silverfishEntity.setInteger("NoAI", 1);

        armorStand.getEquipment().setHelmet(new ItemStack(Material.SKULL_ITEM));
        armorStand.setVisible(false);
        NBTEntity entity = new NBTEntity(armorStand);
        entity.setInteger("NoAI", 1);
        silverfish.setLeashHolder(player);
        armorStand.setCanPickupItems(false);

        new BukkitRunnable() {
            @Override
            public void run() {
                if(armorStand.isDead() || !armorStand.isValid()) {
                    cancel();
                }

                Location location = armorStand.getLocation();

                if(Math.abs(location.getY() - player.getLocation().getY()) > 2) {
                    location.setY(player.getLocation().getY() + 2);
                }


                if(Math.abs(location.getX() - player.getLocation().getX()) > 0.5 || Math.abs(location.getZ()-player.getLocation().getZ()) > 0.5) {
                    location.setX(player.getLocation().getX()+0.5);
                    location.setZ(player.getLocation().getZ()-0.5);
                }

                armorStand.teleport(location);
                silverfish.teleport(location.add(0.0,1.0,0.0));
            }
        }.runTaskTimerAsynchronously(DominionBalloons.getInstance(), 50L, 1L);

        return false;
    }



    public boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
