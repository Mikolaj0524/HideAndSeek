package Events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		if(player.getGameMode() != GameMode.CREATIVE){
			event.setCancelled(true);
		}
	}

}
