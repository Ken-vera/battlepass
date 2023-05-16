package me.kenvera.battlepass;

import me.kenvera.battlepass.commands.BattlepassCommand;
import me.kenvera.battlepass.data.DataManager;
import me.kenvera.battlepass.data.GuiManager;
import me.kenvera.battlepass.events.GuiHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin{

    public GuiManager gui;
    public GuiHandler handler;
    public DataManager data;

    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {

        config.options().copyDefaults(true);
        gui = new GuiManager(this);
        handler = new GuiHandler(this);
        data = new DataManager(this);

        getCommand("battlepass").setExecutor(new BattlepassCommand(this));
        Bukkit.getPluginManager().registerEvents(new GuiHandler(this), this);
        Bukkit.getPluginManager().registerEvents(new GuiManager(this), this);

        System.out.println(ChatColor.GREEN + "Battlepass plugin by " + ChatColor.AQUA + "Kenvera " + ChatColor.GREEN + "has been enabled!");

        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()){
            saveResource("config.yml", true);
        } else{
            saveDefaultConfig();
        }
    }

    @Override
    public void onDisable() {

    }
}
