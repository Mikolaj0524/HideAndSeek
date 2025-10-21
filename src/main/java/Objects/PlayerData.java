package Objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Pig;

public class PlayerData {
	private ArmorStand armorStand;
	private Pig pig;
	private Material material;
	private Integer playersFound;
	private Integer timeToDisguise;
	private Location location;
	private Block block;
	private boolean hunter;
	private boolean disguised;
	private boolean inGame;

	public PlayerData(){
		this.armorStand = null;
		this.pig = null;
		this.playersFound = 0;
		this.timeToDisguise = 10;
		this.hunter = false;
		this.disguised = false;
		this.inGame = false;
		this.location = null;
		this.block = null;
		this.material = null;
	}

	public ArmorStand getArmorStand(){
		return this.armorStand;
	}

	public Pig getPig(){
		return this.pig;
	}

	public Integer getPlayersFound(){
		return this.playersFound;
	}

	public Integer getTimeToDisguise(){
		return this.timeToDisguise;
	}

	public Boolean isHunter(){
		return this.hunter;
	}

	public boolean isDisguised(){
		return this.disguised;
	}

	public boolean inGame(){
		return this.inGame;
	}

	public Material getMaterial(){
		return this.material;
	}

	public Block getBlock(){
		return this.block;
	}

	public Location getLocation(){
		return this.location;
	}

	public void setArmorStand(ArmorStand armorStand){
		this.armorStand = armorStand;
	}

	public void setPig(Pig pig){
		this.pig = pig;
	}

	public void setPlayersFound(Integer playersFound){
		this.playersFound = playersFound;
	}

	public void setTimeToDisguise(Integer timeToDisguise){
		this.timeToDisguise = timeToDisguise;
	}

	public void setHunter(Boolean state){
		this.hunter = state;
	}

	public void setDisguised(Boolean state){
		this.disguised = state;
	}

	public void inGame(Boolean state){
		this.inGame = state;
	}

	public void setMaterial(Material material){
		this.material = material;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public void setBlock(Block block){
		this.block = block;
	}

	public void addPlayerFound(){
		this.playersFound++;
	}

	public void decreaseTimeToDisguise(){
		this.timeToDisguise--;
	}

	public void reset(){
		this.inGame = false;
		this.material = null;
		this.hunter = false;
		if(this.armorStand != null)
			this.armorStand.remove();
		this.armorStand = null;
		if(this.pig != null)
			this.pig.remove();
		this.pig = null;
		if(this.block != null)
			this.block.setType(Material.AIR);
		this.block = null;
		this.playersFound = 0;
		this.timeToDisguise = 10;
		this.disguised = false;
		this.location = null;
	}

}
