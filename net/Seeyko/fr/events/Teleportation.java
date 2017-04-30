package net.Seeyko.fr.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.Seeyko.fr.FallenKingdom;
import net.Seeyko.fr.join.FKJoin;
import net.Seeyko.fr.util.Sounds;
import net.Seeyko.fr.util.Title;

public class Teleportation implements Listener {

	public static Teleportation instance;
	public static Teleportation getInstance() {
		return instance;

	}
	static World FK = Bukkit.getWorld(FallenKingdom.getInstance().World);
	public static Location spawnBlue = (new Location(FK,
			FallenKingdom.getInstance().getConfig().getDouble("Base.blue.x"),
			(FallenKingdom.getInstance().getConfig().getDouble("Base.blue.y")) + 5,
			FallenKingdom.getInstance().getConfig().getDouble("Base.blue.z"))); 	
	public static Location spawnRed = (new Location(FK,
			FallenKingdom.getInstance().getConfig().getDouble("Base.red.x"),
			(FallenKingdom.getInstance().getConfig().getDouble("Base.red.y")) + 5,
			FallenKingdom.getInstance().getConfig().getDouble("Base.red.z")));
	public static Location spawnYellow = (new Location(FK,
			FallenKingdom.getInstance().getConfig().getDouble("Base.yellow.x"),
			(FallenKingdom.getInstance().getConfig().getDouble("Base.yellow.y")) + 5,
			FallenKingdom.getInstance().getConfig().getDouble("Base.yellow.z"))); 
	public static Location spawnGreen = (new Location(FK,
			FallenKingdom.getInstance().getConfig().getDouble("Base.green.x"),
			(FallenKingdom.getInstance().getConfig().getDouble("Base.green.y")) + 5,
			FallenKingdom.getInstance().getConfig().getDouble("Base.green.z"))); 
	static boolean freeze = false;
	static int task;
	static int timer = 11;
	public static void effettp(){
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable() {

			public void run() {
				timer--;

				// if timer = 10 we set gameState to PREGAME for canceling
				// damage and
				// we had potion effect for visual effect of teleportation.
				if (timer == 9) {
					

					if (!(spawnBlue).getChunk().isLoaded()){
						(spawnBlue).getChunk().load();
					}
					if (!(spawnRed).getChunk().isLoaded())
					{
					(spawnRed).getChunk().load();
					}if (!(spawnGreen).getChunk().isLoaded()){
						(spawnGreen).getChunk().load();
					}
					if (!(spawnYellow).getChunk().isLoaded())
					{
					(spawnYellow).getChunk().load();
					}
					for (Player player : FallenKingdom.getInstance().allPlayer) {
						Title.sendTitle(player, "", "§eTeleportation...", 65);

						Player pl = player.getPlayer();
						pl.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 12000, 5));
						freeze = true;

					}
				}
				// another effect for teleportation.
				if (timer == 8) {
					for (Player player :FallenKingdom.getInstance().allPlayer){

						player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 12000, 1));
					}
				}

				// remove all effect and teleport the players to different
				// location by Locations.teleportPlayer()
				if (timer == 5) {

					for (Player player : FallenKingdom.getInstance().allPlayer) {
						Player pl = player.getPlayer();
						tpTeam();
						new Sounds(pl).playSound(Sound.ENDERMAN_TELEPORT);

						GameState.setState(GameState.Day1);

						pl.removePotionEffect(PotionEffectType.BLINDNESS);
						pl.removePotionEffect(PotionEffectType.CONFUSION);
					
						freeze = false;
					}
					for(Player player : FallenKingdom.getInstance().playerInGame){
						Player pl = player.getPlayer();
						pl.setGameMode(GameMode.SURVIVAL);

					}

				}
				if (timer == 0) {
					for(Player player : FallenKingdom.getInstance().playerInGame){
						Player pl = player.getPlayer();
						pl.setGameMode(GameMode.SURVIVAL);
						pl.removePotionEffect(PotionEffectType.SATURATION);

					}
					Bukkit.getScheduler().cancelTask(task);
					timer = 10;
					FKJoin.DamageonTp = false;
					
				}
			}

			

		}, 20, 20);

	
	}
	
	static void tpTeam(){
		for(int i = 0; i < FallenKingdom.getInstance().playerInGame.size(); ++i){
			Player player = FallenKingdom.getInstance().playerInGame.get(i);
			if(FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
				player.teleport(spawnBlue);
				
			}
			if(FallenKingdom.getInstance().Red.getPlayers().contains(player)){
				player.teleport(spawnRed);
			}
			if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
				player.teleport(spawnYellow);
			}
			if(FallenKingdom.getInstance().Green.getPlayers().contains(player)){
				player.teleport(spawnGreen);
			}
		}
		
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (freeze == true) {
			e.setTo(e.getFrom());
		}
		if(e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)){
			
		}
		if(GameState.isState(GameState.LOBBY)){
			if(player.getLocation().getY() < FallenKingdom.getInstance().getConfig().getDouble("spawn-y")){
				player.teleport(FKJoin.Spawn);
			}
		} 
	}
	
}
