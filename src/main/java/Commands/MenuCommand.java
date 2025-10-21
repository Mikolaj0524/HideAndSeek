package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static Elements.Communication.messageToPlayer;
import static Elements.InventoryManager.createInventory;

public class MenuCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(sender instanceof Player player){
			if(command.getName().equals("menu")){
				if(player.hasPermission("hns.menu")){
					createInventory(player);
					return true;
				}
				else{
					messageToPlayer(player, "You don't have <hns.menu> permission.");
				}
			}
		}
		return false;
	}

}
