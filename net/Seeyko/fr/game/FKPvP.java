package net.Seeyko.fr.game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.Seeyko.fr.FallenKingdom;
import net.Seeyko.fr.events.Teleportation;
import net.Seeyko.fr.join.FKJoin;
import net.Seeyko.fr.util.Title;
public class FKPvP implements Listener{
	int task;
    double timer = 21.2;
    
    @EventHandler
    public void HitKing(EntityDamageByEntityEvent event){
    	
    }
	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		
		Player player = (Player) event.getEntity();
		Player killer = (Player) player.getKiller();
		
		player.setHealth(20);
		player.setGameMode(GameMode.SPECTATOR);
		if (killer instanceof Player) {

		Bukkit.broadcastMessage(FallenKingdom.Lang.PREFIX + "§r"+ player.getDisplayName() + "§6 has been killed by " + killer.getDisplayName());
		} else 		Bukkit.broadcastMessage(FallenKingdom.Lang.PREFIX +"§r"+ player.getDisplayName() + "§6 is dead");

		if(FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
			if(  Inventory.lifeKingBlue > 1){
				RespawnAuto(player);
			}else return;
		}
		if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
			if( Inventory.lifeKingYellow > 1){
				RespawnAuto(player);
			}else return;
		}if(FallenKingdom.getInstance().Green.getPlayers().contains(player)){
			if( Inventory.lifeKingGreen > 1){
				RespawnAuto(player);
			}else return;
		}if(FallenKingdom.getInstance().Red.getPlayers().contains(player)){
			if(Inventory.lifeKingRed > 1){
				RespawnAuto(player);
			}else return;
		}
		

	}

	private void RespawnAuto(Player player) {
		FKJoin.DamageonTp = true;

		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable() {
			@Override
			public void run() {
				timer --;
				
				
				if(timer == 20.2){
				Title.sendTitle(player, "§c§lDeath", "§6Respawn in §b3", 20);
				}
				if(timer == 19.2){
					Title.sendTitle(player, "§c§lDeath", "§6Respawn in §b2", 20);
					}
				if(timer == 18.2 ){
					Title.sendTitle(player, "§c§lDeath", "§6Respawn in §b1", 20);
					}
				if(timer == 17.2){
					Title.sendTitle(player, "", "§6Let's go !", 20);
					if(FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
						Teleportation.getInstance();
						player.teleport(Teleportation.spawnBlue);
						player.setGameMode(GameMode.SURVIVAL);
					}
					if(FallenKingdom.getInstance().Red.getPlayers().contains(player)){
						Teleportation.getInstance();
						player.teleport(Teleportation.spawnRed);
						player.setGameMode(GameMode.SURVIVAL);

					}
					if(FallenKingdom.getInstance().Green.getPlayers().contains(player)){
						Teleportation.getInstance();
						player.teleport(Teleportation.spawnGreen);
						player.setGameMode(GameMode.SURVIVAL);

					}
					if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
						Teleportation.getInstance();
						player.teleport(Teleportation.spawnYellow);
						player.setGameMode(GameMode.SURVIVAL);

					}
					
				}
				if(timer == 15.2){
					FKJoin.DamageonTp = false;
					Bukkit.getScheduler().cancelTask(task);
					timer = 21.2;
				}
				
			}
	}, 20, 20);
	}
}
