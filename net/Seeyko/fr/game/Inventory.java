package net.Seeyko.fr.game;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import net.Seeyko.fr.FallenKingdom;
import net.Seeyko.fr.FallenKingdom.Lang;
import net.Seeyko.fr.events.GameState;
import net.Seeyko.fr.spawnroi.Spawn;
import net.Seeyko.fr.util.Sounds;

public class Inventory implements Listener{
	
	public static double lifeKingBlue;
	public static double lifeKingRed;
	public static double lifeKingGreen;
	public static double lifeKingYellow;

	

	public static Inventory instance;

	public static Inventory getInstance() {
		
		return instance;
	}
	
	
	
	@EventHandler
	public void onInventoryclik(InventoryClickEvent event) throws IOException{
		 
        Player player = (Player) event.getWhoClicked();

        if(event.getInventory().getName().equalsIgnoreCase(Lang.INV_RULES+ "")){
        	event.setCancelled(true);
        }
        if(event.getInventory().getName().equalsIgnoreCase(Lang.INV_CHOOSE_TEAM+"")){
        	event.setCancelled(true);
        }
        if(event.getInventory().getName().equalsIgnoreCase(Lang.INV_MOVEKING+"")){
        	event.setCancelled(true);
        }
        if(event.getSlotType() == SlotType.OUTSIDE){
        	return;
        }
        if(!(event.getCurrentItem().hasItemMeta()))return;
        if(!(event.getCurrentItem().getItemMeta().hasDisplayName()))return;

        if(event.getCurrentItem() != null && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Lang.INV_CHOOSE_TEAM+"")){
        	event.setCancelled(true);
        }
    	if(!(event.getCurrentItem().getType().equals(null))&& event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(FallenKingdom.Lang.INV_MOVEKING + "")){
    		event.setCancelled(true);
    		
    		 if(!FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(player)){
    			if(GameState.isState(GameState.LOBBY) || FKGame.timer > 9995){
    				player.sendMessage(FallenKingdom.Lang.PREFIX + "§6Wait the game have start to do this");
    	    		player.closeInventory();
    	    	
        		} else if(GameState.isState(GameState.Day4) || GameState.isState(GameState.Day5IF) || GameState.isState(GameState.FINISH)){
            		player.closeInventory();

        			player.sendMessage(FallenKingdom.Lang.PREFIX + "Too late, your king is placed and can't be moved !");
        		} else if((GameState.isState(GameState.Day1) ||GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))){
        			
        			
        			if(FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
        				for (ProtectedRegion r : WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation())) {
        					String currentRegion = r.getId();
        					if(currentRegion.equalsIgnoreCase("BaseBleu")){
              					Spawn.Bleu(player);

        					} else if ((FallenKingdom.getInstance().getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).size() == 0
        						|| currentRegion.equalsIgnoreCase("BaseRouge")|| currentRegion.equalsIgnoreCase("BaseVerte")|| currentRegion.equalsIgnoreCase("BaseJaune"))&&
        							(GameState.isState(GameState.Day1)|| GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))
        							&& !(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(player))) {
        						player.sendMessage("§9King§6 : Hey, go back to my castle if you wan't to move me !");
        					}

        					}
        				
          
        				}
        			if(FallenKingdom.getInstance().Red.getPlayers().contains(player)){
        				for (ProtectedRegion r : WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation())) {
        					String currentRegion = r.getId();
        					if(currentRegion.equalsIgnoreCase("BaseRouge")){
              					Spawn.Rouge(player);

        					} else if ((FallenKingdom.getInstance().getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).size() == 0
        						|| currentRegion.equalsIgnoreCase("BaseRouge")|| currentRegion.equalsIgnoreCase("BaseVerte")|| currentRegion.equalsIgnoreCase("BaseJaune"))&&
        							(GameState.isState(GameState.Day1)|| GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))
        							&& !(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(player))) {
        						player.sendMessage("§cKing§6 : Hey, go back to my castle if you wan't to move me !");
        					}

        					}
        				
          
        				}
        			if(FallenKingdom.getInstance().Green.getPlayers().contains(player)){
        				for (ProtectedRegion r : WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation())) {
        					String currentRegion = r.getId();
        					if(currentRegion.equalsIgnoreCase("BaseVert")){
        						
              					Spawn.Vert(player);

        					} else if (((FallenKingdom.getInstance().getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).size() == 0
        						|| currentRegion.equalsIgnoreCase("BaseRouge")|| currentRegion.equalsIgnoreCase("BaseVerte")|| currentRegion.equalsIgnoreCase("BaseJaune"))&&
        							(GameState.isState(GameState.Day1)|| GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))
        							&& !(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(player)))) {
        						player.sendMessage("§aKing§6 : Hey, go back to my castle if you wan't to move me !");
        					}

        					}
        				
          
        				}
        			if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
        				for (ProtectedRegion r : WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation())) {
        					String currentRegion = r.getId();
        					if(currentRegion.equalsIgnoreCase("BaseJaune")){
              					Spawn.Jaune(player);

        					} else if ((FallenKingdom.getInstance().getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).size() == 0
        						|| currentRegion.equalsIgnoreCase("BaseRouge")|| currentRegion.equalsIgnoreCase("BaseVerte")|| currentRegion.equalsIgnoreCase("BaseJaune"))&&
        							(GameState.isState(GameState.Day1)|| GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))
        							&& !(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(player))) {
        						player.sendMessage("§eKing§6 : Hey, go back to my castle if you wan't to move me !");
        					}

        					}
        				
          
        				}
        		}
        			
        			
    	} else if(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(player)){
        	player.closeInventory();

			player.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.ALREADY_PLACE_KING);

    	}
    		if ((FallenKingdom.getInstance().getWorldGuard().getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation()).size() == 0
					&&
					(GameState.isState(GameState.Day1)|| GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))
					&& !(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(player)))){
						player.sendMessage("§9King§6 : Hey, go back to my castle if you wan't to move me !");

			    }
    			else return;
    	}
    	
         	if(!(event.getCurrentItem().getType().equals(null))&& event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cLet the king at his actual place.")){
         		event.setCancelled(true);
         		player.closeInventory();
         	}


        if(!event.getCurrentItem().hasItemMeta() || event.getCursor().getType().equals(null)) return;
        if(!(event.getSlot() <= 20) && !(event.getSlot() >= 0))return;
        if(event.getCurrentItem().getItemMeta().getDisplayName().equals(null)) return;
        	if(!(event.getCurrentItem().getType().equals(null))&& event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Join the Blue team !")){
				player.closeInventory();
	        	event.setCancelled(true);

        		if(!(FallenKingdom.getInstance().Blue.getPlayers().contains(player))){	
        			
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
					FallenKingdom.getInstance().addPlayer(player, FallenKingdom.getInstance().Blue);
					FallenKingdom.getInstance().ListBlue ++;
					new Sounds(player).playSound(Sound.NOTE_STICKS);

					player.sendMessage("§6You are now in the §9Blue§6 Team");
					
					player.closeInventory();
					
					
				} else {
					new Sounds(player).playSound(Sound.VILLAGER_NO);

					player.sendMessage("§6Already in this team");;
					player.closeInventory();
				}
        	}
        	else if(event.getCurrentItem().getType() != null &&event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cJoin the Red team !")){
				player.closeInventory();
	        	event.setCancelled(true);

        		if(!(FallenKingdom.getInstance().Red.getPlayers().contains(player))){	
        			
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
					FallenKingdom.getInstance().addPlayer(player, FallenKingdom.getInstance().Red);
					FallenKingdom.getInstance().ListRed ++;
					new Sounds(player).playSound(Sound.NOTE_STICKS);

					player.sendMessage( "§6You are now in the §cRed §6Team");
				} else {
					new Sounds(player).playSound(Sound.VILLAGER_NO);

					player.sendMessage("§6Already in this team");;
				}
        	}
        	else if(event.getCurrentItem().getType() != null &&event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aJoin the Green team !")){
				player.closeInventory();
	        	event.setCancelled(true);

				if(!(FallenKingdom.getInstance().Green.getPlayers().contains(player))){	
				
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
					FallenKingdom.getInstance().addPlayer(player, FallenKingdom.getInstance().Green);
					FallenKingdom.getInstance().ListGreen ++;
					FallenKingdom.getInstance();
					new Sounds(player).playSound(Sound.NOTE_STICKS);

					player.sendMessage( "§6You are now in the §aGreen §6Team");
				} else {
					new Sounds(player).playSound(Sound.VILLAGER_NO);

					player.sendMessage("§6Already in this team");;
				}
        }
        	else if(event.getCurrentItem().getType() != null &&event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eJoin the Yellow team !")){
				player.closeInventory();
	        	event.setCancelled(true);

				if(!(FallenKingdom.getInstance().Yellow.getPlayers().contains(player))){
				
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
						FallenKingdom.getInstance().ListGreen--;
					}
					if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
						FallenKingdom.getInstance().Yellow.removePlayer(player);
						FallenKingdom.getInstance().ListYellow --;
					}
					FallenKingdom.getInstance().addPlayer(player, FallenKingdom.getInstance().Yellow);
					FallenKingdom.getInstance().ListYellow ++;
					new Sounds(player).playSound(Sound.NOTE_STICKS);

					player.sendMessage( "§6You are now in the §eYellow §6Team");
				} else {
					new Sounds(player).playSound(Sound.VILLAGER_NO);
					player.sendMessage("§6Already in this team");;
				}
        	} else if(!(event.getCurrentItem().getType().equals(null))&& event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Règles de Victoire")){
	event.setCancelled(true);
        	} else if(!(event.getCurrentItem().getType().equals(null))&& event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Règles du Temps")){
	event.setCancelled(true);
        	} else if(!(event.getCurrentItem().getType().equals(null))&& event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Placement du roi !")){
	event.setCancelled(true);
        	}
    	
    	}


	
	@EventHandler
	public void ArmorStandDamage(EntityDamageByEntityEvent event){
	
		
		if(event.getEntityType().equals(EntityType.ARMOR_STAND)){
			event.setCancelled(true);
		}
		
		double finalDamage = event.getFinalDamage();
		
		

		if(event.getDamager() instanceof Player){
		Player player = (Player)event.getDamager();	
		
		if(event.getEntity().getName().equals("§6Roi §9Bleu")){
			event.setCancelled(true);
		}
		if(event.getEntity().getName().equals("§6Roi §cRouge")){
			event.setCancelled(true);
		}
		if(event.getEntity().getName().equals("§6Roi §eJaune")){
			event.setCancelled(true);
		}
		if(event.getEntity().getName().equals("§6Roi §aVert")){
			event.setCancelled(true);
		}
		
			if(event.getEntityType().equals(EntityType.VILLAGER)){
				if(event.getEntity().getName().equals("VilBleu")){
					
					if(FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
						event.setCancelled(true);
						player.sendMessage("§9King §6: Don't hit me !");
					}
					if(!FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
						if(GameState.isState(GameState.LOBBY) || GameState.isState(GameState.Day1) || GameState.isState(GameState.Day2)
							|| GameState.isState(GameState.Day3)){
								event.setCancelled(true);
								player.sendMessage(FallenKingdom.Lang.WAIT_FOURDAYS + "");
							} else {
						event.setCancelled(true);
						lifeKingBlue = lifeKingBlue - (finalDamage);
						if(lifeKingBlue < 1000 && lifeKingBlue > 1000-(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.KING_ATTACK +"");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 has damaged the §9Blue§6 King");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 has damaged the §9Blue§6 King");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 has damaged the §9Blue§6 King");
								}
						}
						
						if(lifeKingBlue < 750 && lifeKingBlue > 750 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage("§6Your king have 750 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 750 PV");
								}
							

								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 750 PV");
								}

								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 750 PV");
								
								
							}
						}
						if(lifeKingBlue < 500 && lifeKingBlue > 500 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage("§6Your king have 500 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 500 PV");
								
							}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 500 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 500 PV");
								
							}
						}
						if(lifeKingBlue < 250 && lifeKingBlue > 250 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage("§6Your king have 250 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 250 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 250 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 250 PV");
								}
							
						}
						if(lifeKingBlue < 50 && lifeKingBlue > 50 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage("§6Your king have 50 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 50 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 50 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §9Blue §6 have 50 PV");
								}
							
						}

						if(lifeKingBlue <= 1 ){
							Bukkit.broadcastMessage(FallenKingdom.Lang.PREFIX + "§r "+player.getDisplayName() +" §6 a tué le roi §9Bleu §6félicitations !");
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Your king is dead, if you die, you can't respawn now !");
							}
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §9Blue §6is dead, his soldier can't respawn now !");
							}	
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §9Blue §6is dead, his soldier can't respawn now !");
							}
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §9Blue §6is dead, his soldier can't respawn now !");
							}
							for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
					         	String entity1 = entity.getCustomName();
					         	if(!entity.isCustomNameVisible()){
					    			 if(entity instanceof Villager){

					        		if(entity.getCustomName().equalsIgnoreCase("VilBleu")){
					        			      ((Villager)entity).setHealth(0);
					        			      ((Villager)entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000000, 255));
					        			    }
					        		} else 
							         	if(entity instanceof ArmorStand) { 
					        			if(entity1.contains("JBleu")){
							         		entity.remove();
					        			}
						           
							         	}
					        	}
					         	
					         	}
							
						}
						}
					}
			            	
						
						
					}
			
				if(event.getEntity().getName().equals("VilRouge")){
					if(FallenKingdom.getInstance().Red.getPlayers().contains(player)){
						event.setCancelled(true);
						player.sendMessage("§cKing §6: Don't hit me !");
					}
					if(!FallenKingdom.getInstance().Red.getPlayers().contains(player)){
						if(GameState.isState(GameState.LOBBY) || GameState.isState(GameState.Day1) || GameState.isState(GameState.Day2)
								|| GameState.isState(GameState.Day3)){
									event.setCancelled(true);
									player.sendMessage("" + FallenKingdom.Lang.WAIT_FOURDAYS);
								} else {
						event.setCancelled(true);
						lifeKingRed = lifeKingRed - (finalDamage);
						
						if(lifeKingRed < 1000 && lifeKingRed > 1000-(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.KING_ATTACK +"");
							}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 have damaged the §cRed §6King");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 have damaged the §cRed §6King");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 have damaged the §cRed §6King");
								}
						}
						if(lifeKingRed < 750 && lifeKingRed > 750 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage("§6Your king have 750 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 750 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 750 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 750 PV");
								}
							
						}
						if(lifeKingRed < 500 && lifeKingRed > 500 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage("§6Your king have 500 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 500 PV");
								
							}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 500 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 500 PV");
								}
							
						}
						if(lifeKingRed < 250 && lifeKingRed > 250 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage("§6Your king have 250 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 250 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 250 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 250 PV");
								}
							
						}
						if(lifeKingRed < 50 && lifeKingRed > 50 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage("§6Your king have 50 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 50 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 50 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §cRed §6 have 50 PV");
								}
							
						}

						if(lifeKingRed < 1 ){
							Bukkit.broadcastMessage(FallenKingdom.Lang.PREFIX + "§r "+player.getDisplayName() +" §6 a tué le roi §cRouge §6félicitations !");
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "Your king is dead, if you die, you can't respawn now !");
							}
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §cRed §6is dead, his soldier can't respawn now !");
							}	
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §cRed §6is dead, his soldier can't respawn now !");
							}
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §cRed §6is dead, his soldier can't respawn now !");
							}							
							for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
					        	String entity1 = entity.getCustomName();
					        	if(!entity.isCustomNameVisible()){
					    			 if(entity instanceof Villager){

					        		if(entity.getCustomName().equalsIgnoreCase("VilRouge")){
					        			      ((Villager)entity).setHealth(0);
					        			      ((Villager)entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000000, 255));
					        			    }
					        		} else 
							         	if(entity instanceof ArmorStand) { 
					        			if(entity1.contains("Rouge")){
							         		entity.remove();
					        			}
						           
							         	}
					        	}
					        	
					        }
						
						}
					}
			}
				if(event.getEntity().getName().equals("VilVert")){
					if(FallenKingdom.getInstance().Green.getPlayers().contains(player)){
						event.setCancelled(true);
						player.sendMessage("§aKing §6: Don't hit me !");
					}
					if(!FallenKingdom.getInstance().Green.getPlayers().contains(player)){
						if(GameState.isState(GameState.LOBBY) || GameState.isState(GameState.Day1) || GameState.isState(GameState.Day2)
								|| GameState.isState(GameState.Day3)){
									event.setCancelled(true);
									player.sendMessage(FallenKingdom.Lang.WAIT_FOURDAYS+"");
								} else {
						event.setCancelled(true);
						lifeKingGreen = lifeKingGreen - (finalDamage);
						if(lifeKingGreen < 1000 && lifeKingGreen > 1000-(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.KING_ATTACK + "");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 have damaged the §aGreen §6King");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 have damaged the §aGreen §6King");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 have damaged the §aGreen §6King");
								}
							
						}
						if(lifeKingGreen < 750 && lifeKingGreen > 750 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage("§6Your king have 750 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 750 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 750 PV");
								}

								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 750 PV");
								
								
							}
						}
						if(lifeKingGreen < 500 && lifeKingGreen > 500 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage("§6Your king have 500 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 500 PV");
								
							}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 500 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 500 PV");
								
							}
						}
						if(lifeKingGreen < 250 && lifeKingGreen > 250 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage("§6Your king have 250 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 250 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 250 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 250 PV");
								}
							
						}
						if(lifeKingGreen < 50 && lifeKingGreen > 50 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage("§6Your king have 50 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 50 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 50 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6King §aGreen§6 have 50 PV");
								}
							
						}

						if(lifeKingGreen <= 1 ){
							Bukkit.broadcastMessage(FallenKingdom.Lang.PREFIX + "§r" + player.getDisplayName() +" §6 a tué le roi §aVert §6félicitations !");
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "Your king is dead, if you die, you can't respawn now !");
							}
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §aGreen §6is dead, his soldier can't respawn now !");
							}	
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §aGreen §6is dead, his soldier can't respawn now !");
							}
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §aGreen §6is dead, his soldier can't respawn now !");
							}							
							 for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
						        	String entity1 = entity.getCustomName();
						        	if(!entity.isCustomNameVisible()){
						    			 if(entity instanceof Villager){

						        		if(entity.getCustomName().equalsIgnoreCase("VilVert")){
						        			      ((Villager)entity).setHealth(0);
						        			      ((Villager)entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000000, 255));
						        			    }
						        		} else 
								         	if(entity instanceof ArmorStand) { 
						        			if(entity1.contains("Vert")){
								         		entity.remove();
						        			}
							           
								         	}
						        	}
						        	
						        }
						
						}
						
				}
					}
				if(event.getEntity().getName().equals("VilJaune")){
					if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
						event.setCancelled(true);
						player.sendMessage("§eKing §6: Don't hit me !");
					}
					if(!FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
						if(GameState.isState(GameState.LOBBY) || GameState.isState(GameState.Day1) || GameState.isState(GameState.Day2)
								|| GameState.isState(GameState.Day3)){
									event.setCancelled(true);
									player.sendMessage(FallenKingdom.Lang.WAIT_FOURDAYS+"");
								} else {
						event.setCancelled(true);
						lifeKingYellow = lifeKingYellow - (finalDamage);
						if(lifeKingYellow < 1000 && lifeKingYellow > 1000-(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.KING_ATTACK + "");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 have damaged the §eYellow §6King");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 have damaged the §eYellow §6King");
								}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " + player.getDisplayName()  + " §6 have damaged the §eYellow §6King");
								}
							
						}
						if(lifeKingYellow < 750 && lifeKingYellow > 750 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage("§6Your king have 750 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 750 PV");
								}
							

								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 750 PV");
								}

								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 750 PV");
								
								
							}
						}
						if(lifeKingYellow < 500 && lifeKingYellow > 500 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage("§6Your king have 500 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 500 PV");
								
							}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 500 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 500 PV");
								
							}
						}
						if(lifeKingYellow < 250 && lifeKingYellow > 250 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage("§6Your king have 250 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 250 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 250 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 250 PV");
								}
							
						}
						if(lifeKingYellow < 50 && lifeKingYellow > 50 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage("§6Your king have 50 PV left !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 50 PV");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 50 PV");
								}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6King §eYellow§6 have 50 PV");
								}
							
						}

						if(lifeKingYellow <= 1 ){
							Bukkit.broadcastMessage(FallenKingdom.Lang.PREFIX + "§r "+player.getDisplayName() +" §6 a tué le roi §eJaune §6félicitations !");
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "Your king is dead, if you die, you can't respawn now !");
							}
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §eYellow §6is dead, his soldier can't respawn now !");
							}	
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §eYellow §6is dead, his soldier can't respawn now !");
							}
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §eYellow §6is dead, his soldier can't respawn now !");
							}							
							 for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
						        	String entity1 = entity.getCustomName();
						        	if(!entity.isCustomNameVisible()){
						    			 if(entity instanceof Villager){

						        		if(entity.getCustomName().equalsIgnoreCase("VilJaune")){
						        			      ((Villager)entity).setHealth(0);
						        			      ((Villager)entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000000, 255));
						        			    }
						        		} else 
								         	if(entity instanceof ArmorStand) { 
						        			if(entity1.contains("Jaune")){
								         		entity.remove();
						        			}
							           
								         	}
						        	}
						        	
						        }
							
						
						}
						}
						
						
					}
		}
			}
				if(event.getEntityType().equals(EntityType.ARMOR_STAND)){
					if(event.getEntity().getName().equals("§6Roi §9Bleu")){
						if(FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
							event.setCancelled(true);
							player.sendMessage("§9Roi §6: Ne me tape pas !");

						}else if(!FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
							event.setCancelled(true);

						}}
						if(event.getEntity().getName().equals("§6Roi §cRouge")){
						if(FallenKingdom.getInstance().Red.getPlayers().contains(player)){
							event.setCancelled(true);
							player.sendMessage("§cRoi §6: Ne me tape pas !");

						}else if(!FallenKingdom.getInstance().Red.getPlayers().contains(player)){
							event.setCancelled(true);

						}} 
						if(event.getEntity().getName().equals("§6Roi §aVert")){
							if(FallenKingdom.getInstance().Green.getPlayers().contains(player)){
						
							event.setCancelled(true);
							player.sendMessage("§aRoi §6: Ne me tape pas !");

						}else if(!FallenKingdom.getInstance().Green.getPlayers().contains(player)){
							event.setCancelled(true);
						}
						}
						if(event.getEntity().getName().equals("§6Roi §eJaune")){
							if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
						
							event.setCancelled(true);
							player.sendMessage("§eRoi §6: Ne me tape pas !");

						}else if(!FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
							event.setCancelled(true);

						} 
						
						}
						}
					
			}  
					
						
				
			}
		}  else if(event.getCause().equals(DamageCause.ENTITY_EXPLOSION)){
			
			if(event.getEntity().getName().equals("§6Roi §9Bleu")){
				event.setCancelled(true);
			}
			if(event.getEntity().getName().equals("§6Roi §cRouge")){
				event.setCancelled(true);
			}
			if(event.getEntity().getName().equals("§6Roi §eJaune")){
				event.setCancelled(true);
			}
			if(event.getEntity().getName().equals("§6Roi §aVert")){
				event.setCancelled(true);
			}
			
				if(event.getEntityType().equals(EntityType.VILLAGER)){
					if(event.getEntity().getName().equals("VilBleu")){
					
							event.setCancelled(true);
							lifeKingBlue = lifeKingBlue - (finalDamage);
							if(lifeKingBlue < 1000 && lifeKingBlue > 1000-(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.KING_ATTACK +"");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§9Blue§6 King is attacked");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§9Blue§6 King is attacked");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§9Blue§6 King is attacked");
							}
							
							if(lifeKingBlue < 750 && lifeKingBlue > 750 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Your king have 750 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 750 PV");
									}
								

									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 750 PV");
									}

									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 750 PV");
									
									
								}
							}
							if(lifeKingBlue < 500 && lifeKingBlue > 500 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Your king have 500 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 500 PV");
									
								}
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 500 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 500 PV");
									
								}
							}
							if(lifeKingBlue < 250 && lifeKingBlue > 250 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Your king have 250 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 250 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 250 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 250 PV");
									}
								
							}
							if(lifeKingBlue < 50 && lifeKingBlue > 50 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Your king have 50 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 50 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 50 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §9Blue §6 have 50 PV");
									}
								
							}

							if(lifeKingBlue <= 1 ){
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "Your king is dead, if you die, you can't respawn now !");
								}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §9Blue §6is dead, his soldier can't respawn now !");
								}	
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §9Blue §6is dead, his soldier can't respawn now !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §9Blue §6is dead, his soldier can't respawn now !");
								}
								for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
						         	String entity1 = entity.getCustomName();
						         	if(!entity.isCustomNameVisible()){
						    			 if(entity instanceof Villager){

						        		if(entity.getCustomName().equalsIgnoreCase("VilBleu")){
						        			      ((Villager)entity).setHealth(0);
						        			      ((Villager)entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000000, 255));
						        			    }
						        		} else 
								         	if(entity instanceof ArmorStand) { 
						        			if(entity1.contains("Bleu")){
								         		entity.remove();
						        			}
							           
								         	}
						        	}
						         	
						         	}
								
							}
							
						}
				            	
							
							
						}
				
					if(event.getEntity().getName().equals("VilRouge")){
						
		
							event.setCancelled(true);
							lifeKingRed = lifeKingRed - (finalDamage);
							
							if(lifeKingRed < 1000 && lifeKingRed > 1000-(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.KING_ATTACK +"");
								}
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§cRed§6 King is attacked");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§cRed§6 King is attacked");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§cRed§6 King is attacked");
									}
							}
							if(lifeKingRed < 750 && lifeKingRed > 750 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Your king have 750 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 750 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 750 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 750 PV");
									}
								
							}
							if(lifeKingRed < 500 && lifeKingRed > 500 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Your king have 500 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 500 PV");
									
								}
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 500 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 500 PV");
									}
								
							}
							if(lifeKingRed < 250 && lifeKingRed > 250 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Your king have 250 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 250 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 250 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 250 PV");
									}
								
							}
							if(lifeKingRed < 50 && lifeKingRed > 50 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Your king have 50 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 50 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 50 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §cRed §6 have 50 PV");
									}
								
							}

							if(lifeKingRed < 1 ){
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "Your king is dead, if you die, you can't respawn now !");
								}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §cRed §6is dead, his soldier can't respawn now !");
								}	
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §cRed §6is dead, his soldier can't respawn now !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §cRed §6is dead, his soldier can't respawn now !");
								}							
								for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
						        	String entity1 = entity.getCustomName();
						        	if(!entity.isCustomNameVisible()){
						    			 if(entity instanceof Villager){

						        		if(entity.getCustomName().equalsIgnoreCase("VilRouge")){
						        			      ((Villager)entity).setHealth(0);
						        			      ((Villager)entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000000, 255));
						        			    }
						        		} else 
								         	if(entity instanceof ArmorStand) { 
						        			if(entity1.contains("Rouge")){
								         		entity.remove();
						        			}
							           
								         	}
						        	}
						        	
						        
							
							}
						}
				}
					if(event.getEntity().getName().equals("VilVert")){
						
						
							event.setCancelled(true);
							lifeKingGreen = lifeKingGreen - (finalDamage);
							if(lifeKingGreen < 1000 && lifeKingGreen > 1000-(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.KING_ATTACK + "");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§aGreen§6 King is attacked");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§aGreen§6 King is attacked");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§aGreen§6 King is attacked");
									}
								
							}
							if(lifeKingGreen < 750 && lifeKingGreen > 750 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Your king have 750 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 750 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 750 PV");
									}

									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 750 PV");
									
									
								}
							}
							if(lifeKingGreen < 500 && lifeKingGreen > 500 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Your king have 500 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 500 PV");
									
								}
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 500 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 500 PV");
									
								}
							}
							if(lifeKingGreen < 250 && lifeKingGreen > 250 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Your king have 250 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 250 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 250 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 250 PV");
									}
								
							}
							if(lifeKingGreen < 50 && lifeKingGreen > 50 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Your king have 50 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 50 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 50 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
										player1.sendMessage("§6King §aGreen§6 have 50 PV");
									}
								
							}

							if(lifeKingGreen <= 1 ){
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "Your king is dead, if you die, you can't respawn now !");
								}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §aGreen §6is dead, his soldier can't respawn now !");
								}	
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §aGreen §6is dead, his soldier can't respawn now !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §aGreen §6is dead, his soldier can't respawn now !");
								}							
								 for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
							        	String entity1 = entity.getCustomName();
							        	if(!entity.isCustomNameVisible()){
							    			 if(entity instanceof Villager){

							        		if(entity.getCustomName().equalsIgnoreCase("VilVert")){
							        			      ((Villager)entity).setHealth(0);
							        			      ((Villager)entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000000, 255));
							        			    }
							        		} else 
									         	if(entity instanceof ArmorStand) { 
							        			if(entity1.contains("Vert")){
									         		entity.remove();
							        			}
								           
									         	}
							        	}  
							        	
							        
							
							}
							
					}
						}
					if(event.getEntity().getName().equals("VilJaune")){
						
							event.setCancelled(true);
							lifeKingYellow = lifeKingYellow - (finalDamage);
							if(lifeKingYellow < 1000 && lifeKingYellow > 1000-(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.KING_ATTACK + "");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§eYellow§6 King is attacked");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§eYellow§6 King is attacked");
									}
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage(FallenKingdom.Lang.PREFIX + "§r " +"§eYellow§6 King is attacked");
									}
								
							}
							if(lifeKingYellow < 750 && lifeKingYellow > 750 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Your king have 750 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 750 PV");
									}
								

									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 750 PV");
									}

									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 750 PV");
									
									
								}
							}
							if(lifeKingYellow < 500 && lifeKingYellow > 500 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Your king have 500 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 500 PV");
									
								}
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 500 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 500 PV");
									
								}
							}
							if(lifeKingYellow < 250 && lifeKingYellow > 250 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Your king have 250 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 250 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 250 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 250 PV");
									}
								
							}
							if(lifeKingYellow < 50 && lifeKingYellow > 50 -(finalDamage+1)){
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Your king have 50 PV left !");
								}
									for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 50 PV");
									}
								
									for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 50 PV");
									}
									for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
										player1.sendMessage("§6King §eYellow§6 have 50 PV");
									}
								
							}

							if(lifeKingYellow <= 1 ){
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "Your king is dead, if you die, you can't respawn now !");
								}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §eYellow §6is dead, his soldier can't respawn now !");
								}	
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §eYellow §6is dead, his soldier can't respawn now !");
								}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage(FallenKingdom.Lang.PREFIX + "King §eYellow §6is dead, his soldier can't respawn now !");
								}							
								 for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
							        	String entity1 = entity.getCustomName();
							        	if(!entity.isCustomNameVisible()){
							    			 if(entity instanceof Villager){

							        		if(entity.getCustomName().equalsIgnoreCase("VilJaune")){
							        			      ((Villager)entity).setHealth(0);
							        			      ((Villager)entity).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 1000000, 255));
							        			    }
							        		} else 
									         	if(entity instanceof ArmorStand) { 
							        			if(entity1.contains("Jaune")){
									         		entity.remove();
							        			}
								           
									         	}
							        	}
							        	
							        }
								
							
							}
							
							
							
						}
			}
				
					if(event.getEntityType().equals(EntityType.ARMOR_STAND)){
						if(event.getEntity().getName().equals("§6Roi §9Bleu")){
								event.setCancelled(true);

							}
							if(event.getEntity().getName().equals("§6Roi §cRouge")){
							event.setCancelled(true);

							} 
							if(event.getEntity().getName().equals("§6Roi §aVert")){
								event.setCancelled(true);
							}
							
							if(event.getEntity().getName().equals("§6Roi §eJaune")){
								
								event.setCancelled(true);

							} 
							
							}
							}
						
				
	}
							
					
			
				

						
						
						
					
			
					
						
				
			
		
	
	
	@EventHandler
	public void armorStandDamage(EntityDamageEvent event)
	{
		if(event.getEntity().isCustomNameVisible()){
			if(event.getEntity().getCustomName().equals("VilVert") ||
					event.getEntity().getCustomName().equals("VilRouge") ||
					event.getEntity().getCustomName().equals("VilBleu") ||
					event.getEntity().getCustomName().equals("VilJaune")){
				event.setCancelled(true);
			}
		}
		if(event.getEntityType().equals(EntityType.ARMOR_STAND) || event.getEntityType().equals(EntityType.VILLAGER)){
			event.setCancelled(true);
		}
		
	}
	@EventHandler
	public void armorStandDamage(EntityDamageByBlockEvent event)
	{
		if(event.getEntityType().equals(EntityType.ARMOR_STAND)){
			event.setCancelled(true);
		}
		if(event.getEntity().isCustomNameVisible()){
			if(event.getEntity().getCustomName().equals("VilVert") ||
					event.getEntity().getCustomName().equals("VilRouge") ||
					event.getEntity().getCustomName().equals("VilBleu") ||
					event.getEntity().getCustomName().equals("VilJaune")){
				event.setCancelled(true);
			}
			if(event.getEntityType().equals(EntityType.VILLAGER)){
				double finalDamage = event.getFinalDamage();

				if(event.getEntity().getName().equals("VilBleu")){

						event.setCancelled(true);
						lifeKingBlue = lifeKingBlue - (finalDamage);
						if(lifeKingBlue < 1000 && lifeKingBlue > 1000-(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage("Votre roi est attaqué !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu§6 est attaqué");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu§6 est attaqué");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu§6 est attaqué");
								}
						}
						
						if(lifeKingBlue < 750 && lifeKingBlue > 750 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 750 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 750 points de vie !");
								}
							

								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 750 points de vie !");
								}

								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 750 points de vie !");
								
								
							}
						}
						if(lifeKingBlue < 500 && lifeKingBlue > 500 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 500 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 500 points de vie !");
								
							}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 500 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 500 points de vie !");
								
							}
						}
						if(lifeKingBlue < 250 && lifeKingBlue > 250 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "Votre roi n'a plus que 250 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 250 points de vie !");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 250 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 250 points de vie !");
								}
							
						}
						if(lifeKingBlue < 50 && lifeKingBlue > 50 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 50 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 50 points de vie !");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 50 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §9Bleu §6 n'a plus que 50 points de vie !");
								}
							
						}

						if(lifeKingBlue <= 1 ){
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Votre roi est mort, si vous mourrez vous ne réaparraitrez plus !");
							}
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §9Bleu §6est mort, ses soldats ne peuvent plus revivre !");
							}	
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §9Bleu §6est mort, ses soldats ne peuvent plus revivre !");
							}
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §9Bleu §6est mort, ses soldats ne peuvent plus revivre !");
							}
							
							//Create a timer for make effet
							for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
					            	String entity1 = entity.getCustomName();
									if(entity.isCustomNameVisible()){

					            	if(entity1.equals("§6Roi §9Bleu") ){
					                entity.remove();
				            		Bukkit.dispatchCommand(FallenKingdom.console, "kill @e[type=Villager,name=VilBleu");
				            		Bukkit.getWorld(FallenKingdom.getInstance().World).playSound(entity.getLocation(), Sound.WITHER_DEATH, 3.0F, 0.533F);
				            		
					            	} 
					            	  
									}
									
					            } 
							
						}
						}
					
			            	
						
						
					
			
				if(event.getEntity().getName().equals("VilRouge")){
											
						event.setCancelled(true);
						lifeKingRed = lifeKingRed - (finalDamage);
						
						if(lifeKingRed < 1000 && lifeKingRed > 1000-(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge§6 est attaqué");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge§6 est attaqué");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge§6 est attaqué");
								}
						}
						if(lifeKingRed < 750 && lifeKingRed > 750 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage("Votre roi n'a plus que 750 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 750 points de vie !");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 750 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 750 points de vie !");
								}
							
						}
						if(lifeKingRed < 500 && lifeKingRed > 500 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 500 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 500 points de vie !");
								
							}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 500 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 500 points de vie !");
								}
							
						}
						if(lifeKingRed < 250 && lifeKingRed > 250 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 250 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 250 points de vie !");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 250 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 250 points de vie !");
								}
							
						}
						if(lifeKingRed < 50 && lifeKingRed > 50 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 50 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 50 points de vie !");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 50 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §cRouge §6 n'a plus que 50 points de vie !");
								}
							
						}

						if(lifeKingRed < 1 ){
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Votre roi est mort, si vous mourrez vous ne réaparraitrez plus !");
							}
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §cRouge §6est mort, ses soldats ne peuvent plus revivre !");
							}	
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §cRouge §6est mort, ses soldats ne peuvent plus revivre !");
							}
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §cRouge §6est mort, ses soldats ne peuvent plus revivre !");
							}							
							for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
								
								if(entity.isCustomNameVisible()){
					            	String entity1 = entity.getCustomName();
					            	if(entity1.equals("§6Roi §cRouge")){
					            		entity.remove();
					            		Bukkit.dispatchCommand(FallenKingdom.console, "kill @e[type=Villager,name=VilRouge");
					            		Bukkit.getWorld(FallenKingdom.getInstance().World).playSound(entity.getLocation(), Sound.WITHER_DEATH, 3.0F, 0.533F);
					            	}
				            		
			            } 
							
						
						}
						
						}
				}
			
				if(event.getEntity().getName().equals("VilVert")){
					
						event.setCancelled(true);
						lifeKingGreen = lifeKingGreen - (finalDamage);
						if(lifeKingGreen < 1000 && lifeKingGreen > 1000-(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "Votre roi est attaqué !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §aVert§6 est attaqué");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §aVert§6 est attaqué");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §aVert§6 est attaqué");
								}
							
						}
						if(lifeKingGreen < 750 && lifeKingGreen > 750 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 750 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 750 points de vie !");
								}
							

								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 750 points de vie !");
								}

								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 750 points de vie !");
								
								
							}
						}
						if(lifeKingGreen < 500 && lifeKingGreen > 500 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 500 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 500 points de vie !");
								
							}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 500 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 500 points de vie !");
								
							}
						}
						if(lifeKingGreen < 250 && lifeKingGreen > 250 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage(FallenKingdom.Lang.PREFIX + "Votre roi n'a plus que 250 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 250 points de vie !");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 250 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 250 points de vie !");
								}
							
						}
						if(lifeKingGreen < 50 && lifeKingGreen > 50 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 50 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 50 points de vie !");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 50 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
									player1.sendMessage("§6Le roi §aVert §6 n'a plus que 50 points de vie !");
								}
							
						}

						if(lifeKingGreen <= 1 ){
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Votre roi est mort, si vous mourrez vous ne réaparraitrez plus !");
							}
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §aVert §6est mort, ses soldats ne peuvent plus revivre !");
							}	
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §aVert §6est mort, ses soldats ne peuvent plus revivre !");
							}
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §aVert §6est mort, ses soldats ne peuvent plus revivre !");
							}							
							for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
					            	String entity1 = entity.getCustomName();
					            	if(entity.isCustomNameVisible()){
					            	if(entity1.equals("§6Roi §aVert")){
					            		entity.remove();
					            		Bukkit.dispatchCommand(FallenKingdom.console, "kill @e[type=Villager,name=VilVert");
					            		Bukkit.getWorld(FallenKingdom.getInstance().World).playSound(entity.getLocation(), Sound.WITHER_DEATH, 3.0F, 0.533F);
					            	
					            	} 
					            } 
							}
						
						}
						
				}
					
				if(event.getEntity().getName().equals("VilJaune")){
					
						event.setCancelled(true);
						lifeKingYellow = lifeKingYellow - (finalDamage);
						if(lifeKingYellow < 1000 && lifeKingYellow > 1000-(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage("Votre roi est attaqué !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune§6 est attaqué");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune§6 est attaqué");
								}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune§6 est attaqué");
								}
							
						}
						if(lifeKingYellow < 750 && lifeKingYellow > 750 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 750 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 750 points de vie !");
								}
							

								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 750 points de vie !");
								}

								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 750 points de vie !");
								
								
							}
						}
						if(lifeKingYellow < 500 && lifeKingYellow > 500 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 500 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 500 points de vie !");
								
							}
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 500 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 500 points de vie !");
								
							}
						}
						if(lifeKingYellow < 250 && lifeKingYellow > 250 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 250 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 250 points de vie !");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 250 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 250 points de vie !");
								}
							
						}
						if(lifeKingYellow < 50 && lifeKingYellow > 50 -(finalDamage+1)){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
								player1.sendMessage("§6Votre roi n'a plus que 50 points de vie !");
							}
								for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 50 points de vie !");
								}
							
								for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 50 points de vie !");
								}
								for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
									player1.sendMessage("§6Le roi §eJaune §6 n'a plus que 50 points de vie !");
								}
							
						}

						if(lifeKingYellow <= 1 ){
							for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Votre roi est mort, si vous mourrez vous ne réaparraitrez plus !");
							}
							for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §eJaune §6est mort, ses soldats ne peuvent plus revivre !");
							}	
							for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §eJaune §6est mort, ses soldats ne peuvent plus revivre !");
							}
							for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
							player1.sendMessage(FallenKingdom.Lang.PREFIX + "Le roi §eJaune §6est mort, ses soldats ne peuvent plus revivre !");
							}							
							for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
					            	String entity1 = entity.getCustomName();
					            	if(entity.isCustomNameVisible()){
					            	if(entity1.equals("§6Roi §eJaune")){
					                entity.remove();
				            		Bukkit.dispatchCommand(FallenKingdom.console, "kill @e[type=Villager,"
				            				+ "name=VilJaune");
				            		Bukkit.getWorld(FallenKingdom.getInstance().World).playSound(entity.getLocation(), Sound.WITHER_DEATH, 3.0F, 0.533F);
				            	
					            	} 
					            	}
					            } 
							
						
						}
						}
						
						
					
		
			}
		}
	}
	@EventHandler
	public void clicArmor(PlayerInteractAtEntityEvent event){
		if(event.getRightClicked().equals(EntityType.ARMOR_STAND)){
			event.setCancelled(true);
		}
		if(event.getRightClicked().isCustomNameVisible()){
			if(event.getRightClicked().getCustomName().equalsIgnoreCase("§6Roi §cRouge")){
				event.setCancelled(true);
			}
		}
		if(event.getRightClicked().isCustomNameVisible()){
			if(event.getRightClicked().getCustomName().equalsIgnoreCase("§6Roi §aVert")){
				event.setCancelled(true);
			}
		}
		if(event.getRightClicked().isCustomNameVisible()){
			if(event.getRightClicked().getCustomName().equalsIgnoreCase("§6Roi §9Bleu")){
				event.setCancelled(true);
			}
		}
		if(event.getRightClicked().isCustomNameVisible()){
			if(event.getRightClicked().getCustomName().equalsIgnoreCase("§6Roi §eJaune")){
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void clicVillage(PlayerInteractEntityEvent event){
		Entity Villager = event.getRightClicked();
		String villagerName = Villager.getCustomName();
		if(event.getRightClicked().equals(EntityType.ARMOR_STAND)){
			event.setCancelled(true);
		}
		if(event.getRightClicked().equals(EntityType.ARMOR_STAND)){
			event.setCancelled(true);
		}
		if(villagerName.equalsIgnoreCase("VilBleu") || villagerName.equalsIgnoreCase("VilRouge")
				|| villagerName.equalsIgnoreCase("VilVert") || villagerName.equalsIgnoreCase("VilJaune")){
			event.setCancelled(true);
		}
		
	}
}
