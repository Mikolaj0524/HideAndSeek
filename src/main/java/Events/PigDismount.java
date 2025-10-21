package Events;

import Objects.PlayerData;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDismountEvent;

import static Elements.PlayerManager.playerData;
import static Elements.PlayerManager.playerMove;

public class PigDismount implements Listener {

	@EventHandler
	public void onPigDismount(EntityDismountEvent event){
		if(event.getEntity() instanceof Pig pig){
			for(Player player : playerData.keySet()){
				PlayerData data = playerData.get(player);
				 if(data.getPig() == pig){
					 playerMove(player);
				 }
			}
		}
	}

}
