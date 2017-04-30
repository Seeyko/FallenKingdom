package net.Seeyko.fr.events;


public enum GameState {
	
	LOBBY(true), 
	Day1(false),
	Day2(false), 
	Day3(false),	
	Day4(false),
	Day5IF(false),
	FINISH(false);
	
	private static GameState current;
	private boolean canJoin;
	
	GameState(boolean b) {
    	canJoin = b;
    	
    }
	 public boolean canJoin(){
	    	return canJoin;
	    }
	    	
	    
	
	    public static void setState(GameState state) {
	    	current = state;
	    	
	    }
	    public static boolean isState(GameState state){
	    	return current == state;
	    	
	    }
	    
	    public static GameState getState() {
	    	return current;
	    }
}
