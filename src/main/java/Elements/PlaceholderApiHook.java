package Elements;

import Objects.MapData;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static Elements.LocManager.mapData;
import static Elements.LocManager.selectedMap;
import static Elements.SmallCaps.smallCaps;
import static Elements.Timers.time;

public class PlaceholderApiHook extends PlaceholderExpansion {


	@Override
	public @NotNull String getAuthor() {
		return "Mikolaj0524";
	}

	@Override
	public @NotNull String getIdentifier() {
		return "hns";
	}

	@Override
	public @NotNull String getVersion() {
		return "1.0.0";
	}

	@Override
	public String onRequest(OfflinePlayer player, @NotNull String params) {
		if(!(player instanceof Player))
			return "";
		if(params.equals("map_name")){
			return mapData.get(selectedMap).getName();
		}
		else if(params.equals("game_time")){
			String minutes = String.valueOf(time / 60);
			String seconds = String.valueOf(time % 60);
			if (minutes.length() == 1) {
				minutes = "0" + minutes;
			}
			if (seconds.length() == 1) {
				seconds = "0" + seconds;
			}
			return minutes + ":" + seconds;
		}
		else if(params.startsWith("block_")){
			int num = Integer.parseInt(params.substring(params.length() - 1));

			MapData data = mapData.get(selectedMap);

			if(num < data.getMaterials().size()){
				return "&7  -&a " + smallCaps(data.getMaterials().get(num).name());
			}
			else{
				return "";
			}
		}
		return "";
	}

	public static void registerHook() {
		new PlaceholderApiHook().register();
	}
}
