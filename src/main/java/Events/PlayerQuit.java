package Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static Elements.Communication.formatText;
import static Elements.PlayerManager.playerData;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();

		event.setQuitMessage(formatText(player.getDisplayName() + " left!"));
		if(playerData.containsKey(player)){
			playerData.get(player).reset();
			playerData.remove(player);
		}
	}

}
