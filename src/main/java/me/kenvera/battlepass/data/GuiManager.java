package me.kenvera.battlepass.data;

import me.kenvera.battlepass.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GuiManager implements Listener {

    private final Main plugin;

    public GuiManager(Main plugin) {
        this.plugin = plugin;
    }

    public Inventory inv;

    public void createGui(Player p, Integer size, String title, Material emptyslot){
        inv = Bukkit.createInventory(p, size, title);
        p.openInventory(inv);
        for (int i = 0; i < size; i++){
            inv.setItem(i, createGuiItem(emptyslot, "§a", "null", 1));
        }
    }

    public void menuGui(Player p){
        createGui(p, 9, "§8Battlepass", Material.BLACK_STAINED_GLASS_PANE);
        inv.setItem(2, createGuiItem(Material.EMERALD, "§aReward", ChatColor.GRAY + "Your rewards will be listed here...", 1));
        inv.setItem(4, createGuiItem(Material.CHEST, "§aBattlepass", ChatColor.GRAY + "Your Battlepasses will be listed here...", 1));
        inv.setItem(6, createGuiItem(Material.BOOK, "§aChallenge", ChatColor.GRAY + "Your challenges will be listed here...", 1));
    }

    public void listGui(Player p){
        createGui(p, 27, "§8Battlepass", Material.BLACK_STAINED_GLASS_PANE);
        inv.setItem(11, createGuiItem(Material.SUGAR, "§f§lFree Battlepass", ChatColor.GRAY + "Free Battlepass without any purchase needed...", 1));
        inv.setItem(13, createGuiItem(Material.BLAZE_POWDER, "§6§lFlawless Battlepass", ChatColor.GRAY + "Paid Battlepass you might want to try once in your life...", 1));
        inv.setItem(15, createGuiItem(Material.NETHER_STAR, "§c§lChaotic Battlepass", ChatColor.GRAY + "A luxury Battlepass that would put you in luxuriness...", 1));
        inv.setItem(18, createGuiItem(Material.YELLOW_GLAZED_TERRACOTTA, "§c§l<BACK", "null", 1));
    }

    public ItemStack createGuiItem(Material material, String name, String lore, Integer amount){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(name);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);


        ArrayList<String> Lore = new ArrayList<>();
        if (lore != "null") {
            Lore.add(lore);
            meta.setLore(Lore);
        } else{
            Lore.clear();
            meta.setLore(Lore);
        }
        item.setItemMeta(meta);
        item.setAmount(amount);
        return item;
    }

    public void freeGui(Player p, Integer page){
        int count = 1;
        Material border = Material.valueOf(plugin.getConfig().getString("Layout.Border.Free"));
        Material tier = Material.valueOf(plugin.getConfig().getString("Layout.Tier.Free"));
        Material back = Material.valueOf(plugin.getConfig().getString("Buttons.Back"));
        Material next = Material.valueOf(plugin.getConfig().getString("Buttons.Next_page"));
        Material claim = Material.valueOf(plugin.getConfig().getString("Buttons.Claim"));
        Material exit = Material.valueOf(plugin.getConfig().getString("Buttons.Exit"));
        Material reward = Material.valueOf(plugin.getConfig().getString("Reward"));
        createGui(p, 36, "§f§lFree Battlepass", Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, createGuiItem(border, "§a", "null", 1));
        }
        for (int i = 9; i <= 27; i = i + 9) {
            inv.setItem(i, createGuiItem(border, "§a", "null", 1));
        }
        for (int i = 17; i <= 26; i = i + 9) {
            inv.setItem(i, createGuiItem(border, "§a", "null", 1));
        }
        for (int i = 27; i < 35; i++) {
            inv.setItem(i, createGuiItem(border, "§a", "null", 1));
        }
        for (int i = 10; i <= 16; i++){
            switch (page) {
                case 1:
                    for (int o = 19; o <= 25; o = o + 3) {
                        count ++;
                        inv.setItem(o, createGuiItem(reward, "§b§lReward " + (count - 19), "- " + plugin.data.getConfig("Rewards/Free.yml").get().getItemStack("Slot." + 1).getItemMeta().getDisplayName(), 1));
                    }
                    inv.setItem(i, createGuiItem(tier, "§o§lTIER " + (i - 9), "null", (i - 9)));
                    break;
                case 2:
                    for (int o = 20; o <= 25; o = o + 3) {
                        count++;
                        inv.setItem(o, createGuiItem(reward, "§b§lReward " + (count - 11), "null", 1));
                    }
                    inv.setItem(i, createGuiItem(tier, "§o§lTIER " + (i - 2), "null", (i - 2)));
                    break;
                case 3:
                    for (int o = 19; o <= 25; o = o + 3) {
                        inv.setItem(o, createGuiItem(reward, "Reward " + (o - 9), "null", 1));
                    }
                    inv.setItem(i, createGuiItem(tier, "§o§lTIER " + (i + 5), "null", (i + 5)));
                    break;
                case 4:
                    for (int o = 20; o <= 25; o = o + 3) {
                        inv.setItem(o, createGuiItem(reward, "Reward " + (o - 6), "null", 1));
                    }
                    inv.setItem(i, createGuiItem(tier, "§o§lTIER " + (i + 12), "null", (i + 12)));
                    break;
                case 5:
                    for (int o = 19; o <= 25; o = o + 3) {
                        inv.setItem(o, createGuiItem(reward, "Reward " + (o - 3), "null", 1));
                    }
                    inv.setItem(i, createGuiItem(tier, "§o§lTIER " + (i + 17), "null", (i + 17)));
                    break;
            }
        }
        inv.setItem(27, createGuiItem(back, "§c§lBACK", null, 1));
        inv.setItem(30, createGuiItem(claim, "§a§lCLAIM", null, 1));
        inv.setItem(32, createGuiItem(exit, "§c§lEXIT", null, 1));
        inv.setItem(35, createGuiItem(next, "§b§lNEXT PAGE", null, 1));
    }

    public void flawlessGui(Player p){
        createGui(p, 54, "§6§lFlawless Battlepass", Material.AIR);
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, createGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "§a", "null", 1));
        }
        for (int i = 9; i <= 36; i = i + 9) {
            inv.setItem(i, createGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "§a", "null", 1));
        }
        for (int i = 17; i <= 44; i = i + 9) {
            inv.setItem(i, createGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "§a", "null", 1));
        }
        for (int i = 45; i < 54; i++) {
            inv.setItem(i, createGuiItem(Material.ORANGE_STAINED_GLASS_PANE, "§a", "null", 1));
        }
        inv.setItem(37, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(29, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(30, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(31, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(32, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(33, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(40, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(43, createGuiItem(Material.BARRIER, "§a", "null", 1));
    }

    public void chaoticGui(Player p){
    createGui(p, 54, "§c§lChaotic Battlepass", Material.AIR);
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, createGuiItem(Material.RED_STAINED_GLASS_PANE, "§a", "null", 1));
        }
        for (int i = 9; i <= 36; i = i + 9) {
            inv.setItem(i, createGuiItem(Material.RED_STAINED_GLASS_PANE, "§a", "null", 1));
        }
        for (int i = 17; i <= 44; i = i + 9) {
            inv.setItem(i, createGuiItem(Material.RED_STAINED_GLASS_PANE, "§a", "null", 1));
        }
        for (int i = 45; i < 54; i++) {
            inv.setItem(i, createGuiItem(Material.RED_STAINED_GLASS_PANE, "§a", "null", 1));
        }
        inv.setItem(37, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(29, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(30, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(31, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(32, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(33, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(40, createGuiItem(Material.BARRIER, "§a", "null", 1));
        inv.setItem(43, createGuiItem(Material.BARRIER, "§a", "null", 1));
    }

    public void editorGui(Player p, String title, Integer page){
        int count = 1;
        createGui(p, 54, title, Material.BROWN_STAINED_GLASS_PANE);
        for (int i = 10; i <= 16; i++){
            inv.setItem(i, createGuiItem(Material.YELLOW_STAINED_GLASS_PANE, "§7Slot " + count, "null", count));
            inv.setItem(i + 18, createGuiItem(Material.YELLOW_STAINED_GLASS_PANE, "§7Slot " + (count + 7), "null", (count + 7)));
            count++;
        }
        for (String key : plugin.data.getConfig("Rewards/Free.yml").get().getKeys(false)){
            for (int i = 19; i <= 43; i++) {
                if (i >= 19 && i <= 25) {
                    if (plugin.data.getConfig("Rewards/Free.yml").get().getString(key + "." + (i - 18)) != null) {
                        inv.setItem(i, plugin.data.getConfig("Rewards/Free.yml").get().getItemStack(key + "." + (i - 18)));
                    } else {
                        inv.setItem(i, null);
                    }
                }
                if (i >= 37 && i <= 43){
                    if (plugin.data.getConfig("Rewards/Free.yml").get().getString(key + "." + (i - 29)) != null) {
                        inv.setItem(i, plugin.data.getConfig("Rewards/Free.yml").get().getItemStack(key + "." + (i - 29)));
                    } else {
                        inv.setItem(i, null);
                    }
                }D
            }
        }
    }
}
