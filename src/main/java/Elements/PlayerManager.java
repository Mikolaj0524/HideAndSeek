package Elements;

import Objects.Place;
import Objects.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

import static Elements.Game.isGameEnd;
import static Elements.LocManager.*;

public class PlayerManager {

	public static HashMap<Player, PlayerData> playerData = new HashMap<>();

	public static void teleportPlayer(Player player, Place place){
		preparePlayer(player, place);
		if(place == Place.SPAWN){
			player.teleport(spawn);
		}
		else{
			player.teleport(mapData.get(selectedMap).getSpawn());
		}
	}

	public static void giveItems(Player player){
		player.getInventory().setItem(0, new ItemStack(Material.STONE_SWORD));
	}

	public static void playerMove(Player player){
		PlayerData data = playerData.get(player);
		if(!data.isHunter()){
			data.setTimeToDisguise(10);
			data.setDisguised(false);
			player.setInvulnerable(false);
			if(data.getPig() != null){
				data.getPig().remove();
				data.setPig(null);
				player.teleport(player.getLocation().add(0, 1,0));
			}
			if(data.getBlock() != null){
				data.getBlock().setType(Material.AIR);
				data.setBlock(null);
			}
			if(data.getMaterial() != null && data.getArmorStand() != null){
				data.getArmorStand().getEquipment().setHelmet(
						new ItemStack(data.getMaterial(), 1)
				);
			}
		}
	}

	public static void playerDeath(Player player){
		player.spigot().respawn();
		player.setGameMode(GameMode.SPECTATOR);
		player.teleport(mapData.get(selectedMap).getSpawn());
		playerData.get(player).reset();
		isGameEnd();
	}

	public static void preparePlayer(Player player, Place place){
		for(PotionEffectType potionEffect : PotionEffectType.values()){
			player.removePotionEffect(potionEffect);
		}
		player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(20);
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setLevel(0);
		player.setExp(0);
		player.setTotalExperience(0);
		player.getInventory().clear();
		player.setInvisible(false);

		if(place == Place.SPAWN){
			player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(20);
			player.setHealth(20.0f);
			player.setInvulnerable(true);
			player.setGameMode(GameMode.ADVENTURE);
			player.setSaturation(20);
		}
		else {
			player.setGameMode(GameMode.SURVIVAL);
			if(playerData.get(player).isHunter()){
				player.setInvulnerable(true);
				player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(20);
				player.setHealth(20.0);
				player.setSaturation(20.0f);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1, false, false));
			}
			else {
				player.setInvulnerable(false);
				player.setInvisible(true);
				player.getAttribute(Attribute.MAX_HEALTH).setBaseValue(6);
				player.setHealth(6.0);
				player.setSaturation(0);
			}
		}
	}

}
