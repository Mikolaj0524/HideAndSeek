package Elements;

import Objects.MapData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;

import java.util.ArrayList;
import java.util.List;

import static fun.mikolaj0524.hideAndSeek.HideAndSeek.getPluginInstance;

public class LocManager {

	public static List<MapData> mapData = new ArrayList<>();
	public static Location spawn;
	public static Integer selectedMap;

	public static void loadLocations(){
		World world = Bukkit.getWorld("world");
		Configuration config = getPluginInstance().getConfig();

		loadSpawn(world, config);
		loadMaps(world, config);
	}

	private static void loadSpawn(World world, Configuration config){
		spawn = new Location(world, config.getDouble("spawn.x"), config.getDouble("spawn.y"), config.getDouble("spawn.z"), config.getInt("spawn.yaw"), 0);
	}

	private static void loadMaps(World world, Configuration config){
		for(String name : config.getConfigurationSection("maps").getKeys(false)){
			String path = "maps." + name + ".";
			Location location = new Location(
					world,
					config.getDouble(path + "loc.x"),
					config.getDouble(path + "loc.y"),
					config.getDouble(path + "loc.z"),
					config.getInt(path + "loc.yaw"),
					0
			);

			List<Material> materials = config.getStringList(path + "blocks").stream().map(x -> Material.getMaterial(x)).toList();

			mapData.add(new MapData(name, location, materials));
		}

		selectedMap = 0;
	}

}