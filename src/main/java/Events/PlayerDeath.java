package Events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static Elements.Communication.formatText;
import static Elements.PlayerManager.playerDeath;
import static fun.mikolaj0524.hideAndSeek.HideAndSeek.getPluginInstance;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event){
		Player player = event.getEntity();
		event.setDeathMessage(formatText(player.getDisplayName() + " died!"));

		Bukkit.getScheduler().runTaskLater(getPluginInstance(), () -> playerDeath(player), 1L);
	}

}
