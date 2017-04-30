package net.Seeyko.fr;
/*									      	
 *  Author : Seeyko

 * 	Start on: 13/04/17										    
 *  First release on: 25/04/17
 *	
 *
 *	Any report, correction, question go here: https://www.spigotmc.org/members/seeyko.330398/
 *  This plugin is free and will stay free, don't sell it.
 * 	
 */	
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import net.Seeyko.fr.events.EventsManager;
import net.Seeyko.fr.events.GameState;
import net.Seeyko.fr.game.FKGame;
import net.Seeyko.fr.join.FKJoin;
import net.Seeyko.fr.scoreboard.ScoreboardRunnable;
import net.Seeyko.fr.util.Command;
import net.Seeyko.fr.util.ItemsDepart;
import net.Seeyko.fr.util.Team;

public class FallenKingdom extends JavaPlugin{

	
	
	
    
    public static Inventory King;
	public String World = getConfig().getString("name-of-the-world");
	public static FallenKingdom instance;
	public static ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	public ArrayList<Player> playerInGame = new ArrayList<>();
	public ArrayList<Player> allPlayer = new ArrayList<>();
	public ArrayList<Player> Spectator = new ArrayList<>();
	public ArrayList<Player> listPlayerWhoSpawnKing = new ArrayList<>();

	public Team Blue = new Team("Blue", "§9", (byte)11);
	public Team Red = new Team("Red", "§c", (byte)14);
	public Team Green = new Team("Green", "§a", (byte)5);
	public Team Yellow = new Team("Yellow", "§e", (byte)4);
	
    public static Inventory TeamInv;
    public static Inventory Rules;
    public int ListBlue = 0;
    public int ListYellow = 0;
    public int ListGreen = 0;
    public int ListRed = 0;
	public static FallenKingdom getInstance(){
		return instance;
	}
	
	
	public void onEnable(){
		
		
		ListBlue = 0;
		ListYellow = 0;
		ListGreen = 0;
		ListRed = 0;
		 loadLang();
		saveDefaultConfig();
		GameState.setState(GameState.LOBBY);
		instance = this;
		console.sendMessage(Lang.PREFIX + "§aPlugin is active!");
		EventsManager.registerEvents(this);
		
        TeamInv = Bukkit.createInventory(null, 9, Lang.INV_CHOOSE_TEAM+"");
        Rules = Bukkit.createInventory(null, 9, Lang.INV_RULES+ "");
        FKJoin.getInstance();
		TeamInv.addItem(FKJoin.Bleu());
		TeamInv.addItem(FKJoin.Rouge());
		TeamInv.addItem(FKJoin.Vert());
		TeamInv.addItem(FKJoin.Jaune());
		
		King = Bukkit.createInventory(null, 9, Lang.INV_MOVEKING+"");
        getCommand("forcestart").setExecutor(new Command());
        getCommand("BaseBlue").setExecutor(new Command());
        getCommand("BaseRed").setExecutor(new Command());
        getCommand("BaseGreen").setExecutor(new Command());
        getCommand("BaseSpawn").setExecutor(new Command());
        getCommand("rules").setExecutor(new Command());
        getCommand("king").setExecutor(new Command());

        ItemsDepart.getInstance();
        Rules.setItem(0, ItemsDepart.RegleVictoire());
        Rules.setItem(4, ItemsDepart.RegleTemps());
        Rules.setItem(8, ItemsDepart.RegleRoi());
		King.setItem(3, ItemsDepart.RoiConfirm());
		King.setItem(5, ItemsDepart.RoiRefus());

		new ScoreboardRunnable().runTaskTimer(this, 20L, 20);
        getWorldGuard();
		Bukkit.getWorld(World).setPVP(false);
		FKGame.timerJour = 1;
 		


	}
	
	public void onDisable(){
		
		console.sendMessage(Lang.PREFIX + "§cPlugin is disabling..");
		playerInGame.clear();
		allPlayer.clear();
		Spectator.clear();
		for(int i = 0; i < allPlayer.size(); ++i){
			Player player = allPlayer.get(i);
			playerInGame.remove(player);
			Spectator.remove(player);
			allPlayer.remove(player);
			player.setLevel(-1);
			ItemsDepart.removeAll(player);
		}
	   
		

	
	}
/*	private void createConfig() {
	    try {
	        if (!getDataFolder().exists()) {
	            getDataFolder().mkdirs();
	        }
	        File file = new File(getDataFolder(), "config.yml");
	        if (!file.exists()) {
	        	getConfig().options().copyDefaults(true);
	            saveConfig();
	        } else {
	            getLogger().info("Config.yml existe, on le charge !");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();

	    }

	}*/	
	
