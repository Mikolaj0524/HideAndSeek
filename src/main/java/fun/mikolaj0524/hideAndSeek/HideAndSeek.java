package fun.mikolaj0524.hideAndSeek;

import Commands.MenuCommand;
import Elements.PlaceholderApiHook;
import Events.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static Elements.LocManager.loadLocations;
import static Elements.Timers.runTimers;

public final class HideAndSeek extends JavaPlugin implements Listener {
	private static HideAndSeek instance;

	@Override
	public void onEnable() {
		getServer().getLogger().info("Hide And Seek > start!");
		instance = this;
		saveDefaultConfig();

		loadLocations();
		runTimers();

		PluginManager manager = getServer().getPluginManager();
		manager.registerEvents(new BlockBreak(), this);
		manager.registerEvents(new BlockPlace(), this);
		manager.registerEvents(new FoodLevelChange(), this);
		manager.registerEvents(new InventoryClick(), this);
		manager.registerEvents(new ItemDrop(), this);
		manager.registerEvents(new PigDismount(), this);
		manager.registerEvents(new PlayerDeath(), this);
		manager.registerEvents(new PlayerJoin(), this);
		manager.registerEvents(new PlayerMove(), this);
		manager.registerEvents(new PlayerQuit(), this);
		manager.registerEvents(new InteractEvent(), this);
		manager.registerEvents(new Damage(), this);

		manager.registerEvents(this, this);

		getCommand("menu").setExecutor(new MenuCommand());

		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) { //
			PlaceholderApiHook.registerHook();
		}

	}

	@Override
	public void onDisable() {
		getServer().getLogger().info("Hide And Seek > stop!");
	}

	public static HideAndSeek getPluginInstance(){
		return instance;
	}
}
