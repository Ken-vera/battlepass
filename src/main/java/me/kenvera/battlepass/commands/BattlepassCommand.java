package me.kenvera.battlepass.commands;

import me.kenvera.battlepass.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BattlepassCommand implements CommandExecutor, Listener {

    private final Main plugin;

    public BattlepassCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0){
            if (sender instanceof Player){
                plugin.gui.menuGui(player);
            }
        } else {
            if (args[0].equalsIgnoreCase("admin")){
                if (args.length >= 3){
                    if (args[1].equalsIgnoreCase("edit")){
                        if (args[2].equalsIgnoreCase("free")){
                            plugin.gui.editorGui(player, "§f§lFree Battlepass §8(§7Editor§8)", 1);
                        }
                    }
                } else {
                    if (args.length >= 2){
                        if (args[1].equalsIgnoreCase("reload")){
                            plugin.data.reloadConfig("config.yml");
                            plugin.data.reloadConfig("Rewards/Free.yml");
                        }
                    }
                }
            }
        }
        return true;
    }
}
