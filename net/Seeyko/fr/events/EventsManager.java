package net.Seeyko.fr.events;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import net.Seeyko.fr.FallenKingdom;
import net.Seeyko.fr.chat.chatFormat;
import net.Seeyko.fr.game.FKPvP;
import net.Seeyko.fr.game.Inventory;
import net.Seeyko.fr.join.FKJoin;

public class EventsManager {
	public static void registerEvents(FallenKingdom pl) {
		//On crée une variable permettant de register les events
			PluginManager pm = Bukkit.getPluginManager();
			
			pm.registerEvents(new FKJoin(), pl);
			pm.registerEvents(new Teleportation(), pl);
			pm.registerEvents(new FKPvP(), pl);
			pm.registerEvents(new Inventory(),pl);
			pm.registerEvents(new chatFormat(), pl);

		}
}
