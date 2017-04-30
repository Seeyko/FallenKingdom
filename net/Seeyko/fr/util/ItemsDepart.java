package net.Seeyko.fr.util;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.Seeyko.fr.FallenKingdom;


public class ItemsDepart {
	public static ItemsDepart instance;
	public static ItemsDepart getInstance() {
		return instance;
	}
	
	 public static ItemStack RegleVictoire(){

		ItemStack RegleVictoire = new ItemStack(Material.PAPER);
		ItemMeta iM2 = RegleVictoire.getItemMeta();
		iM2.setDisplayName("§6Rules for Victory");
		 ArrayList<String> LoreRegleVictoire = new ArrayList<String>();

		 LoreRegleVictoire.add("§b1 §7: After the day §b4§7 find the §6roi §7of the other team");
		 LoreRegleVictoire.add("§b2 §7: Kill the §6king§7 of the other team !");
		  LoreRegleVictoire.add("§b3 §7: And finaly kill them all.");
		iM2.setLore(LoreRegleVictoire);
		RegleVictoire.setItemMeta(iM2);
		
		return RegleVictoire;
	} 
	 public static ItemStack RoiConfirm(){

			ItemStack RoiConfirm = new ItemStack(Material.STAINED_GLASS_PANE,1, (byte) 5);
			ItemMeta iM2 = RoiConfirm.getItemMeta();
			iM2.setDisplayName(FallenKingdom.Lang.INV_MOVEKING + "");
			ArrayList<String> LoreRoiConfirm = new ArrayList<String>();

			LoreRoiConfirm.add("§7Clic here if you want to move your §6king");
			LoreRoiConfirm.add("§7You can use this only one time.");
			LoreRoiConfirm.add("§7Make a good chose for the placement !");
			LoreRoiConfirm.add("§7And talk to your mate before this..");
			LoreRoiConfirm.add("§cAware: §7The §6king §7will be at your actual position !!");

			iM2.setLore(LoreRoiConfirm);
			RoiConfirm.setItemMeta(iM2);
			
			return RoiConfirm;
		}
	 public static ItemStack RoiRefus(){

			ItemStack RoiRefus = new ItemStack(Material.STAINED_GLASS_PANE,1, (byte) 14);
			ItemMeta iM2 = RoiRefus.getItemMeta();
			iM2.setDisplayName("§cLet the king at his actual place.");
			ArrayList<String> LoreRoiRefus = new ArrayList<String>();

			LoreRoiRefus.add("§7Clic here to let the king");

			iM2.setLore(LoreRoiRefus);
			RoiRefus.setItemMeta(iM2);
			
			return RoiRefus;
		}
	 
	 public static ItemStack RegleTemps(){

			ItemStack RegleTemps = new ItemStack(Material.PAPER);
			ItemMeta iM2 = RegleTemps.getItemMeta();
			iM2.setDisplayName("§6Rules of time");
			 ArrayList<String> LoreRegleTemps = new ArrayList<String>();
			 LoreRegleTemps.add("§7Day §b1 §7: PvP §cdesactivated§7, TnT §cdesactivated");
			 LoreRegleTemps.add("§7Day §b2 §7: PvP §cdesactivated§7, TnT §cdesactivated");
			 LoreRegleTemps.add("§7Day §b3 §7: PvP §aactivated§7, TnT §cdesactivated");
			 LoreRegleTemps.add("§7Day §b4 §7: PvP §aactivated§7, TnT §aactivated");
			 


			iM2.setLore(LoreRegleTemps);
			RegleTemps.setItemMeta(iM2);
			
			return RegleTemps;
		}
	 public static ItemStack RegleRoi(){

			ItemStack RegleRoi = new ItemStack(Material.PAPER);
			ItemMeta iM2 = RegleRoi.getItemMeta();
			iM2.setDisplayName("§6Placement of the king !");
			 ArrayList<String> LoreRegleRoi = new ArrayList<String>();
			 LoreRegleRoi.add("§7Placement of the §6king§7 between day §b1§7 and §b4");
			 LoreRegleRoi.add("§7To place or move it, do §a/roi");
			 LoreRegleRoi.add("§7Can be use §c1 §6time");


			iM2.setLore(LoreRegleRoi);
			RegleRoi.setItemMeta(iM2);
			
			return RegleRoi;
		}
	
	
	
	public static void removeAll(Player player){
				
	
	ItemStack remove = new ItemStack(Material.AIR, 1);
	player.getInventory().clear();
	player.getInventory().addItem(remove);
	player.getInventory().addItem(remove);
	player.getInventory().setHelmet(remove);
	player.getInventory().setChestplate(remove);
	player.getInventory().setLeggings(remove);
	player.getInventory().setBoots(remove);
	}
}

