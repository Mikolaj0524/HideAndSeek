package Events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static Elements.PlayerManager.playerData;
import static Elements.PlayerManager.playerMove;

public class InteractEvent implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
 		Player player = event.getPlayer();

		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && player.getGameMode() != GameMode.CREATIVE){
			event.setCancelled(true);
		}

		if(event.getAction() == Action.LEFT_CLICK_BLOCK){
			if(!playerData.get(player).isHunter())
				return;

			if(event.getClickedBlock() == null)
				return;

			Location loc = event.getClickedBlock().getLocation();
			for(Player player1 : Bukkit.getOnlinePlayers()){
				Block block = playerData.get(player1).getBlock();

				if(block == null)
					continue;

				Location loc1 = block.getLocation();
				if(loc.getBlockX() == loc1.getBlockX() && loc.getBlockY() == loc1.getBlockY() && loc.getBlockZ() == loc1.getBlockZ()){
					playerMove(player1);
					return;
				}
			}
		}

	}

}
