package net.Seeyko.fr.game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import net.Seeyko.fr.FallenKingdom;
import net.Seeyko.fr.events.GameState;
import net.Seeyko.fr.join.FKJoin;
import net.Seeyko.fr.util.Title;

public class FKGame {
	
	
	public static int timerJour = 1;
	public static int timerSecondes = 0;
	public static int timerMinutes = 0;
	public static int timer = 10009;
	public static int task;
	public static int taskSecondes;
	public static int taskMinutes;
	public static int taskJour;
	public static FKGame instance;
	public static FKGame getInstance() {
		return instance;

	}

	
	public static void start(){
		
		GameState.setState(GameState.Day1);
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable(){

			public void run() {
				timer --;
				if(timerSecondes == 59){
				timerSecondes = -1;
				timerMinutes = timerMinutes + 1;
				}
				if(timerMinutes == 15){
				timerJour = timerJour + 1;
				timerMinutes = 0;
				timerSecondes = -1;				
				}
				for(Player player : FallenKingdom.getInstance().playerInGame){
					if(!(player.getGameMode().equals(GameMode.SPECTATOR))){
					Player pl = player.getPlayer();
					pl.setGameMode(GameMode.SURVIVAL);
					}
				}
				if(timer < 10008){
					for(int i = 0; i < FallenKingdom.getInstance().Spectator.size(); ++i){
						Player player = FallenKingdom.getInstance().Spectator.get(i);
						player.setGameMode(GameMode.SPECTATOR);
						FallenKingdom.getInstance().playerInGame.remove(player);
					}
					
				}
				
				
				if(timer == 10000){
					for(int i = 0; i < FallenKingdom.getInstance().allPlayer.size(); ++i){
						Player player = FallenKingdom.getInstance().allPlayer.get(i);
						Title.sendTitle(player, "§6Day" + timerJour, FallenKingdom.Lang.TITLE_DAY_ONE+ "" , 20);
					}
					FKJoin.DamageonTp = false;
				}	
				if(timer < 10000){
					timerSecondes ++;

				}
				
				if(timerJour == 1){
					Bukkit.getWorld(FallenKingdom.getInstance().World).setPVP(false);
					
				}
				if(timerJour == 2 && timerMinutes == 0 && timerSecondes == 0){
					for(int i = 0; i < FallenKingdom.getInstance().allPlayer.size(); ++i){
						Player player = FallenKingdom.getInstance().allPlayer.get(i);
						Title.sendTitle(player, "§6Day " + timerJour,FallenKingdom.Lang.TITLE_DAY_TWO + "", 20);
					}
					
				}
				if(timerJour == 3 && timerMinutes == 0 && timerSecondes == 0){
					Bukkit.getWorld(FallenKingdom.getInstance().World).setPVP(true);
					for(int i = 0; i < FallenKingdom.getInstance().allPlayer.size(); ++i){
						Player player = FallenKingdom.getInstance().allPlayer.get(i);
						Title.sendTitle(player, "§6Day " + timerJour,FallenKingdom.Lang.TITLE_DAY_THREE + "", 20);
						Bukkit.getWorld(player.getWorld().getName()).setPVP(true);

					}
				}
				if(timerJour == 4 && timerMinutes == 0 && timerSecondes == 0){
				
					for(int i = 0; i < FallenKingdom.getInstance().allPlayer.size(); ++i){
						Player player = FallenKingdom.getInstance().allPlayer.get(i);
						Title.sendTitle(player, "§6Day " + timerJour,FallenKingdom.Lang.TITLE_DAY_FOUR + "", 20);

					}
					
				}
				if(timerJour == 5 && timerMinutes == 0 && timerSecondes == 0 || timerJour == 6 && timerMinutes == 0 && timerSecondes == 0 ||timerJour == 7 && timerMinutes == 0 && timerSecondes == 0 ||timerJour == 8 && timerMinutes == 0 && timerSecondes == 0 || timerJour == 9 && timerMinutes == 0 && timerSecondes == 0){
					
					for(int i = 0; i < FallenKingdom.getInstance().allPlayer.size(); ++i){
						Player player = FallenKingdom.getInstance().allPlayer.get(i);
						Title.sendTitle(player, "§6Day " + timerJour,null, 20);
					}
				}
				}
				
			},20,20);
			
				}}