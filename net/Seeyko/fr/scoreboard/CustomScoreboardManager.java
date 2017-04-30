package net.Seeyko.fr.scoreboard;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import net.Seeyko.fr.FallenKingdom;
import net.Seeyko.fr.game.FKGame;
import net.Seeyko.fr.join.FKJoin;


public class CustomScoreboardManager implements ScoreboardManager {
	
	public Player player;
	public Scoreboard scoreboard;
	public Objective objective;
	public static HashMap<Player, CustomScoreboardManager> sb = new HashMap<>();
	private String name = "fighclub";
	
	public CustomScoreboardManager(Player p){
		
		this.player = p;
		this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		//Si il est dans la map on fait rien
		if(sb.containsKey(p)) return;
		//Si il n'a pas de scoreboard on lui en crée un mais custom
		sb.put(p, this);
		
		Random r = new Random();
		
		this.name = "sb." + r.nextInt(1000000000);
		
		objective = scoreboard.registerNewObjective(name, "dummy");
		objective = scoreboard.getObjective(name);
		objective.setDisplayName("§aFallen §6King§aDom");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);		
		
	}
	//element dynamique
	public void refresh(){
		
		if(FallenKingdom.getInstance().ListBlue > 0){
			objective.getScore("§9Blue §6: ").setScore(7);
		} else if(FallenKingdom.getInstance().ListBlue <1){
			for (String ligneBleu : scoreboard.getEntries()){
				if(ligneBleu.contains("Blue")){
					scoreboard.resetScores(ligneBleu);
				}
			}
		}
		if(FallenKingdom.getInstance().ListRed > 0){
			objective.getScore("§cRed §6: ").setScore(6);
		} else if(FallenKingdom.getInstance().ListRed <1){
			for (String ligneRouge : scoreboard.getEntries()){
				if(ligneRouge.contains("Red")){
					scoreboard.resetScores(ligneRouge);
				}
			}
		}
		if(FallenKingdom.getInstance().ListGreen > 0){
			objective.getScore("§aGreen §6: ").setScore(5);
		} else if(FallenKingdom.getInstance().ListGreen <1){
			for (String ligneVerte : scoreboard.getEntries()){
				if(ligneVerte.contains("Green")){
					scoreboard.resetScores(ligneVerte);
				}
			}
		}if(FallenKingdom.getInstance().ListYellow > 0){
			objective.getScore("§eYellow §6: ").setScore(4);
		} else if(FallenKingdom.getInstance().ListYellow <1){
			for (String ligneJaune : scoreboard.getEntries()){
				if(ligneJaune.contains("Yellow")){
					scoreboard.resetScores(ligneJaune);
				}
			}
		}
		if(FKGame.timer < 10008 && FKGame.timer > 10001){
	        objective.getScore("§7---------------------§8§d").setScore(9);
	        objective.getScore("§7---------------------§8§c").setScore(11);
	        objective.getScore("§6Téléportation...").setScore(10);


		}else if(FKGame.timer <= 10002){
			scoreboard.resetScores("§6Téléportation...");

		        objective.getScore("§7---------------------§8§c§a").setScore(12);
		        objective.getScore("§6Days: ").setScore(11);
		        objective.getScore("§6Hours: ").setScore(10);

		}
		for(String ligne : scoreboard.getEntries()){

			
			if(ligne.contains("§6Timer: ")){
				if(FKJoin.timer < 120 && FKJoin.timer > 0){
				 scoreboard.resetScores(ligne);
           
           String lastligne = ligne.split(": ")[0];
           FallenKingdom.getInstance();
				
				String newligne = lastligne + ": §b" + (FKJoin.timer);
          
           objective.getScore(newligne).setScore(10);
				} else if(ligne.contains("§6Timer: " + (FKJoin.timer - 3))){
					scoreboard.resetScores("§7---------------------§8§d");
					
					scoreboard.resetScores("§7---------------------§8§c");
					scoreboard.resetScores("§6Timer: §b122");
					scoreboard.resetScores("§6Timer: §b1");

					}
       } 
			if(ligne.contains("§6Days: ")){
				if(FKGame.timer <= 10002){
				 scoreboard.resetScores(ligne);
           
           String lastligne = ligne.split(": ")[0];
           FallenKingdom.getInstance();
				
				String newligne = lastligne + ": §b" + (FKGame.timerJour);
          
           objective.getScore(newligne).setScore(11);
				} 
       }
			
			if(ligne.contains("§6Hours: ")){
				if(FKGame.timer < 10000){
				 scoreboard.resetScores(ligne);
           
           String lastligne = ligne.split(": ")[0];
           FallenKingdom.getInstance();
				
				String newligne = lastligne + ": §b" + (FKGame.timerMinutes) + "§6 : §b" + (FKGame.timerSecondes);
          
           objective.getScore(newligne).setScore(10);
				} 
       }if(ligne.contains("§9Blue §6: ")){
    	   
    	
				 scoreboard.resetScores(ligne);
          
          String lastligne = ligne.split(": ")[0];
          FallenKingdom.getInstance();
				
				String newligne = lastligne + "§6: §9" + (FallenKingdom.getInstance().ListBlue);
         
          objective.getScore(newligne).setScore(7);
				
      } 
       if(ligne.contains("§cRed §6: ")){
    	   
			 	 scoreboard.resetScores(ligne);
    
				 String lastligne = ligne.split(": ")[0];
				 FallenKingdom.getInstance();
    
				 String newligne = lastligne + "§6: §c" + (FallenKingdom.getInstance().ListRed);
   
				 objective.getScore(newligne).setScore(6);
			
       } 
       if(ligne.contains("§aGreen §6: ")){
    	   
			 scoreboard.resetScores(ligne);
  
			 String lastligne = ligne.split(": ")[0];
			 FallenKingdom.getInstance();
			
			String newligne = lastligne + "§6: §a" + (FallenKingdom.getInstance().ListGreen);
 
			objective.getScore(newligne).setScore(5);
			
       } if(ligne.contains("§eYellow §6: ")){
    	   
	
    	   scoreboard.resetScores(ligne);

    	   String lastligne = ligne.split(": ")[0];
    	   FallenKingdom.getInstance();
	
    	   String newligne = lastligne + "§6: §e" + (FallenKingdom.getInstance().ListYellow);

    	   objective.getScore(newligne).setScore(4);
	
} 
		
			
        } 
	           
	    
	           
		}
		
	   
	
	    public void getLine(){

	    	
		        
	    	
		        objective.getScore("§6Teams: ").setScore(8);
		        objective.getScore("§7---------------------§8").setScore(3);
		        objective.getScore("§b/rules : Rules of the game").setScore(2);

		        objective.getScore("§7---------------------").setScore(1);

		        

		        objective.getScore("§6  mc.olympia.fr").setScore(0);
		      
	    }
	        
	       
	        

	   
	    
	//On recup le scoreboard custom du joueur
	public static CustomScoreboardManager getScoreboard(Player p){
		return sb.get(p);
		
	}
	
	@Override
	public Scoreboard getMainScoreboard() {

		return scoreboard;
	}

	@Override
	public Scoreboard getNewScoreboard() {

		return null;
	}

}
