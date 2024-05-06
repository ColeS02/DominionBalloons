package com.unclecole.dominionballoons;

import com.unclecole.dominionballoons.commands.BalloonCmd;
import org.bukkit.entity.Chicken;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;

public final class DominionBalloons extends JavaPlugin {

    public static DominionBalloons dominionBalloons;
    public static DominionBalloons getInstance() { return dominionBalloons; }
    private ArrayList<Chicken> entityHashMap;

    public ArrayList<Chicken> getEntityHashMap() {
        return entityHashMap;
    }

    @Override
    public void onEnable() {
        dominionBalloons = this;
        getCommand("balloon").setExecutor(new BalloonCmd());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
