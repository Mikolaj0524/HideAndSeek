package Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static Elements.Communication.messageToPlayer;
import static Elements.Game.gameState;
import static Elements.Game.startGame;
import static Elements.InventoryManager.*;
import static Elements.LocManager.mapData;
import static Elements.LocManager.selectedMap;
import static Elements.PlayerManager.playerData;


public class InventoryClick implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		if(player.getGameMode() != GameMode.CREATIVE){
			event.setCancelled(true);
		}

		if(event.getView().getTitle().equals(inventories[0])){
			switch(event.getSlot()){
				case 10:
					if(event.isLeftClick()){
						if(mapData.size() - 1 > selectedMap){
							selectedMap++;
						}
						else{
							selectedMap = 0;
						}
						reloadInv(event.getClickedInventory());
					}
					else if(event.isRightClick()){
						if(selectedMap != 0){
							selectedMap--;
						}
						else{
							selectedMap = mapData.size() - 1;
						}
						reloadInv(event.getClickedInventory());
					}
					break;
				case 12:
					if(gameState) {
						messageToPlayer(player, "The game is already running!");
					}
					else{
						player.closeInventory();
						startGame(true);
					}
					break;
				case 14:
					if(gameState) {
						messageToPlayer(player, "The game is already running!");
					}
					else {
						if(selectedPlayer != null){
							player.closeInventory();
							startGame(false);
						}
						else{
							messageToPlayer(player, "First you need to select a player!");
						}
					}
					break;
				case 16:
					createInventory2(player);
					break;
			}
		}
		else if(event.getView().getTitle().equals(inventories[1])) {
			if(event.getSlot() == 22){
				createInventory(player);
			}
			ItemStack itemStack = event.getCurrentItem();
			if(itemStack.getType() == Material.PLAYER_HEAD){
				selectedPlayer = Bukkit.getPlayer(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName().trim()));
				reloadInv2(event.getClickedInventory());
			}
		}
		else if(event.getView().getTitle().equals(inventories[2])) {
			if(event.getCurrentItem() == null)
				return;

			Material mat = event.getCurrentItem().getType();
			if(mat.equals(Material.AIR) || mat.equals(Material.GRAY_STAINED_GLASS_PANE))
				return;

			playerData.get(player).setMaterial(mat);
			player.closeInventory();
		}

	}

}
