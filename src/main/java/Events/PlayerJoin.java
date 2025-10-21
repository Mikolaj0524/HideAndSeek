package Events;

import Objects.Place;
import Objects.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static Elements.Communication.formatText;
import static Elements.PlayerManager.playerData;
import static Elements.PlayerManager.teleportPlayer;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		event.setJoinMessage(formatText(player.getDisplayName() + " has joined!"));
		teleportPlayer(player, Place.SPAWN);
		playerData.put(player, new PlayerData());
	}

}
