package net.Seeyko.fr.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Team {
		private Location spawn;
		private String name;
		private String tag;
		private byte woolData;
		private boolean hasBed;
		private List<Player> team = new ArrayList<>();
		
		public Team(String name, String tag, byte woolData) {
			this.name = name;
			this.tag = tag;
			this.woolData = woolData;
			this.hasBed = true;
		}
		
		public ItemStack getIcon(){
			ItemStack i = new ItemStack(Material.WOOL, 1, woolData);
			ItemMeta iM = i.getItemMeta();
			iM.setDisplayName(tag +"Rejoindre l'equipe des " + name);
			i.setItemMeta(iM);
			return i;
		}
		
		public void addPlayer(Player player){
			team.add(player);
		}
		
		public Location getSpawn(){
			return spawn;
		}
		
		public void removePlayer(Player player){
			team.remove(player);
		}
		
		public void removeBed(){
			this.hasBed = false;
		}
		
		public boolean hasBed(){
			return hasBed;
		}
		
		public List<Player> getPlayers(){
			return team;
		}
		
		public int getSize(){
			return team.size();
		}
		
		public String getName() {
			return name;
		}

		public String getTag() {
			return tag;
		}
		
		public byte getWoolData() {
			return woolData;
		}
		
		
		

	}


