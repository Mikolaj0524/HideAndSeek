package Events;

import Objects.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static Elements.PlayerManager.playerData;
import static Elements.PlayerManager.playerMove;

public class PlayerMove implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		Location loc = player.getLocation();

		if(!playerData.containsKey(player)){
			playerData.put(player, new PlayerData());
		}

		PlayerData data = playerData.get(player);
		if((!data.isHunter() && data.inGame()) && data.getMaterial() == null){
			event.setCancelled(true);
		}

		if(!(data.getLocation() != null && data.getLocation().getBlockX() == loc.getBlockX() && data.getLocation().getBlockY() == loc.getBlockY() && data.getLocation().getBlockZ() == loc.getBlockZ())){
			playerMove(player);
			data.setLocation(loc);
		}
	}

}
