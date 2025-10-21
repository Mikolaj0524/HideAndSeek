package Events;

import Objects.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import static Elements.PlayerManager.playerData;

public class ArmorStandInteract implements Listener {

	@EventHandler
	public void onArmorStandClick(PlayerInteractAtEntityEvent event){

		if (!(event.getRightClicked() instanceof ArmorStand))
			return;

		Player player = event.getPlayer();
		ArmorStand stand = (ArmorStand) event.getRightClicked();
		if(!playerData.get(player).isHunter())
			return;

		for(Player player1 : Bukkit.getOnlinePlayers()){
			PlayerData data = playerData.get(player1);
			if(data.getArmorStand() == stand){
				double newHealth = Math.max(player1.getHealth() - 1, 0);
				player1.setHealth(newHealth);
				return;
			}
		}
	}

}
