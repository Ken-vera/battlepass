package me.kenvera.battlepass.events;

import me.kenvera.battlepass.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiHandler implements Listener {

    private final Main plugin;

    public GuiHandler(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        if(e.getView().getTitle().equalsIgnoreCase("§8Battlepass")){
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            switch (e.getCurrentItem().getItemMeta().getDisplayName()){
                case "§aBattlepass":
                    plugin.gui.listGui(p);
                    break;
                case "§c§l<BACK":
                    plugin.gui.menuGui(p);
                    break;
                case "§f§lFree Battlepass":
                    plugin.gui.freeGui(p, 1);
                    break;
                case "§6§lFlawless Battlepass":
                    plugin.gui.flawlessGui(p);
                    break;
                case "§c§lChaotic Battlepass":
                    plugin.gui.chaoticGui(p);
                    break;
            }
        }
        if(e.getView().getTitle().equalsIgnoreCase("§f§lFree Battlepass")){
            e.setCancelled(true);
            Player p = (Player) e.getWhoClicked();
            Integer amount = e.getInventory().getItem(10).getAmount();
            switch (e.getCurrentItem().getItemMeta().getDisplayName()){
                case "§b§lReward 1":
                    p.sendMessage(plugin.data.getConfig("Rewards/Free.yml").get().getItemStack("Free.12").getItemMeta().getDisplayName());
                    break;
                case "§b§lNEXT PAGE":
                    switch (amount){
                        case 1:
                            plugin.gui.freeGui(p, 2);
                            break;
                        case 8:
                            plugin.gui.freeGui(p, 3);
                            break;
                        case 15:
                            plugin.gui.freeGui(p, 4);
                            break;
                        case 22:
                            plugin.gui.freeGui(p, 5);
                            break;
                    }
                    break;
                case "§c§lEXIT":
                    p.closeInventory();
                    break;
                case "§c§lBACK":
                    switch (amount){
                        case 1:
                            plugin.gui.listGui(p);
                            break;
                        case 8:
                            plugin.gui.freeGui(p, 1);
                            break;
                        case 15:
                            plugin.gui.freeGui(p, 2);
                            break;
                        case 22:
                            plugin.gui.freeGui(p, 3);
                            break;
                        case 27:
                            plugin.gui.freeGui(p, 4);
                            break;
                    }
                    break;
            }
        }
        if(e.getView().getTitle().equalsIgnoreCase("§6§lFlawless Battlepass")){
            e.setCancelled(true);
        }
        if(e.getView().getTitle().equalsIgnoreCase("§c§lChaotic Battlepass")){
            e.setCancelled(true);
        }
        if(e.getView().getTitle().equalsIgnoreCase("§f§lFree Battlepass §8(§7Editor§8) test")){
            int slot = e.getSlot();
            if (e.getClickedInventory().getItem(slot).getType() == Material.WHITE_STAINED_GLASS_PANE){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEdit(InventoryCloseEvent e){
        if (e.getView().getTitle().equalsIgnoreCase("§f§lFree Battlepass §8(§7Editor§8)"))
            for (int i = 19; i <= 43; i++){
                if (i >= 19 && i <= 25){
                    if (e.getInventory().getItem(i) != null) {
                        plugin.data.getConfig("Rewards/Free.yml").set("Slot." + (i - 18), e.getInventory().getItem(i));
                        plugin.data.saveConfig("Rewards/Free.yml");
                    }
                }
                if (i >= 37 && i <= 43){
                    if (e.getInventory().getItem(i) != null) {
                        plugin.data.getConfig("Rewards/Free.yml").set("Slot." + (i - 29), e.getInventory().getItem(i));
                        plugin.data.saveConfig("Rewards/Free.yml");
                    }
                }
            }
    }
}
