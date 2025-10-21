package Objects;


import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class MapData {
	private String name;
	private Location spawn;
	private List<Material> materials = new ArrayList<>();

	public MapData(String name, Location spawn, List<Material> materials){
		this.name = name;
		this.spawn = spawn;
		this.materials = materials;
	}

	public String getName(){
		return this.name;
	}

	public Location getSpawn(){
		return this.spawn;
	}

	public List<Material> getMaterials(){
		return this.materials;
	}
}