    public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
   try {
   ymlConfig.save(ymlFile);
   } catch (IOException e) {
   e.printStackTrace();
   }
   }
	public void addPlayer(Player player, Team team) {

		
		team.addPlayer(player);
		player.setPlayerListName(team.getTag() + player.getName());
		player.setDisplayName(team.getTag() + player.getName());
	}
	public void removePlayer(Player player, Team team) {
		team.removePlayer(player);
	}
	public void randomTeam(Player player) {
		
	
		
		//Pour rajouter un joueur a une équipe utilise juste cette méthode en remplaçant team par Bleu, Rouge, Vert ou Jaune :
		//Ce if vérifie que le joueur ne fait partie d'aucune team
		
	
	}
		

	 
	
	public WorldGuardPlugin getWorldGuard()
    {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
        if ((plugin == null) || (!(plugin instanceof WorldGuardPlugin)))
        {
           return null; //throws a NullPointerException, telling the Admin that WG is not loaded.
        }
        else 

        
        return (WorldGuardPlugin)plugin;

    }
	 public static YamlConfiguration LANG;
	    public static File LANG_FILE;

	    public YamlConfiguration loadLang() {
	        File lang = new File(getDataFolder(), "lang.yml");
	        if (!lang.exists()) {
	            try {
	                getDataFolder().mkdir();
	                lang.createNewFile();
	                InputStream defConfigStream = this.getResource("lang.yml");
	                if (defConfigStream != null) {
	                    @SuppressWarnings("deprecation")
						YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	                    defConfig.save(lang);
	                    Lang.setFile(defConfig);
	                    return defConfig;
	                }
	            } catch(IOException e) {
	                e.printStackTrace(); // So they notice
	                getLogger().severe(Lang.PREFIX + "Couldn't create language file.");
	                getLogger().severe(Lang.PREFIX + "This is a fatal error. Now disabling");
	                this.setEnabled(false); // Without it loaded, we can't send them messages
	            }
	        }
	        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
	        for(Lang item:Lang.values()) {
	            if (conf.getString(item.getPath()) == null) {
	                conf.set(item.getPath(), item.getDefault());
	            }
	        }
	        Lang.setFile(conf);
	        LANG = conf;
	        LANG_FILE = lang;
	        try {
	            conf.save(getLangFile());
	        } catch(IOException e) {
	            getLogger().log(Level.WARNING, Lang.PREFIX +"Failed to save lang.yml.");
	            getLogger().log(Level.WARNING, Lang.PREFIX +"Report this stack trace to <your name>.");
	            e.printStackTrace();
	        }
	        return conf;
	    }

	    public enum Lang {
	        PREFIX("prefix", "&6[&aFallen Kingdom&6]&6 : "),
	        NO_PERMISSION("no-permission", "&6You don't have enough permission to do this."),
	        
	        INV_MOVEKING("Inv-moveKing", "&6Move of the king !"),
	        INV_CHOOSE_TEAM("Inv-chooseTeam", "&6Choose your team"),
	    	INV_RULES("Inv-Rules", "&bRules"),
	    	
	        DAMAGE_KING_BEFOREPVP("Damage-King-Before-Pvp", "&6Wait the &b4&6 days to do this !"),
	        PLACE_KING_EXTERNAL_BASE("PlaceKing_NotInBase", "&6Your king is not going to be protect here !"),
	        ALREADY_PLACE_KING("Already_PlaceKing", "&6You have already move your king, can't do this again, ask one of your mate"),
	      
	        WAIT_FOURDAYS("Wait-four-days", "&6Wait day &b4&6 to do this !"),
	        PLACE_OUTSIDE_BASE("Place-outside-base", "&6You can't place a block outside your base !"),
	        
	        BLUEBASE_CREATE("Base-Blue-Create", "&6You just have create the blue base !"),
	        BLUEBASE_BREAK("Base-Blue-Break", "&6You can't break block in the &9Blue &6base !"),
	        BLUEBASE_PLACE("Base-Blue-Place", "&6You can't place block in the &9Blue &6base !"),
	        REDBASE_CREATE("Base-Red-Create", "&6You just have create the red base !"),
	        REDBASE_BREAK("Base-Red-Break", "&6You can't break block in the &cRed &6base !"),
	        REDBASE_PLACE("Base-Red-Place", "&6You can't place block in the &cRed &6base !"),
	        GREENBASE_CREATE("Base-Green-Create", "&6You just have create the green base !"),
	        GREENBASE_BREAK("Base-Green-Break", "&6You can't break block in the &aGreen &6base !"),
	        GREENBASE_PLACE("Base-Green-Place", "&6You can't place block in the &aGreen &6base !"),
	        YELLOWBASE_CREATE("Base-Yellow-Create", "&6You just have create the yellow base !"),
	        YELLOWBASE_BREAK("Base-Yellow-Break", "&6You can't break block in the &eYellow &6base !"),
	        YELLOWBASE_PLACE("Base-Yellow-Place", "&6You can't place block in the &eYellow &6base !"),
	        
	        KING_ATTACK("King-attack", "&6Your king has been damaged !"),

	        TITLE_DAY_ONE("Title-one","&6Research of materials.."),
	        TITLE_DAY_TWO("Title-two","&6Creation of the castle"),
	        TITLE_DAY_THREE("Title-three","&6Placement of the king"),
	        TITLE_DAY_FOUR("Title-four","&6Kill them all"),
	        
	        GAME_ALREADY_START("game-already-start", "&6The game have already start, you are a spectator"),
	        GAME_FULL("game-isfull", "&6The game is full, wait or come back later !");
	        
        private String path;
	        private String def;
	        private static YamlConfiguration LANG;

	        Lang(String path, String start) {
	            this.path = path;
	            this.def = start;
	        }

	        public static void setFile(YamlConfiguration config) {
	            LANG = config;
	        }

	        @Override
	        public String toString() {
	            if (this == PREFIX)
	                return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def)) + " ";
	            return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
	        }

	        public String getDefault() {
	            return this.def;
	        }

	        public String getPath() {
	            return this.path;
	        }
	    }


	    public YamlConfiguration getLang() {
	        return LANG;
	    }

	    public File getLangFile() {
	        return LANG_FILE;
	    }
}
