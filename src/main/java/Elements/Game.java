package Elements;

import Objects.Place;
import Objects.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

import static Elements.Communication.messageToAll;
import static Elements.InventoryManager.selectedPlayer;
import static Elements.LocManager.selectedMap;
import static Elements.PlayerManager.playerData;
import static Elements.PlayerManager.teleportPlayer;

public class Game {
	public static boolean gameState = false;
	public static boolean hide = false;

	public static void startGame(boolean randomPlayer){
		if(randomPlayer){
			Random random = new Random();
			int counter = 0;
			int customNum = random.nextInt(0, playerData.size() - 1);

			for(Player player : Bukkit.getOnlinePlayers()){
				PlayerData data = playerData.get(player);
				if(counter == customNum){
					data.setHunter(true);
				}
				counter++;
			}
		}
		else{
			playerData.get(selectedPlayer).setHunter(true);
		}

		for(Player player : Bukkit.getOnlinePlayers()){
			playerData.get(player).inGame(true);
		}

		gameState = true;
	}

	public static void isGameEnd(){
		boolean playerExist = false;

		for(PlayerData data : playerData.values()){
			if(data.inGame() && !data.isHunter()){
				playerExist = true;
			}
		}

		if(!playerExist){
			gameEnd(false);
		}
	}

	public static void gameEnd(boolean time){
		for(Player player : playerData.keySet()){
			playerData.get(player).reset();
			teleportPlayer(player, Place.SPAWN);
		}
		messageToAll("&e&l" + (time ? "Hiders" : "Hunters") + " won!");
		restartGame();
	}

	public static void restartGame(){
		gameState = false;
		hide = false;
		selectedPlayer = null;
		selectedMap = 0;
	}

}
