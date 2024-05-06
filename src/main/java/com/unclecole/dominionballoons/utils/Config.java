package com.unclecole.dominionballoons.utils;

import com.unclecole.dominionballoons.DominionBalloons;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import lombok.Getter;
import org.bukkit.Material;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.List;

public class Config {
    private DominionBalloons dominionFun;
    @Getter private ItemBuilder switcherBall;
    @Getter private ItemBuilder switcherStick;
    @Getter private ItemBuilder grappler;


    @Getter private int stickCooldown;
    @Getter private int ballCooldown;
    @Getter private int grapplerCooldown;
    
    @Getter private List<String> grapplerLore;
    @Getter private List<String> switcherStickLore;

    public Config(DominionBalloons dominionFun) {
        this.dominionFun = dominionFun;
        loadSwitcherBall();
        loadSwitcherStick();
        loadConfig();
        loadGrappler();
    }

    public void loadConfig() {
        stickCooldown = dominionFun.getConfig().getInt("SwitcherStick.Cooldown");
        ballCooldown = dominionFun.getConfig().getInt("SwitcherBall.Cooldown");
        grapplerCooldown = dominionFun.getConfig().getInt("Grappler.Cooldown");
    }

    public void loadSwitcherBall() {
        switcherBall = new ItemBuilder(Material.valueOf(dominionFun.getConfig().getString("SwitcherBall.Item.Material")));
        switcherBall.setName(C.color(dominionFun.getConfig().getString("SwitcherBall.Item.Name")));
        dominionFun.getConfig().getStringList("SwitcherBall.Item.Lore").forEach(string -> {
            switcherBall.addLore(C.color(string));
        });

        NBTItem item = new NBTItem(switcherBall);
        item.setInteger("switcherball", 1);

        if(switcherBall.getType().equals(Material.SKULL_ITEM) || switcherBall.getType().equals(Material.SKULL)) {
            switcherBall.setDurability(3);
            NBTCompound skull = item.addCompound("SkullOwner");
            skull.setString("Id", "3009709f-5c0b-4434-a406-547917ae1e76");

            NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
            texture.setString("Value",  dominionFun.getConfig().getString("SwitcherBall.Item.Skull"));
        }
        item.applyNBT(switcherBall);
    }

    public void loadSwitcherStick() {
        switcherStickLore = dominionFun.getConfig().getStringList("SwitcherStick.Item.Lore");

        switcherStick = new ItemBuilder(Material.valueOf(dominionFun.getConfig().getString("SwitcherStick.Item.Material")));
        switcherStick.setName(C.color(dominionFun.getConfig().getString("SwitcherStick.Item.Name")));
        dominionFun.getConfig().getStringList("SwitcherStick.Item.Lore").forEach(string -> {
            switcherStick.addLore(C.color(string));
        });

        NBTItem item = new NBTItem(switcherStick);
        item.setInteger("switcherstick", 1);

        if(switcherBall.getType().equals(Material.SKULL_ITEM) || switcherBall.getType().equals(Material.SKULL)) {
            switcherBall.setDurability(3);
            NBTCompound skull = item.addCompound("SkullOwner");
            skull.setString("Id", "3009709f-5c0b-4434-a406-547917ae1e76");

            NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
            texture.setString("Value",  dominionFun.getConfig().getString("SwitcherStick.Item.Skull"));
        }
        item.applyNBT(switcherStick);
    }
    public void loadGrappler() {
        grapplerLore = dominionFun.getConfig().getStringList("Grappler.Item.Lore");
        
        grappler = new ItemBuilder(Material.valueOf(dominionFun.getConfig().getString("Grappler.Item.Material")));
        grappler.setName(C.color(dominionFun.getConfig().getString("Grappler.Item.Name")));
        dominionFun.getConfig().getStringList("Grappler.Item.Lore").forEach(string -> {
            switcherStick.addLore(C.color(string));
        });

        NBTItem item = new NBTItem(grappler);
        item.setInteger("grappler", 1);

        if(grappler.getType().equals(Material.SKULL_ITEM) || grappler.getType().equals(Material.SKULL)) {
            grappler.setDurability(3);
            NBTCompound skull = item.addCompound("SkullOwner");
            skull.setString("Id", "3009709f-5c0b-4434-a406-547917ae1e76");

            NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
            texture.setString("Value",  dominionFun.getConfig().getString("Grappler.Item.Skull"));
        }
        item.applyNBT(grappler);
    }
}
