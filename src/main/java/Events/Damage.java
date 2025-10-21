package Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import static Elements.Game.gameState;

public class Damage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent event){
		if(!gameState)
			event.setCancelled(true);
	}

}
