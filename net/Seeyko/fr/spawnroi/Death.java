package net.Seeyko.fr.spawnroi;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.Seeyko.fr.FallenKingdom;

public class Death {

	int taskBleu;int taskRouge;int taskVert;int taskJaune;int timerBleu;int timerRouge;int timerVert;int timerJaune;
	int taskTnt; int timerTnt;
	
	
	public void roiBleu(Player player){
		timerBleu = 120;
		
		taskBleu = Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable() {

			@Override
			public void run() {
				
				timerBleu --;
				if(timerBleu == 119){
					//Crée l'effet de mort de l'enderDragon
		    		Bukkit.dispatchCommand(FallenKingdom.console, "kill @e[type=Villager,name=VilBleu");

				}
				
				if(timerBleu == 112){
					for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
			        	String entity1 = entity.getCustomName();
						if(entity.isCustomNameVisible()){

			        	if(entity1.equals("§6Roi §9Bleu") ){
			        		World world = Bukkit.getWorld(FallenKingdom.getInstance().World);
			        		Location entityLocation = entity.getLocation();
			        		world.createExplosion(entityLocation, 4);
			        	 world.playSound(entityLocation, Sound.EXPLODE, 3.0F, 0.533F);
			        	 world.playEffect(entityLocation, Effect.ENDER_SIGNAL, 3);
			        	} 
			        	  
						}
						
			        } 
				}
 				for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
		        	String entity1 = entity.getCustomName();
					if(entity.isCustomNameVisible()){

		        	if(entity1.equals("§6Roi §9Bleu") ){
		            entity.remove();
		    		Bukkit.dispatchCommand(FallenKingdom.console, "kill @e[type=Villager,name=VilBleu");
		    		player.getWorld().playSound(entity.getLocation(), Sound.WITHER_DEATH, 3.0F, 0.533F);
		    		
		        	} 
		        	  
					}
					
		        } 
			}
			
		}, 20, 20);
		
		for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
        	String entity1 = entity.getCustomName();
			if(entity.isCustomNameVisible()){

        	if(entity1.equals("§6Roi §9Bleu") ){
            entity.remove();
    		Bukkit.dispatchCommand(FallenKingdom.console, "kill @e[type=Villager,name=VilBleu");
    		player.getWorld().playSound(entity.getLocation(), Sound.WITHER_DEATH, 3.0F, 0.533F);
    		
        	} 
        	  
			}
			
        } 
	}
	
	public void fakeTnt(Player player){
		timerTnt = 120;
		taskTnt = Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable() {
		World world = Bukkit.getWorld(FallenKingdom.getInstance().World);
		
		
			@Override
			public void run() {
			timerTnt --;
				
			if(timerTnt == 119){
				for (Entity entity : Bukkit.getWorld(FallenKingdom.getInstance().World).getEntities()) {
		        	String entity1 = entity.getCustomName();
					if(entity.isCustomNameVisible()){

		        	if(entity1.equals("§6Roi §9Bleu") ){
		        	world.spawnEntity(entity.getLocation(), EntityType.PRIMED_TNT);	
		    		
		    		
		    		
		        	} 
		        	  
					}
					
		        } 
			}
			}
			
		}, 20,20);
		
		
		
	}
	
	public void fakeTntEntity(Entity entity){
		Entity primedTnt = Bukkit.getWorld(FallenKingdom.getInstance().World).spawnEntity(entity.getLocation(), EntityType.PRIMED_TNT);
		primedTnt.setCustomName("fakeTnt");
		primedTnt.setCustomNameVisible(false);
		primedTnt.setFireTicks(1);
		
		
		
		
	}
}
