package Elements;

import Objects.PlayerData;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static Elements.PlayerManager.playerData;
import static fun.mikolaj0524.hideAndSeek.HideAndSeek.getPluginInstance;

public class Communication {

	public static String prefix = getPluginInstance().getConfig().getString("prefix");

	/* MESSAGES */
	public static void messageToAll(String text){
		for(Player player : Bukkit.getOnlinePlayers()){
			messageToPlayer(player, text);
		}
	}

	public static void messageToHiders(String text){
		for(Player player : Bukkit.getOnlinePlayers()){
			PlayerData data = playerData.get(player);
			if(!data.isHunter()){
				messageToPlayer(player, text);
			}
		}
	}

	public static void messageToPlayer(Player player, String text){
		player.sendMessage(formatText(text));
	}

	/* HOTBARS */
	public static void hotbarToPlayer(Player player, String text){
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(colorize(text)));
	}

	/* TITLES */
	public static void titleToRunners(String text){
		for(Player player : Bukkit.getOnlinePlayers()){
			PlayerData data = playerData.get(player);
			if(data.isHunter()){
				titleToPlayer(player, text);
			}
		}
	}

	public static void titleToHiders(String text){
		for(Player player : Bukkit.getOnlinePlayers()){
			PlayerData data = playerData.get(player);
			if(!data.isHunter()){
				titleToPlayer(player, text);
			}
		}
	}

	public static void titleToPlayer(Player player, String text){
		player.sendTitle(colorize(text), "", 10, 20, 10);
	}

	/* FORMATING */
	public static String formatText(String text){
		return colorize(prefix + text + " \n ");
	}

	public static String colorize(String text){
		return ChatColor.translateAlternateColorCodes('&', text);
	}

}
