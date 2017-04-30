package net.Seeyko.fr.util;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;

public class FlagRegion {
	
	
	public static void RegionBleu(Player player, World world){
		BlockVector b1 = new BlockVector(player.getLocation().getBlockX()+13,player.getLocation().getBlockY()+ 200,player.getLocation().getBlockZ()+10);
        BlockVector b2 = new BlockVector(player.getLocation().getBlockX()-13,player.getLocation().getBlockY()-200,player.getLocation().getBlockZ()-10);
        ProtectedCuboidRegion ProtectedBaseBleu = new ProtectedCuboidRegion("BaseBleu", b1, b2);
        RegionManager rgManager = WGBukkit.getRegionManager(world);
		DefaultDomain owner = new DefaultDomain();
		owner.addPlayer(player.getName());
		ProtectedBaseBleu.setOwners(owner);
		rgManager.addRegion(ProtectedBaseBleu);
        ProtectedBaseBleu.setFlag(DefaultFlag.BLOCK_PLACE, StateFlag.State.ALLOW);
        ProtectedBaseBleu.setFlag(DefaultFlag.BLOCK_BREAK, StateFlag.State.ALLOW);
        ProtectedBaseBleu.setFlag(DefaultFlag.PVP, StateFlag.State.ALLOW);
        ProtectedBaseBleu.setFlag(DefaultFlag.BUILD, StateFlag.State.ALLOW);
        ProtectedBaseBleu.setFlag(DefaultFlag.CHEST_ACCESS, StateFlag.State.ALLOW);
        ProtectedBaseBleu.setFlag(DefaultFlag.CREEPER_EXPLOSION, StateFlag.State.ALLOW);
        ProtectedBaseBleu.setFlag(DefaultFlag.DAMAGE_ANIMALS, StateFlag.State.ALLOW);
        ProtectedBaseBleu.setFlag(DefaultFlag.ENDERPEARL, StateFlag.State.ALLOW);
		ProtectedBaseBleu.setFlag(DefaultFlag.ENTRY, StateFlag.State.ALLOW);
		ProtectedBaseBleu.setFlag(DefaultFlag.EXIT, StateFlag.State.ALLOW);
		ProtectedBaseBleu.setFlag(DefaultFlag.EXP_DROPS, StateFlag.State.ALLOW);
		ProtectedBaseBleu.setFlag(DefaultFlag.INTERACT, StateFlag.State.ALLOW);
		ProtectedBaseBleu.setFlag(DefaultFlag.MOB_DAMAGE, StateFlag.State.ALLOW);
		ProtectedBaseBleu.setFlag(DefaultFlag.MOB_SPAWNING, StateFlag.State.ALLOW);
		ProtectedBaseBleu.setFlag(DefaultFlag.TNT, StateFlag.State.ALLOW);
		ProtectedBaseBleu.setFlag(DefaultFlag.PISTONS, StateFlag.State.ALLOW);
		ProtectedBaseBleu.setFlag(DefaultFlag.OTHER_EXPLOSION, StateFlag.State.ALLOW);


		
	}
	public static void RegionRouge(Player player, World world){
		BlockVector b1 = new BlockVector(player.getLocation().getBlockX()+13,player.getLocation().getBlockY()+ 200,player.getLocation().getBlockZ()+10);
        BlockVector b2 = new BlockVector(player.getLocation().getBlockX()-13,player.getLocation().getBlockY()-200,player.getLocation().getBlockZ()-10);
        ProtectedCuboidRegion ProtectedBaseRouge = new ProtectedCuboidRegion("BaseRouge", b1, b2);
        RegionManager rgManager = WGBukkit.getRegionManager(world);
		DefaultDomain owner = new DefaultDomain();
		owner.addPlayer(player.getName());
		ProtectedBaseRouge.setOwners(owner);
		rgManager.addRegion(ProtectedBaseRouge);
		ProtectedBaseRouge.setFlag(DefaultFlag.BLOCK_PLACE, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.BLOCK_BREAK, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.PVP, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.BUILD, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.CHEST_ACCESS, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.CREEPER_EXPLOSION, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.DAMAGE_ANIMALS, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.ENDERPEARL, StateFlag.State.ALLOW);
        ProtectedBaseRouge.setFlag(DefaultFlag.ENTRY, StateFlag.State.ALLOW);
        ProtectedBaseRouge.setFlag(DefaultFlag.EXIT, StateFlag.State.ALLOW);
        ProtectedBaseRouge.setFlag(DefaultFlag.EXP_DROPS, StateFlag.State.ALLOW);
        ProtectedBaseRouge.setFlag(DefaultFlag.INTERACT, StateFlag.State.ALLOW);
        ProtectedBaseRouge.setFlag(DefaultFlag.MOB_DAMAGE, StateFlag.State.ALLOW);
        ProtectedBaseRouge.setFlag(DefaultFlag.MOB_SPAWNING, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.TNT, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.PISTONS, StateFlag.State.ALLOW);
		ProtectedBaseRouge.setFlag(DefaultFlag.OTHER_EXPLOSION, StateFlag.State.ALLOW);


		
	}
	public static void RegionVert(Player player, World world){
		BlockVector b1 = new BlockVector(player.getLocation().getBlockX()+13,player.getLocation().getBlockY()+ 200,player.getLocation().getBlockZ()+10);
        BlockVector b2 = new BlockVector(player.getLocation().getBlockX()-13,player.getLocation().getBlockY()-200,player.getLocation().getBlockZ()-10);
        ProtectedCuboidRegion ProtectedBaseVert = new ProtectedCuboidRegion("BaseVert", b1, b2);
        RegionManager rgManager = WGBukkit.getRegionManager(world);
		DefaultDomain owner = new DefaultDomain();
		owner.addPlayer(player.getName());
		ProtectedBaseVert.setOwners(owner);
		rgManager.addRegion(ProtectedBaseVert);
		ProtectedBaseVert.setFlag(DefaultFlag.BLOCK_PLACE, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.BLOCK_BREAK, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.PVP, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.BUILD, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.CHEST_ACCESS, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.CREEPER_EXPLOSION, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.DAMAGE_ANIMALS, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.ENDERPEARL, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.ENTRY, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.EXIT, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.EXP_DROPS, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.INTERACT, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.MOB_DAMAGE, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.MOB_SPAWNING, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.TNT, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.PISTONS, StateFlag.State.ALLOW);
		ProtectedBaseVert.setFlag(DefaultFlag.OTHER_EXPLOSION, StateFlag.State.ALLOW);


		
	}
	public static void RegionJaune(Player player, World world){
		BlockVector b1 = new BlockVector(player.getLocation().getBlockX()+13,player.getLocation().getBlockY()+ 200,player.getLocation().getBlockZ()+10);
        BlockVector b2 = new BlockVector(player.getLocation().getBlockX()-13,player.getLocation().getBlockY()-200,player.getLocation().getBlockZ()-10);
        ProtectedCuboidRegion ProtectedBasejaune = new ProtectedCuboidRegion("BaseJaune", b1, b2);
		RegionManager rgManager = WGBukkit.getRegionManager(world);
		DefaultDomain owner = new DefaultDomain();
		owner.addPlayer(player.getName());
		ProtectedBasejaune.setOwners(owner);
		rgManager.addRegion(ProtectedBasejaune);
		ProtectedBasejaune.setFlag(DefaultFlag.BLOCK_PLACE, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.BLOCK_BREAK, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.PVP, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.BUILD, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.CHEST_ACCESS, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.CREEPER_EXPLOSION, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.DAMAGE_ANIMALS, StateFlag.State.ALLOW);
        ProtectedBasejaune.setFlag(DefaultFlag.ENDERPEARL, StateFlag.State.ALLOW);
        ProtectedBasejaune.setFlag(DefaultFlag.ENTRY, StateFlag.State.ALLOW);
        ProtectedBasejaune.setFlag(DefaultFlag.EXIT, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.EXP_DROPS, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.INTERACT, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.MOB_DAMAGE, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.MOB_SPAWNING, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.TNT, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.PISTONS, StateFlag.State.ALLOW);
		ProtectedBasejaune.setFlag(DefaultFlag.OTHER_EXPLOSION, StateFlag.State.ALLOW);
		
	}

}
