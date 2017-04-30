package net.Seeyko.fr.spawnroi;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.Seeyko.fr.FallenKingdom;
import net.Seeyko.fr.amorstand.ItemArmorStand;
import net.Seeyko.fr.game.Inventory;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class Spawn {
	 static  int taskBleu;
	  static int timerBleu = 10;
	  static  int taskRouge;
	  static int timerRouge = 10;
	  static  int taskVert;
	  static int timerVert = 10;
	  static  int taskJaune;
	  static int timerJaune = 10;
	public static void Bleu(Player player){
		
		FallenKingdom.getInstance().listPlayerWhoSpawnKing.add(player);
		 player.closeInventory();
			FallenKingdom.console.sendMessage("§aLa methode pour deplacer le roi bleu a été appelé");

			taskBleu = Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable() {

				@Override
				public void run() {
					timerBleu --;

					if(timerBleu == 9){
						Inventory.lifeKingBlue = 1000;
						FallenKingdom.console.sendMessage("§aBlue king has been destroy by" +player.getDisplayName());

						FallenKingdom.getInstance();
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
					if(timerBleu == 8){
						for(Player player1 : FallenKingdom.getInstance().Blue.getPlayers()){
							player1.sendMessage("§9King§6 : §r"+player.getDisplayName()+" §6move me, i hope i'm protected here!");

			        		}

					 ArmorStand Bleu = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
					 
					 Bleu.setArms(true);
			         EntityArmorStand as = ((CraftArmorStand) Bleu).getHandle();
			         NBTTagCompound compound = new NBTTagCompound();
			         Entity roiBleu = player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
					 roiBleu.setCustomName("VilBleu");
			         roiBleu.setCustomNameVisible(false);
			         net.minecraft.server.v1_8_R3.Entity nmsVillager = ((CraftEntity) roiBleu).getHandle();
			         NBTTagCompound tag = nmsVillager.getNBTTag();
			         if(tag == null){
			             tag = new NBTTagCompound();
			         }
			         nmsVillager.c(tag);
			         compound.setBoolean("Invisible", true);

			         tag.setInt("NoAI", 1);
			         tag.setByte("Silent", (byte) 8);
			         nmsVillager.b(true);
			         nmsVillager.setCustomNameVisible(false);
			         nmsVillager.setInvisible(false);
			         nmsVillager.f(tag);
			         nmsVillager.f(tag);
			         LivingEntity LivingRoiBleu = (LivingEntity) roiBleu;
			         LivingRoiBleu.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 40));
			         
			         roiBleu.setCustomName("VilBleu");
			         roiBleu.setCustomNameVisible(false);
			         
			         as.b(true);
			         as.c(compound);
			         compound.setInt("NoAI", 1);
			         
			         as.c(compound);
			         as.f(compound);
			         Bleu.setGravity(false);
			         Bleu.setVisible(true);
			         Bleu.setCustomNameVisible(false);
			         Bleu.setCustomName("§6Roi §9Bleu");
			         Bleu.setBasePlate(false);
			         Bleu.setBoots(ItemArmorStand.BotteBleu());
			         Bleu.setChestplate(ItemArmorStand.PlastronBleu());
			         Bleu.setLeggings(ItemArmorStand.PantalonBleu());

					}
					
					if(timerBleu == 7){
						Bukkit.getScheduler().cancelTask(taskBleu);
						timerBleu = 10;
					}
				}
		  
         		
			}, 2, 2);

         	
        
		 
	}
	
	public static void Rouge(Player player){
		Inventory.lifeKingRed = 1000;

		 player.closeInventory();
		 
		 taskRouge = Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable() {
			 
			 @Override
			 public void run() {
			 	// TODO Auto-generated method stub
			 	timerRouge --;
			 if(timerRouge == 9){
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
			 if(timerRouge == 8){
				 
			 
		 for(Player player1 : FallenKingdom.getInstance().Red.getPlayers()){
				player1.sendMessage("§cKing§6 : §r"+player.getDisplayName()+" §6move me, i hope i'm protected here!");

        		}
FallenKingdom.getInstance().listPlayerWhoSpawnKing.add(player);
 player.closeInventory();
 ArmorStand Rouge = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);                        
 Rouge.setArms(true);
 EntityArmorStand AsRouge = ((CraftArmorStand) Rouge).getHandle();
 NBTTagCompound TagAs= new NBTTagCompound();
 Entity roiRouge = player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
 roiRouge.setCustomName("VilRouge");
 roiRouge.setCustomNameVisible(false);
 net.minecraft.server.v1_8_R3.Entity nmsVillager = ((CraftEntity) roiRouge).getHandle();
 NBTTagCompound tag = nmsVillager.getNBTTag();
 if(tag == null){
     tag = new NBTTagCompound();
 }
 nmsVillager.c(tag);
 TagAs.setBoolean("Invisible", true);

 tag.setInt("NoAI", 1);
 tag.setByte("Silent", (byte) 8);
 nmsVillager.b(true);
 nmsVillager.setCustomNameVisible(false);
 nmsVillager.setInvisible(false);
 nmsVillager.f(tag);
 nmsVillager.f(tag);
 LivingEntity LivingRoiRouge = (LivingEntity) roiRouge;

 LivingRoiRouge.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 40));
 
 roiRouge.setCustomName("VilRouge");
 roiRouge.setCustomNameVisible(false);
 
 AsRouge.b(true);
 AsRouge.c(TagAs);
 TagAs.setInt("NoAI", 1);
 
 AsRouge.c(TagAs);
 AsRouge.f(TagAs);
 Rouge.setGravity(false);
 Rouge.setVisible(true);
 Rouge.setCustomNameVisible(false);
 Rouge.setCustomName("§6Roi §cRouge");
 Rouge.setBasePlate(false);
 Rouge.setBoots(ItemArmorStand.BotteRouge());
 Rouge.setChestplate(ItemArmorStand.PlastronRouge());
 Rouge.setLeggings(ItemArmorStand.PantalonRouge());
			 }
 if(timerRouge == 7){
		Bukkit.getScheduler().cancelTask(taskRouge);
		timerRouge = 10;
	}
}
		 }, 2, 2);
		 }
	
	public static void Vert(Player player){
		Inventory.lifeKingGreen = 1000;
		 player.closeInventory();
		 
		 taskVert= Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable() {
			 
			 @Override
			 public void run() {
			 	// TODO Auto-generated method stub
			 	timerVert --;
				 if(timerVert ==9){
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
				 if(timerVert == 8){
		 for(Player player1 : FallenKingdom.getInstance().Green.getPlayers()){
				player1.sendMessage("§aRoi§6 : §r"+player.getDisplayName()+" §6move me, i hope i'm protected here!");

        		}
FallenKingdom.getInstance().listPlayerWhoSpawnKing.add(player);
player.closeInventory();
ArmorStand Vert = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);                        
Vert.setArms(true);
EntityArmorStand AsVert = ((CraftArmorStand) Vert).getHandle();
NBTTagCompound TagAs= new NBTTagCompound();
Entity roiVert = player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
roiVert.setCustomName("VilVert");
roiVert.setCustomNameVisible(false);
net.minecraft.server.v1_8_R3.Entity nmsVillager = ((CraftEntity) roiVert).getHandle();
NBTTagCompound tag = nmsVillager.getNBTTag();
if(tag == null){
    tag = new NBTTagCompound();
}
nmsVillager.c(tag);
TagAs.setBoolean("Invisible", true);

tag.setInt("NoAI", 1);
tag.setByte("Silent", (byte) 8);
nmsVillager.b(true);
nmsVillager.setCustomNameVisible(false);
nmsVillager.setInvisible(false);
nmsVillager.f(tag);
nmsVillager.f(tag);
LivingEntity LivingRoiVert = (LivingEntity) roiVert;
LivingRoiVert.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 40));

roiVert.setCustomName("VilVert");
roiVert.setCustomNameVisible(false);

AsVert.b(true);
AsVert.c(TagAs);
TagAs.setInt("NoAI", 1);

AsVert.c(TagAs);
AsVert.f(TagAs);
Vert.setGravity(false);
Vert.setVisible(true);
Vert.setCustomNameVisible(false);
Vert.setCustomName("§6Roi §aVert");
Vert.setBasePlate(false);
Vert.setBoots(ItemArmorStand.BotteVert());
Vert.setChestplate(ItemArmorStand.PlastronVert());
Vert.setLeggings(ItemArmorStand.PantalonVert());
				 }
if(timerVert == 7){
	Bukkit.getScheduler().cancelTask(taskVert);
	timerVert = 10;
}
		 }
		 }, 2, 2);

	}
	
	public static void Jaune(Player player){
		FallenKingdom.getInstance().listPlayerWhoSpawnKing.add(player);
			 player.closeInventory();
				Inventory.lifeKingYellow = 1000;
				
				taskJaune = Bukkit.getScheduler().scheduleSyncRepeatingTask(FallenKingdom.getInstance(), new Runnable() {
					 @Override
					 public void run() {
					 	// TODO Auto-generated method stub
				timerJaune --;	 	
			if(timerJaune == 9){
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
			
			if(timerJaune == 8){
			 for(Player player1 : FallenKingdom.getInstance().Yellow.getPlayers()){
						player1.sendMessage("§eRoi§6 : §r"+player.getDisplayName()+" §6move me, i hope i'm protected here!");

	            		}
	FallenKingdom.getInstance().listPlayerWhoSpawnKing.add(player);
	 player.closeInventory();
	 ArmorStand Jaune = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);                        
	 Jaune.setArms(true);
    EntityArmorStand AsJaune = ((CraftArmorStand) Jaune).getHandle();
    NBTTagCompound TagAs= new NBTTagCompound();
    Entity roiJaune = player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
    roiJaune.setCustomName("VilVert");
    roiJaune.setCustomNameVisible(false);
    net.minecraft.server.v1_8_R3.Entity nmsVillager = ((CraftEntity) roiJaune).getHandle();
    NBTTagCompound tag = nmsVillager.getNBTTag();
    if(tag == null){
        tag = new NBTTagCompound();
    }
    nmsVillager.c(tag);
    TagAs.setBoolean("Invisible", true);

    tag.setInt("NoAI", 1);
    tag.setByte("Silent", (byte) 8);
    nmsVillager.b(true);
    nmsVillager.setCustomNameVisible(false);
    nmsVillager.setInvisible(false);
    nmsVillager.f(tag);
    nmsVillager.f(tag);
    LivingEntity LivingroiJaune = (LivingEntity) roiJaune;
    LivingroiJaune.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 40));
    
    roiJaune.setCustomName("VilVert");
    roiJaune.setCustomNameVisible(false);
    
    AsJaune.b(true);
    AsJaune.c(TagAs);
    TagAs.setInt("NoAI", 1);
    
    AsJaune.c(TagAs);
    AsJaune.f(TagAs);
    Jaune.setGravity(false);
    Jaune.setVisible(true);
    Jaune.setCustomNameVisible(false);
    Jaune.setCustomName("§6Roi §eJaune");
    Jaune.setBasePlate(false);
    Jaune.setBoots(ItemArmorStand.BotteJaune());
    Jaune.setChestplate(ItemArmorStand.PlastronJaune());
    Jaune.setLeggings(ItemArmorStand.PantalonJaune());
			}
    if(timerJaune == 7){
    	Bukkit.getScheduler().cancelTask(taskJaune);
    	timerJaune = 10;
    }
    		 }
    		 }, 2, 2);
	}
}
