package net.Seeyko.fr.join;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import net.Seeyko.fr.FallenKingdom;
import net.Seeyko.fr.events.GameState;
import net.Seeyko.fr.events.Teleportation;
import net.Seeyko.fr.game.FKGame;
import net.Seeyko.fr.scoreboard.CustomScoreboardManager;
import net.Seeyko.fr.scoreboard.ScoreboardRunnable;
import net.Seeyko.fr.util.Sounds;
import net.Seeyko.fr.util.Title;

public class FKJoin implements Listener {

	public static boolean DamageonTp;
	public static Location Spawn = (new Location(Bukkit.getWorld(FallenKingdom.getInstance().World),
			FallenKingdom.getInstance().getConfig().getDouble("Spawn.x"),
			FallenKingdom.getInstance().getConfig().getDouble("Spawn.y"),
			FallenKingdom.getInstance().getConfig().getDouble("Spawn.z"))); 	
	public static int nbJoueur = FallenKingdom.getInstance().getConfig().getInt("number-of-players");
	public static FKJoin instance;
	public static FKJoin getInstance() {
		return instance;

	}

	static int task;
	public static int timer = 122;
	
	@EventHandler
	public void Join(PlayerJoinEvent event){
		
		
		Player player = event.getPlayer();
		
		
		if (!Spawn.getChunk().isLoaded()) {
			Spawn.getChunk().load();
		}
		if(!(FallenKingdom.getInstance().allPlayer.contains(player))){
		FallenKingdom.getInstance().allPlayer.add(player);
		}
		if(GameState.isState(GameState.Day1) || GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3) || GameState.isState(GameState.Day4)|| GameState.isState(GameState.Day5IF)|| GameState.isState(GameState.FINISH)){
			if(!(FallenKingdom.getInstance().Spectator.contains(player))){
			FallenKingdom.getInstance().Spectator.add(player);
			}
			if(FallenKingdom.getInstance().playerInGame.contains(player)){
			FallenKingdom.getInstance().playerInGame.remove(player);
			}
			
			player.setHealth(20);
			event.setJoinMessage("");
			player.setLevel(-1);
			FallenKingdom.getInstance();
			player.sendMessage(FallenKingdom.Lang.PREFIX  + ""  + FallenKingdom.Lang.GAME_ALREADY_START);
			return;
		}
		else if(GameState.isState(GameState.LOBBY) && timer < 122 && timer > 1 && FallenKingdom.getInstance().playerInGame.size() > nbJoueur ){
			if(!(FallenKingdom.getInstance().Spectator.contains(player))){
				FallenKingdom.getInstance().Spectator.add(player);
				}
				if(FallenKingdom.getInstance().playerInGame.contains(player)){
				FallenKingdom.getInstance().playerInGame.remove(player);
				}
				player.setHealth(20);
				event.setJoinMessage("");
				player.setLevel(-1);
				FallenKingdom.getInstance();
				player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.GAME_FULL );
				return;
			
		
			
		}else {
			
			new Sounds(player).playSound(Sound.NOTE_PIANO);
			player.setGameMode(GameMode.SURVIVAL);
			player.setHealth(20);
			player.setFoodLevel(20);
			player.setLevel(-1);
			player.removePotionEffect(PotionEffectType.BLINDNESS);
			player.removePotionEffect(PotionEffectType.CONFUSION);
			FallenKingdom.getInstance().playerInGame.add(player);
		
			event.setJoinMessage("§b"+ player.getName() + "§6 has joined the game (§b" + (FallenKingdom.getInstance().playerInGame.size())+ "§e/§b" + nbJoueur + "§e)");
			
				
			for(Player allPlayer : FallenKingdom.getInstance().allPlayer){
				Title.sendActionBar(allPlayer, "§b"+ player.getName() + "§6 has joined the game (§b" + (FallenKingdom.getInstance().playerInGame.size())+ "§e/§b" + nbJoueur + "§e)");

			}
			player.teleport(Spawn);
			player.setHealth(20);
			player.setFoodLevel(20);
			player.setLevel(-1);
			player.removePotionEffect(PotionEffectType.BLINDNESS);
			player.removePotionEffect(PotionEffectType.CONFUSION);
			player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 12000, 5));

			player.getInventory().addItem(choixTeam());
			new CustomScoreboardManager(player);
			ScoreboardRunnable.sendLine();
			player.setScoreboard(CustomScoreboardManager.getScoreboard(player).getMainScoreboard());
		


			startgame();
		}
	}
	public void startgame() {
		DamageonTp = true;
		if(FallenKingdom.getInstance().playerInGame.size() == 1){
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable() {
		
				@Override
				public void run() {
					timer--;
					setLevel(timer);
					if(timer < 122){
						for(int i = 0; i < FallenKingdom.getInstance().Spectator.size(); ++i){
							Player player = FallenKingdom.getInstance().Spectator.get(i);
							player.setGameMode(GameMode.SPECTATOR);
							FallenKingdom.getInstance().playerInGame.remove(player);
						}
						
					}
					
					if (timer < 121 && FallenKingdom.getInstance().playerInGame.size() <= 0) {
						Bukkit.getScheduler().cancelTask(task);
						Bukkit.getScheduler().cancelTask(timer);
						timer = 122;
						FallenKingdom.getInstance();
						Bukkit.broadcastMessage(FallenKingdom.Lang.PREFIX+ "not enough player for start the game !");
						for (Player player : Bukkit.getOnlinePlayers()){
							player.setLevel(-1);
						}
					}
					if (timer > 62) {
						if (FallenKingdom.getInstance().playerInGame.size() >= nbJoueur / 2) {
							timer = 61;

						}
					}
					if (timer > 12) {
						if (FallenKingdom.getInstance().playerInGame.size() == nbJoueur) {
							timer = 11;

						}
					}
					if (FallenKingdom.getInstance().Spectator.size() >= 1) {
						if (FallenKingdom.getInstance().playerInGame.size() < nbJoueur) {

							Player pl = FallenKingdom.getInstance().Spectator.get(0);
							pl.setGameMode(GameMode.SURVIVAL);
							FallenKingdom.getInstance();
							pl.sendMessage(FallenKingdom.Lang.PREFIX +"a place have been released for you !");
							FallenKingdom.getInstance().Spectator.remove(pl);
							FallenKingdom.getInstance().playerInGame.add(pl);
							pl.setLevel(timer);
							
							FallenKingdom.getInstance();
							Bukkit.broadcastMessage(FallenKingdom.Lang.PREFIX + "§b " + pl.getDisplayName() + "§6 a rejoint la partie(§b"+ (FallenKingdom.getInstance().playerInGame.size()) + "§e/§b" + nbJoueur + "§e)");
							
						}
					}
					if (timer == 120 || timer == 60 || timer == 20 || timer == 15 || timer == 10 || timer == 5) {
						FallenKingdom.getInstance();
						Bukkit.broadcastMessage(FallenKingdom.Lang.PREFIX + "The game start in §d" + timer + "§6 secondes");
						for (Player player : FallenKingdom.getInstance().playerInGame) {

							Player pl = player.getPlayer();
							Title.sendTitle(pl, "", "§6Start in §E" + timer + " secondes", 20);
							new Sounds(pl).playSound(Sound.NOTE_PLING);

						}
					}
					if (timer == 3 || timer == 2 || timer == 1) {
						for (Player player : FallenKingdom.getInstance().playerInGame) {

							Player pl = player.getPlayer();
							Title.sendTitle(pl, "§e" + timer,"" , 20);
							new Sounds(pl).playSound(Sound.NOTE_PLING);
						}

					}

					if (timer == 2) {

						FKGame.start();
						Bukkit.getWorld(FallenKingdom.getInstance().World).setDifficulty(Difficulty.HARD);

					}

					// If timer = 10 we call the class UHCGame.start() and
					// cancel the task.
					if (timer == 0) {
						for (Player player : FallenKingdom.getInstance().playerInGame) {

							Title.sendTitle(player, ChatColor.YELLOW + "Good luck ! ", "", 20);
							player.getInventory().clear();
							if(!(FallenKingdom.getInstance().Blue.getPlayers().contains(player)) && !(FallenKingdom.getInstance().Red.getPlayers().contains(player)) && !(FallenKingdom.getInstance().Green.getPlayers().contains(player)) && !(FallenKingdom.getInstance().Yellow.getPlayers().contains(player))){
								FallenKingdom.getInstance().randomTeam(player);
							}
						}
						Teleportation.effettp();

						timer = 122;
						Bukkit.getScheduler().cancelTask(task);

					}

				}

				
	
	}, 20, 20);
}
}
	public void setLevel(int timer) {
		// On recup l'uuid des joueur de la game
		for (Player player : FallenKingdom.getInstance().allPlayer) {

			Player pl = player.getPlayer();

			pl.setLevel(timer);

		}
	}
	
	
	public static ItemStack Bleu(){
		ItemStack IconBleu = new ItemStack(Material.WOOL, 1, (byte)11);
		ItemMeta iM = IconBleu.getItemMeta();
		iM.setDisplayName("§9Join the Blue team !");
		IconBleu.setItemMeta(iM);
		return IconBleu;
	}
	public static ItemStack Rouge(){

		ItemStack IconRouge = new ItemStack(Material.WOOL, 1, (byte)14);
		ItemMeta iM1 = IconRouge.getItemMeta();
		iM1.setDisplayName("§cJoin the Red team !");
		IconRouge.setItemMeta(iM1);
		return IconRouge;
	}
	public static ItemStack Vert(){
		ItemStack IconVert = new ItemStack(Material.WOOL, 1, (byte)5);
		ItemMeta iM3 = IconVert.getItemMeta();
		iM3.setDisplayName("§aJoin the Green team !");
		IconVert.setItemMeta(iM3);
		return IconVert;
	}
	public static ItemStack Jaune(){

		ItemStack IconJaune = new ItemStack(Material.WOOL, 1, (byte)4);
		ItemMeta iM2 = IconJaune.getItemMeta();
		iM2.setDisplayName("§eJoin the Yellow team !");
		IconJaune.setItemMeta(iM2);
		
		return IconJaune;
	}
	public static ItemStack choixTeam(){

		ItemStack choixTeam = new ItemStack(Material.NETHER_STAR);
		ItemMeta iM5 = choixTeam.getItemMeta();
		iM5.setDisplayName("§6Choose a team !");
		choixTeam.setItemMeta(iM5);
		
		return choixTeam;
	}
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event){
	
		Player player = event.getPlayer();
		if(!player.getInventory().getItemInHand().hasItemMeta()) return;
		if(player.getInventory().getItemInHand().getItemMeta().getDisplayName() == null) return;
        if(player.getItemInHand().getType() != null && player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6Choose a team !")){
            if(event.getAction()== Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            	FallenKingdom.getInstance();
				player.openInventory(FallenKingdom.TeamInv);
            }

        }
			
        
       
        
        
        
	} 
	
	@EventHandler
	public void Damage(EntityDamageEvent event){
		if(GameState.isState(GameState.LOBBY) || FKGame.timer > 10000){
			event.setCancelled(true);
		} 
		if(DamageonTp == true){
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void onDrop(PlayerDropItemEvent event){
		if(event.getItemDrop().getItemStack().getType() == Material.NETHER_STAR){
			event.setCancelled(true);
			}
	}
	
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		Material tnt = Material.TNT;
		//if(GameState.isState(GameState.LOBBY) || FKGame.timer > 1000){
			//event.setCancelled(true);
		//} else {
	    if (FallenKingdom.getInstance().getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(event.getBlock().getLocation()).size() == 0) {
			if(!(event.getBlock().getType().equals(tnt))){
			if(!player.isOp()) {
				event.setCancelled(true);
				player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.PLACE_OUTSIDE_BASE);
			}
			} else if(event.getBlock().getType().equals(tnt)){
				if(GameState.isState(GameState.LOBBY) ||FKGame.timerJour == 1 || FKGame.timerJour == 2 || FKGame.timerJour == 3 ){
					if(!player.isOp()) {

					event.setCancelled(true);
					player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.WAIT_FOURDAYS);
					}
					}
			}
				
	    }    
		
		for(ProtectedRegion r : WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(event.getBlock().getLocation())) {
			
			if(r.getId().equalsIgnoreCase("BaseBleu")){
					if(!FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
						
						  if(!(event.getBlock().getType().equals(tnt))){

								event.setCancelled(true);
								player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.BLUEBASE_PLACE);					               	   
							
						} else {if(FKGame.timerJour == 1 || FKGame.timerJour == 2 || FKGame.timerJour == 3){
							event.setCancelled(true);
							player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.WAIT_FOURDAYS);
						}}
			}
			}
				
			
				if(r.getId().equalsIgnoreCase("BaseRouge")){
					if(!FallenKingdom.getInstance().Red.getPlayers().contains(player)){
						  if(!(event.getBlock().getType().equals(tnt))){

								event.setCancelled(true);
								player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.REDBASE_PLACE);					               	   
							
						} else { if(FKGame.timerJour == 1 || FKGame.timerJour == 2 || FKGame.timerJour == 3){
							event.setCancelled(true);
							player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.WAIT_FOURDAYS);
						}}}
				}

				if(r.getId().equalsIgnoreCase("BaseJaune")){
					if(!FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
						  if(!(event.getBlock().getType().equals(tnt))){

								event.setCancelled(true);
								player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.YELLOWBASE_PLACE);					               	   
							
						} else {if(FKGame.timerJour == 1 || FKGame.timerJour == 2 || FKGame.timerJour == 3){
							event.setCancelled(true);
							player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.WAIT_FOURDAYS);
						}}
			         }
				}
				if(r.getId().equalsIgnoreCase("BaseVert")){
					if(!FallenKingdom.getInstance().Green.getPlayers().contains(player)){
						  if(!(event.getBlock().getType().equals(tnt))){

								event.setCancelled(true);
								player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.GREENBASE_PLACE);					               	   
							
						} else {
									if(FKGame.timerJour == 1 || FKGame.timerJour == 2 || FKGame.timerJour == 3){
										event.setCancelled(true);
										player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.WAIT_FOURDAYS);
									}
								}
			            }
					}
				
			
		 					
				}
		}
	//}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		Material tnt = Material.TNT;

		//if(GameState.isState(GameState.LOBBY) || FKGame.timer > 1000){
			//event.setCancelled(true);
		//} else {
					for(ProtectedRegion r : WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(event.getBlock().getLocation())) {
						if(r.getId().equalsIgnoreCase("BaseBleu")){
					                	   if(event.getBlock().getType() != tnt){
					                			if(!FallenKingdom.getInstance().Blue.getPlayers().contains(player)){

					                	   event.setCancelled(true);
					                	   player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.BLUEBASE_BREAK);
					                	   }					                   }
						}
					if(r.getId().equalsIgnoreCase("BaseJaune")){
	                	   if(event.getBlock().getType() != tnt){
	                			if(!FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){

	                	   event.setCancelled(true);
	                	   player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.YELLOWBASE_BREAK);
	                	   }					                   }
	                }if(r.getId().equalsIgnoreCase("BaseVert")){
	                	   if(event.getBlock().getType() != tnt){
	                			if(!FallenKingdom.getInstance().Green.getPlayers().contains(player)){

	                	   event.setCancelled(true);
	                	   player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.GREENBASE_BREAK);
	                	   }					                   }
	                }if(r.getId().equalsIgnoreCase("BaseRouge")){
	                	   if(event.getBlock().getType() != tnt){
	                			if(!FallenKingdom.getInstance().Red.getPlayers().contains(player)){

	                	   event.setCancelled(true);
	                	   player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.REDBASE_BREAK);
	                	   }					                   }
	                }
					
						
					}		
	}//}
	@EventHandler
	public void kick(PlayerKickEvent e) {

		Player player = e.getPlayer();
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);

		if (FallenKingdom.getInstance().playerInGame.contains(player)) {
			FallenKingdom.getInstance().playerInGame.remove(player.getPlayer());
		}
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		CustomScoreboardManager.sb.remove(player);
		FallenKingdom.getInstance().playerInGame.remove(player);
		FallenKingdom.getInstance().allPlayer.remove(player);
		FallenKingdom.getInstance().Spectator.remove(player);
		
		ItemStack remove = new ItemStack(Material.AIR, 1);
		player.getInventory().clear();
		player.getInventory().addItem(remove);
		player.getInventory().addItem(remove);
		player.getInventory().setHelmet(remove);
		player.getInventory().setChestplate(remove);
		player.getInventory().setLeggings(remove);
		player.getInventory().setBoots(remove);
		FallenKingdom.getInstance().playerInGame.trimToSize();
		if(FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
			FallenKingdom.getInstance().Blue.removePlayer(player);
			FallenKingdom.getInstance().ListBlue --;
		}
		if(FallenKingdom.getInstance().Red.getPlayers().contains(player)){
			FallenKingdom.getInstance().Red.removePlayer(player);
			FallenKingdom.getInstance().ListRed --;
		}
		if(FallenKingdom.getInstance().Green.getPlayers().contains(player)){
			FallenKingdom.getInstance().Green.removePlayer(player);
			FallenKingdom.getInstance().ListGreen --;
		}
		if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
			FallenKingdom.getInstance().Yellow.removePlayer(player);
			FallenKingdom.getInstance().ListYellow --;
		}
		
}
	@EventHandler
	public void quit(PlayerQuitEvent e) {

		// On recup le joueur qui quit
		Player player = e.getPlayer();
		if(FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
			FallenKingdom.getInstance().Blue.removePlayer(player);
			FallenKingdom.getInstance().ListBlue --;
		}
		if(FallenKingdom.getInstance().Red.getPlayers().contains(player)){
			FallenKingdom.getInstance().Red.removePlayer(player);
			FallenKingdom.getInstance().ListRed --;
		}
		if(FallenKingdom.getInstance().Green.getPlayers().contains(player)){
			FallenKingdom.getInstance().Green.removePlayer(player);
			FallenKingdom.getInstance().ListGreen --;
		}
		if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
			FallenKingdom.getInstance().Yellow.removePlayer(player);
			FallenKingdom.getInstance().ListYellow --;
		}
		player.kickPlayer("");
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);

		e.setQuitMessage(null);
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		CustomScoreboardManager.sb.remove(player);
		
		if (FallenKingdom.getInstance().playerInGame.contains(player)) {
			FallenKingdom.getInstance().playerInGame.remove(player.getPlayer());
		}
	

		FallenKingdom.getInstance().playerInGame.remove(player);
		FallenKingdom.getInstance().allPlayer.remove(player);
		FallenKingdom.getInstance(). Spectator.remove(player);
		
		ItemStack remove = new ItemStack(Material.AIR, 1);
		player.getInventory().clear();
		player.getInventory().addItem(remove);
		player.getInventory().addItem(remove);
		player.getInventory().setHelmet(remove);
		player.getInventory().setChestplate(remove);
		player.getInventory().setLeggings(remove);
		player.getInventory().setBoots(remove);
	
		
		if (GameState.isState(GameState.LOBBY)) {
			if(FallenKingdom.getInstance().playerInGame.contains(player)){
			e.setQuitMessage(FallenKingdom.Lang.PREFIX + "§b"+ player.getName() + "§6 left the game (§b" + (FallenKingdom.getInstance().playerInGame.size())+ "§e/§b" + FKJoin.nbJoueur + "§e)");
			}else e.setQuitMessage(null);
		}else if(FallenKingdom.getInstance().playerInGame.contains(player)){
			e.setQuitMessage(FallenKingdom.Lang.PREFIX + "§b"+ player.getName() + "§6 left the game (§b" + (FallenKingdom.getInstance().playerInGame.size())+ "§e/§b" + FKJoin.nbJoueur + "§e)");
		}else e.setQuitMessage(null);
		FallenKingdom.getInstance().ListBlue --;
	}

	}



	
	
	
	
