package Elements;

import Objects.Place;
import Objects.PlayerData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

import static Elements.Communication.*;
import static Elements.Game.*;
import static Elements.InventoryManager.createBlockSelectMenu;
import static Elements.InventoryManager.materials;
import static Elements.PlayerManager.*;
import static fun.mikolaj0524.hideAndSeek.HideAndSeek.getPluginInstance;

public class Timers {

	public static Integer time = 600;
	private final static float standOffset = 1.4f;

	public static void runTimers(){
		timer();
		timer2();
		timer3();
	}

	public static void timer(){
		new BukkitRunnable() {
			@Override
			public void run() {
				if(gameState){
					time--;
					task();

					if(time <= 0){
						gameEnd(true);
					}
				}
				else{
					time = 600;
				}
			}
		}.runTaskTimer(getPluginInstance(), 0L, 20L);
	}

	public static void timer2(){
		new BukkitRunnable() {
			@Override
			public void run() {
				task2();
			}
		}.runTaskTimer(getPluginInstance(), 0L, 1L);
	}

	public static void timer3(){
		new BukkitRunnable() {
			@Override
			public void run() {
				task3();
			}
		}.runTaskTimer(getPluginInstance(), 0L, 10L);
	}

	public static void task(){
		playerBlockSelect();
		int timeFrom0 = 600 - time;

		switch(timeFrom0){
			case 1: titleToHiders("&415"); break;
			case 2: titleToHiders("&414"); break;
			case 3: titleToHiders("&413"); break;
			case 4: titleToHiders("&412"); break;
			case 5: titleToHiders("&c11"); break;
			case 6: titleToHiders("&c10"); break;
			case 7: titleToHiders("&c9"); break;
			case 8: titleToHiders("&c8"); break;
			case 9: titleToHiders("&67"); break;
			case 10: titleToHiders("&96"); break;
			case 11: titleToHiders("&35"); break;
			case 12: titleToHiders("&b4"); break;
			case 13: titleToHiders("&23"); break;
			case 14: titleToHiders("&a2"); break;
			case 15:
				titleToHiders("&aGO!");
				hide = true;
				autoBlockSelect();
				for(Player player : playerData.keySet()){
					PlayerData data = playerData.get(player);
					if(!data.isHunter())
						teleportPlayer(player, Place.GAME);
				}
				break;
		}

		if(timeFrom0 > 1 && timeFrom0 < 26){
			int counter = 26 - timeFrom0;

			if(counter == 1){
				titleToRunners("&aGO!");
				messageToHiders("Released Hunters!");

				for(Player player : playerData.keySet()){
					PlayerData data = playerData.get(player);
					if(data.isHunter()){
						teleportPlayer(player, Place.GAME);
						giveItems(player);
					}
				}

			}
			else{
				titleToRunners("&a" + counter);
				if(timeFrom0 > 15){
					messageToHiders("Hunters will be released in " + counter + " seconds!");
				}
			}
		}
	}

	public static void task2(){
		if(!gameState)
			return;

		for(Player player : playerData.keySet()){
			PlayerData data = playerData.get(player);

			if(data.isHunter())
				continue;

			if(data.getMaterial() == null)
				continue;

			if(data.getTimeToDisguise() <= 0) {

				if(data.getPig() == null){
					Location loc = player.getLocation().clone().getBlock().getLocation().add(0.5, 0.5, 0.5);

					Block block = loc.getBlock();
					Block block1 = loc.clone().subtract(0, 1, 0).getBlock();
					if(block.getType() == Material.AIR && block1.getType() != Material.AIR){
						block.setType(data.getMaterial());
						data.setBlock(block);

						Pig pig = loc.getWorld().spawn(loc.clone().subtract(0, 1, 0), Pig.class);
						pig.setSilent(true);
						pig.setAdult();
						pig.setInvisible(true);
						pig.setInvulnerable(true);
						pig.setGravity(false);
						pig.addPassenger(player);
						data.setPig(pig);
						data.setDisguised(true);
						player.setInvulnerable(true);
						data.getArmorStand().getEquipment().setHelmet(null);
					}
					else{
						data.setDisguised(false);
					}
				}

			}
			else {
				Location loc = player.getLocation().clone().subtract(0, standOffset, 0);
				if(data.getArmorStand() == null){

					if(!hide)
						continue;

					ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);
					stand.setGravity(false);
					stand.setMarker(true);
					stand.setInvulnerable(true);
					stand.setInvisible(true);
					stand.getEquipment().setHelmet(new ItemStack(data.getMaterial(), 1));

					data.setArmorStand(stand);
				}
				else{
					data.getArmorStand().teleport(loc);
				}
			}
		}
	}

	public static void task3(){

		if(!gameState)
			return;

		for(Player player : playerData.keySet()){
			PlayerData data = playerData.get(player);

			if(data.isHunter())
				continue;

			if(!data.inGame())
				continue;

			if(!hide)
				continue;

			if(data.getArmorStand() != null){
				if(data.getTimeToDisguise() > 0)
					data.decreaseTimeToDisguise();
			}

			Block block1 = player.getLocation().clone().subtract(0, 1, 0).getBlock();
			if(( player.getLocation().getBlock().getType() != Material.AIR || block1.getType() == Material.AIR ) && !data.isDisguised()){
				hotbarToPlayer(player, "&cUnable to disguise!");
			}
			else if(data.isDisguised()){
				hotbarToPlayer(player, "&cDisguised");
			}
			else{
				if(data.getTimeToDisguise() == 0){
					hotbarToPlayer(player, "&cDisguised");
				}
				else{
					hotbarToPlayer(player, "&8[&e&l " + "▉".repeat(data.getTimeToDisguise()) + " &8]");
				}
			}
		}
	}

	public static void autoBlockSelect(){
		Random random = new Random();
		for(Player player : playerData.keySet()){
			PlayerData data = playerData.get(player);
			if(data.getMaterial() == null){
				data.setMaterial(materials.get(random.nextInt(0, materials.size() - 1)));
			}
		}
	}

	public static void playerBlockSelect(){
		for(Player player : playerData.keySet()){
			PlayerData data = playerData.get(player);
			if(!data.isHunter() && data.getMaterial() == null && data.inGame()){
				if(player.getOpenInventory().getType() != InventoryType.CHEST){
					createBlockSelectMenu(player);
				}
			}
		}
	}


}
