package net.Seeyko.fr.chat;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.Seeyko.fr.FallenKingdom;

public class chatFormat implements Listener{
	
	@EventHandler
	public void chat(AsyncPlayerChatEvent event){
		
		
		Player player = event.getPlayer();
		event.setFormat(player.getName() + " : " + event.getMessage());
		if(FallenKingdom.getInstance().Blue.getPlayers().contains(player)){
			event.setFormat("§9" + player.getName() + " §b:§f " + event.getMessage());
		} 		
		else if(FallenKingdom.getInstance().Red.getPlayers().contains(player)){
			event.setFormat("§c" + player.getName() + " §4:§f " +event.getMessage());
		} 
		else if(FallenKingdom.getInstance().Green.getPlayers().contains(player)){
			event.setFormat("§a"+player.getName() + " §2:§f " + event.getMessage());
		}
		else if(FallenKingdom.getInstance().Yellow.getPlayers().contains(player)){
			event.setFormat("§e"+player.getName() + " §6:§f " + event.getMessage());
		} else 
		
		if(player.getGameMode().equals(GameMode.SPECTATOR)){
			event.setFormat("§7"+player.getName() + "§7 : " +event.getMessage());
		}
		else {
			event.setFormat("§f" +player.getName() + " §f: §7" + event.getMessage());

		}
	}

}
