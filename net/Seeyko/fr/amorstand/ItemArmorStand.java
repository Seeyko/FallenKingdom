package net.Seeyko.fr.amorstand;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemArmorStand {

	public static ItemStack BotteBleu(){
		ItemStack BotteBleu = new ItemStack(Material.LEATHER_BOOTS,1);
        LeatherArmorMeta botteBleuMeta = (LeatherArmorMeta)BotteBleu.getItemMeta();	
        botteBleuMeta.setColor(Color.fromRGB(0, 0, 255));
		BotteBleu.setItemMeta(botteBleuMeta);
				return BotteBleu;
	}
	public static ItemStack PantalonBleu(){
		ItemStack PantalonBleu = new ItemStack(Material.LEATHER_LEGGINGS,1);
        LeatherArmorMeta pantalonBleuMeta = (LeatherArmorMeta)PantalonBleu.getItemMeta();	
        pantalonBleuMeta.setColor(Color.fromRGB(0, 0, 255));
        PantalonBleu.setItemMeta(pantalonBleuMeta);
				return PantalonBleu;
	}
	public static ItemStack PlastronBleu(){
		ItemStack PlastronBleu = new ItemStack(Material.LEATHER_LEGGINGS,1);
        LeatherArmorMeta plastronBleuMeta = (LeatherArmorMeta)PlastronBleu.getItemMeta();	
        plastronBleuMeta.setColor(Color.fromRGB(0, 0, 255));
        PlastronBleu.setItemMeta(plastronBleuMeta);
				return PlastronBleu;
	}
	public static  ItemStack BotteRouge(){
		ItemStack BotteRouge = new ItemStack(Material.LEATHER_BOOTS,1);
        LeatherArmorMeta BotteRougeMeta = (LeatherArmorMeta)BotteRouge.getItemMeta();	
        BotteRougeMeta.setColor(Color.fromRGB(255, 0, 0));
        BotteRouge.setItemMeta(BotteRougeMeta);
				return BotteRouge;
	}
	public static  ItemStack PantalonRouge(){
		ItemStack PantalonRouge = new ItemStack(Material.LEATHER_LEGGINGS,1);
        LeatherArmorMeta PantalonRougeMeta = (LeatherArmorMeta)PantalonRouge.getItemMeta();	
        PantalonRougeMeta.setColor(Color.fromRGB(255, 0, 0));
        PantalonRouge.setItemMeta(PantalonRougeMeta);
				return PantalonRouge;
	}
	public static  ItemStack PlastronRouge(){
		ItemStack PlastronRouge = new ItemStack(Material.LEATHER_LEGGINGS,1);
        LeatherArmorMeta PlastronRougeMeta = (LeatherArmorMeta)PlastronRouge.getItemMeta();	
        PlastronRougeMeta.setColor(Color.fromRGB(255, 0, 0));
        PlastronRouge.setItemMeta(PlastronRougeMeta);
				return PlastronRouge;
	}
	public static  ItemStack BotteVert(){
		ItemStack BotteVert = new ItemStack(Material.LEATHER_BOOTS,1);
        LeatherArmorMeta BotteVertMeta = (LeatherArmorMeta)BotteVert.getItemMeta();	
        BotteVertMeta.setColor(Color.fromRGB(0, 255, 0));
        BotteVert.setItemMeta(BotteVertMeta);
				return BotteVert;
	}
	public static  ItemStack PantalonVert(){
		ItemStack PantalonVert = new ItemStack(Material.LEATHER_LEGGINGS,1);
        LeatherArmorMeta PantalonVertMeta = (LeatherArmorMeta)PantalonVert.getItemMeta();	
        PantalonVertMeta.setColor(Color.fromRGB(0, 255, 0));
        PantalonVert.setItemMeta(PantalonVertMeta);
				return PantalonVert;
	}
	public static  ItemStack PlastronVert(){
		ItemStack PlastronVert = new ItemStack(Material.LEATHER_LEGGINGS,1);
        LeatherArmorMeta PlastronVertMeta = (LeatherArmorMeta)PlastronVert.getItemMeta();	
        PlastronVertMeta.setColor(Color.fromRGB(0, 255, 0));
        PlastronVert.setItemMeta(PlastronVertMeta);
				return PlastronVert;
	}
	public static  ItemStack BotteJaune(){
		ItemStack BotteJaune = new ItemStack(Material.LEATHER_BOOTS,1);
        LeatherArmorMeta BotteJauneMeta = (LeatherArmorMeta)BotteJaune.getItemMeta();	
        BotteJauneMeta.setColor(Color.fromRGB(255, 255, 85));
        BotteJaune.setItemMeta(BotteJauneMeta);
				return BotteJaune;
	}
	public static  ItemStack PantalonJaune(){
		ItemStack PantalonJaune = new ItemStack(Material.LEATHER_LEGGINGS,1);
        LeatherArmorMeta PantalonJauneMeta = (LeatherArmorMeta)PantalonJaune.getItemMeta();	
        PantalonJauneMeta.setColor(Color.fromRGB(255, 255, 85));
        PantalonJaune.setItemMeta(PantalonJauneMeta);
				return PantalonJaune;
	}
	public static ItemStack PlastronJaune(){
		ItemStack PlastronJaune = new ItemStack(Material.LEATHER_LEGGINGS,1);
        LeatherArmorMeta PlastronJauneMeta = (LeatherArmorMeta)PlastronJaune.getItemMeta();	
        PlastronJauneMeta.setColor(Color.fromRGB(255, 255, 85));
        PlastronJaune.setItemMeta(PlastronJauneMeta);
				return PlastronJaune;
	}
}
