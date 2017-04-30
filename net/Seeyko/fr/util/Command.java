package net.Seeyko.fr.util;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import net.Seeyko.fr.FallenKingdom;
import net.Seeyko.fr.FallenKingdom.Lang;
import net.Seeyko.fr.events.GameState;
import net.Seeyko.fr.game.FKGame;
import net.Seeyko.fr.join.FKJoin;

public class Command implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String msg, String[] arg) {
		
			if(sender instanceof Player){
				Player p = (Player) sender;
				HumanEntity pl = (HumanEntity) sender;
				if(cmd.getName().equalsIgnoreCase("forcestart")){
					
					if(arg.length == 0){
						 Bukkit.broadcastMessage("§b"+p.getName()+"§6 start the game !");
						FKJoin.getInstance();
						FKJoin.timer = 11;
						return true;
						
					}
					if(arg.length == 1){
						
						if(arg[0].equalsIgnoreCase("20")){
							
						FKJoin.timer = 21;
						return true;

						}
					}
				}
				if(cmd.getName().equalsIgnoreCase("rules")){
					if(arg.length == 0){
						FallenKingdom.getInstance();
						pl.openInventory(FallenKingdom.Rules);
						return true;

					}
				}
				if(cmd.getName().equalsIgnoreCase("king")){
					if(arg.length == 0){
						if(pl.getGameMode().equals(GameMode.SURVIVAL)){
				    		 if(!FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(pl)){
				     			if(GameState.isState(GameState.LOBBY) || FKGame.timer > 9995){
				     				pl.sendMessage(FallenKingdom.Lang.PREFIX + "Wait to be in the game for doing that");
				     	    		pl.closeInventory();
									return true;

				     	    	
				         		} else if(GameState.isState(GameState.Day4) || GameState.isState(GameState.Day5IF) || GameState.isState(GameState.FINISH)){
				             		pl.closeInventory();

				         			pl.sendMessage(FallenKingdom.Lang.PREFIX + "Too late, your king is already place and can't be moved");
									return true;

				         		} else if((GameState.isState(GameState.Day1) ||GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))){

				         			if(FallenKingdom.getInstance().Blue.getPlayers().contains(pl)){
				         				for (ProtectedRegion r : WGBukkit.getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation())) {
				         					String currentRegion = r.getId();
				         					if(currentRegion.equalsIgnoreCase("BaseBleu")){
				         						FallenKingdom.getInstance();
				    							pl.openInventory(FallenKingdom.King);
				    							return true;

				         					} else if ((FallenKingdom.getInstance().getWorldGuard().getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation()).size() == 0
				         						||!currentRegion.equalsIgnoreCase("BaseBleu") || currentRegion.equalsIgnoreCase("BaseRouge")|| currentRegion.equalsIgnoreCase("BaseVerte")|| currentRegion.equalsIgnoreCase("BaseJaune"))&&
				         							(GameState.isState(GameState.Day1)|| GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))
				         							&& !(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(pl))) {
				         						pl.sendMessage( FallenKingdom.Lang.PLACE_KING_EXTERNAL_BASE +"");
				        						return true;

				         					}

				         					}
				         				
				           
				         				}
				         			if(FallenKingdom.getInstance().Red.getPlayers().contains(pl)){
				         				for (ProtectedRegion r : WGBukkit.getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation())) {
				         					String currentRegion = r.getId();
				         					if(currentRegion.equalsIgnoreCase("BaseRouge")){
				         						FallenKingdom.getInstance();
				    							pl.openInventory(FallenKingdom.King);
				    							return true;

				         					} else if ((FallenKingdom.getInstance().getWorldGuard().getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation()).size() == 0
				         						|| !currentRegion.equalsIgnoreCase("BaseRouge")||currentRegion.equalsIgnoreCase("BaseBleu")|| currentRegion.equalsIgnoreCase("BaseVerte")|| currentRegion.equalsIgnoreCase("BaseJaune"))&&
				         							(GameState.isState(GameState.Day1)|| GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))
				         							&& !(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(pl))) {
				         						pl.sendMessage( FallenKingdom.Lang.PLACE_KING_EXTERNAL_BASE +"");
				        						return true;

				         					}

				         					}
				         				
				           
				         				}
				         			if(FallenKingdom.getInstance().Green.getPlayers().contains(pl)){
				         				for (ProtectedRegion r : WGBukkit.getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation())) {
				         					String currentRegion = r.getId();
				         					if(currentRegion.equalsIgnoreCase("BaseVert")){

				         						FallenKingdom.getInstance();
				    							pl.openInventory(FallenKingdom.King);
				    							return true;

				         					} else if (((FallenKingdom.getInstance().getWorldGuard().getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation()).size() == 0
				         							|| !currentRegion.equalsIgnoreCase("BaseVert")|| currentRegion.equalsIgnoreCase("BaseRouge")|| currentRegion.equalsIgnoreCase("BaseVerte")|| currentRegion.equalsIgnoreCase("BaseJaune"))&&
				         							(GameState.isState(GameState.Day1)|| GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))
				         							&& !(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(pl)))) {
				         						pl.sendMessage( FallenKingdom.Lang.PLACE_KING_EXTERNAL_BASE +"");
				        						return true;

				         					}

				         					}
				         				
				           
				         				}
				         			if(FallenKingdom.getInstance().Yellow.getPlayers().contains(pl)){
				         				for (ProtectedRegion r : WGBukkit.getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation())) {
				         					String currentRegion = r.getId();
				         					if(currentRegion.equalsIgnoreCase("BaseJaune")){
				         						FallenKingdom.getInstance();
				    							pl.openInventory(FallenKingdom.King);						return true;

				         					} else if ((FallenKingdom.getInstance().getWorldGuard().getRegionManager(pl.getWorld()).getApplicableRegions(pl.getLocation()).size() == 0
				         						|| !(currentRegion.equalsIgnoreCase("BaseJaune"))|| currentRegion.equalsIgnoreCase("BaseRouge")|| currentRegion.equalsIgnoreCase("BaseVerte")|| currentRegion.equalsIgnoreCase("BaseJaune"))&&
				         							(GameState.isState(GameState.Day1)|| GameState.isState(GameState.Day2) || GameState.isState(GameState.Day3))
				         							&& !(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(pl))) {
				         						pl.sendMessage( FallenKingdom.Lang.PLACE_KING_EXTERNAL_BASE +"");
				        						return true;

				         					}

				         					}
				         				
				           
				         				}
				         		}
				         			
				         			
				     	} else if(FallenKingdom.getInstance().listPlayerWhoSpawnKing.contains(pl)){
				         	pl.closeInventory();

				 			pl.sendMessage(FallenKingdom.Lang.PREFIX + "" + FallenKingdom.Lang.ALREADY_PLACE_KING);
							return true;

				     	}
				     	
							
							
						} else return false;
					
						
						
					}
				}
				
				if(cmd.getName().equalsIgnoreCase("BaseSpawn")){
					if(pl.isOp()){
					if(arg.length == 0){
					double x = pl.getLocation().getX();
					double y = pl.getLocation().getY();
					double z = pl.getLocation().getZ();
					FlagRegion.RegionBleu((Player)pl, Bukkit.getWorld(FallenKingdom.getInstance().World));
					FallenKingdom.getInstance().getConfig().set("Spawn.x", x);
					FallenKingdom.getInstance().getConfig().set("Spawn.y", y);
					FallenKingdom.getInstance().getConfig().set("Spawn.z",z);
					FallenKingdom.getInstance().saveConfig();
					Bukkit.broadcastMessage(Lang.PREFIX + "§b" + pl.getName() + " §6 have defined the Spawn");
					return true;


					}
					} else {
						pl.sendMessage("§6Only §cOP§6 can do this !");
						return true;

					}
				}
				if(cmd.getName().equalsIgnoreCase("BaseBlue")){
					if(pl.isOp()){
					if(arg.length == 0){
					double x = pl.getLocation().getX();
					double y = pl.getLocation().getY();
					double z = pl.getLocation().getZ();
					FlagRegion.RegionBleu((Player)pl, Bukkit.getWorld(FallenKingdom.getInstance().World));
					FallenKingdom.getInstance().getConfig().set("Base.blue.x", x);
					FallenKingdom.getInstance().getConfig().set("Base.blue.y", y);
					FallenKingdom.getInstance().getConfig().set("Base.blue.z",z);
					FallenKingdom.getInstance().saveConfig();
					Bukkit.broadcastMessage(Lang.PREFIX + "§b" + pl.getName() + " §6 have defined the §9Blue §6Base.");
					Bukkit.broadcastMessage("§6Don't forger to restart the server once every base have been defined !");

					return true;


					}
					} else {
						pl.sendMessage("§6Only §cOP§6 can do this !");
						return true;

					}
				}
				if(cmd.getName().equalsIgnoreCase("BaseRed")){
					if(pl.isOp()){

					if(arg.length == 0){
					FlagRegion.RegionRouge((Player)pl, Bukkit.getWorld(FallenKingdom.getInstance().World));	
					double x = pl.getLocation().getX();
					double y = pl.getLocation().getY();
					double z = pl.getLocation().getZ();
					FallenKingdom.getInstance().getConfig().set("Base.red.x", x);
					FallenKingdom.getInstance().getConfig().set("Base.red.y", y);
					FallenKingdom.getInstance().getConfig().set("Base.red.z",z);
					FallenKingdom.getInstance().saveConfig();
					Bukkit.broadcastMessage(Lang.PREFIX + "§b" + pl.getName() + " §6 have defined the §cRed §6Base.");
					Bukkit.broadcastMessage("§6Don't forger to restart the server once every base have been defined !");
					return true;

					}
					}else {
						pl.sendMessage("§6Only §cOP§6 can do this !");
						return true;

					}
				}
				if(cmd.getName().equalsIgnoreCase("BaseYellow")){
					if(pl.isOp()){

					if(arg.length == 0){
					FlagRegion.RegionJaune((Player)pl, Bukkit.getWorld(FallenKingdom.getInstance().World));	
					double x = pl.getLocation().getX();
					double y = pl.getLocation().getY();
					double z = pl.getLocation().getZ();
					FallenKingdom.getInstance().getConfig().set("Base.yellow.x", x);
					FallenKingdom.getInstance().getConfig().set("Base.yellow.y", y);
					FallenKingdom.getInstance().getConfig().set("Base.yellow.z",z);
					FallenKingdom.getInstance().saveConfig();
					Bukkit.broadcastMessage(Lang.PREFIX + "§b" + pl.getName() + " §6 have defined the §eYellow §6Base.");
					Bukkit.broadcastMessage("§6Don't forger to restart the server once every base have been defined !");
					return true;

					}
					}else {
						pl.sendMessage("§6Only §cOP§6 can do this !");
						return true;

					}
				}
				if(cmd.getName().equalsIgnoreCase("BaseGreen")){
					if(pl.isOp()){

					if(arg.length == 0){
					FlagRegion.RegionVert((Player)pl, Bukkit.getWorld(FallenKingdom.getInstance().World));				
					double x = pl.getLocation().getX();
					double y = pl.getLocation().getY();
					double z = pl.getLocation().getZ();
					FallenKingdom.getInstance().getConfig().set("Base.green.x", x);
					FallenKingdom.getInstance().getConfig().set("Base.green.y", y);
					FallenKingdom.getInstance().getConfig().set("Base.green.z",z);
					FallenKingdom.getInstance().saveConfig();
					Bukkit.broadcastMessage(Lang.PREFIX + "§b" + pl.getName() + " §6 have defined the §9Green §6Base.");
					Bukkit.broadcastMessage("§6Don't forger to restart the server once every base have been defined !");
					return true;

					}
					}else {
						pl.sendMessage("§6Only §cOP§6 can do this !");
						return true;

					}
				}
			
		}
		
		return false;
	}
}
