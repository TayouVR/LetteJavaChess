public class GameProperties {
	
	public int playerCount; 					// Player count
	public PlayField map; 						// play field
	
	public boolean hasTimeLimit; 				// whether or not the game has a time limit
	public int fullGameTimer; 					// maximum Time for the entire game
	
	public boolean hasMoveTimer; 				// whether or not moves have a time limit
	public int perMoveTimer; 					// maximum time per move

	public boolean hasWarningTimer;				// whether or not theres a Warning for the Time
	public int warningTimer;					// Time when the Warning pops up
	
}
