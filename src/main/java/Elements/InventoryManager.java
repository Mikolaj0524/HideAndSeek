package Elements;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.stream.Collectors;

import static Elements.Communication.colorize;
import static Elements.LocManager.mapData;
import static Elements.LocManager.selectedMap;

public class InventoryManager {
	public static String[] inventories = {
			colorize("&3&l        Hide And Seek"),
			colorize("&3&l            Players"),
			colorize("&3&l          Select block")
	};

	public static List<Material> materials = List.of(Material.ANDESITE, Material.COBBLESTONE, Material.DIRT, Material.REDSTONE_BLOCK, Material.SPRUCE_TRAPDOOR, Material.OAK_LEAVES, Material.YELLOW_CONCRETE, Material.BLUE_CONCRETE);

	private static final Integer invSize = 3;

	public static Player selectedPlayer = null;

	public static void createInventory(Player player){
		Inventory inv = Bukkit.createInventory(player,invSize * 9, inventories[0]);
		for(int i = 0; i < invSize * 9; i++){
			inv.setItem(i, createItemStack(Material.GRAY_STAINED_GLASS_PANE, " "));
		}
		reloadInv(inv);
		player.openInventory(inv);
	}

	public static void createInventory2(Player player){
		Inventory inv = Bukkit.createInventory(player,invSize * 9, inventories[1]);
		for(int i = 0; i < invSize * 9; i++){
			if (i < 10 || i > 17) {
				inv.setItem(i, createItemStack(Material.GRAY_STAINED_GLASS_PANE, " "));
			}
		}
		inv.setItem(22, createItemStack(Material.BARRIER, "&c&lGo back"));

		reloadInv2(inv);
		player.openInventory(inv);
	}

	public static void createBlockSelectMenu(Player player){
		Inventory inv = Bukkit.createInventory(player, invSize * 9, inventories[2]);
		for(int i = 0; i < invSize * 9; i++){
			if (i < 11 || i > 16) {
				inv.setItem(i, createItemStack(Material.GRAY_STAINED_GLASS_PANE, " "));
			}
		}

		int counter = 10;
		for(Material material : mapData.get(selectedMap).getMaterials()){
			inv.setItem(counter, new ItemStack(material, 1));
			counter++;
		}
		player.openInventory(inv);
	}

	public static void reloadInv2(Inventory inv){
		for(int i = 8; i < 18; i++){
			inv.setItem(i, createItemStack(Material.AIR, " "));
		}

		int i = 9;

		for(Player player : Bukkit.getOnlinePlayers()){
			inv.setItem(i, createItemStack(player, (selectedPlayer == player ? "&a&l" : "&c&l") + player.getDisplayName()));
			i++;
		}

	}

	public static void reloadInv(Inventory inv){

		if(!(selectedMap >= 0)){
			mapData.get(selectedMap);
		}

		List<String> lore = List.of(
				"",
				"&eCurrent: " + mapData.get(selectedMap).getName(),
				"",
				"&a ⬆ (LMB)",
				"&c ⬇ (RMB)",
				""
		);

		inv.setItem(10, createItemStack(Material.MAP,"&a&lMap", lore));
		inv.setItem(12, createItemStack(Material.SOUL_LANTERN,"&3&lStart random"));
		inv.setItem(14, createItemStack(Material.LANTERN,"&6&lStart"));
		inv.setItem(16, createItemStack(Material.PLAYER_HEAD,"&a&lPlayers"));
	}

	public static ItemStack createItemStack(Material material, String name, List<String> lore){
		return createItemStack(material, name, lore, null);
	}

	public static ItemStack createItemStack(Material material, String name, List<String> lore, Player player) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();

		if(itemMeta == null){
			return itemStack;
		}

		if (player != null && material == Material.PLAYER_HEAD && itemMeta instanceof SkullMeta skullMeta) {
			skullMeta.setOwningPlayer(player);
			itemMeta = skullMeta;
		}

		itemMeta.setDisplayName(colorize(name));

		if (lore != null) {
			List<String> coloredLore = lore.stream().map(text -> colorize(text)).collect(Collectors.toList());
			itemMeta.setLore(coloredLore);
		}

		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static ItemStack createItemStack(Material material, String name){
		return createItemStack(material, name, null);
	}

	public static ItemStack createItemStack(Player player, String name){
		return createItemStack(Material.PLAYER_HEAD, name, null, player);
	}

}
