import java.awt.*;

/**
 * Handles game start and turn management, holds game settings
 */
public class Game {
	
	public boolean isServer;
	
	public GameProperties properties = new GameProperties();
	
	public Player[] players = new Player[4];
	
	private int currentPlayerTurn = 0;
	
	public int getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}
	
	/**
	 * Starts next players turn
	 */
	public void nextPlayerTurn() {
		currentPlayerTurn = (currentPlayerTurn % properties.playerCount) + 1;
		//if (!isServer) {
			for (int i = 0; i < players.length; i++) {
				players[i].playerNameLabel.setForeground(i == currentPlayerTurn - 1 ? Color.CYAN : SystemColor.textText);
			}
		//}
	}
	
	/**
	 * initialize all the players and their figures
	 * @param screen GameScreen object which has the field
	 * @param color1 color for first player
	 * @param color2 color for second player
	 * @param color3 color for third player
	 * @param color4 color for fourth player
	 */
	public void initializeGame(GameScreen screen, int color1, int color2, int color3, int color4) {
		
		int playerCount = properties.playerCount;
		screen.playerName1.setVisible(playerCount >= 1);
		screen.playerName2.setVisible(playerCount >= 2);
		screen.playerName3.setVisible(playerCount >= 3);
		screen.playerName4.setVisible(playerCount >= 4);
		
		for (int i = 0; i < playerCount; i++) {
			players[i] = new Player();
		}
		
		if (properties.playerCount >= 1) {
			players[0].playerNameLabel = screen.playerName1;
			players[0].name = !screen.userInterfaceThread.preGame.textField_player0Name.getText().equals("") ? screen.userInterfaceThread.preGame.textField_player0Name.getText() : "Unnamed 1";
			screen.playerName1.setText(players[0].name);
			
			screen.setFigureToPosition(0, 3, new Rook(color1, 1, Direction.DOWN));
			screen.setFigureToPosition(0, 4, new Knight(color1, 1, Direction.DOWN));
			screen.setFigureToPosition(0, 5, new Bishop(color1, 1, Direction.DOWN));
			screen.setFigureToPosition(0, 6, new Queen(color1, 1, Direction.DOWN));
			screen.setFigureToPosition(0, 7, new King(color1, 1, Direction.DOWN));
			screen.setFigureToPosition(0, 8, new Bishop(color1, 1, Direction.DOWN));
			screen.setFigureToPosition(0, 9, new Knight(color1, 1, Direction.DOWN));
			screen.setFigureToPosition(0, 10, new Rook(color1, 1, Direction.DOWN));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(1, i, new Pawn(color1, 1, Direction.DOWN));
			}
		}
		if (properties.playerCount >= 2) {
			players[1].playerNameLabel = screen.playerName2;
			players[1].name = !screen.userInterfaceThread.preGame.textField_player1Name.getText().equals("") ? screen.userInterfaceThread.preGame.textField_player1Name.getText() : "Unnamed 2";
			screen.playerName2.setText(players[1].name);
			
			screen.setFigureToPosition(13, 3, new Rook(color2, 2, Direction.UP));
			screen.setFigureToPosition(13, 4, new Knight(color2, 2, Direction.UP));
			screen.setFigureToPosition(13, 5, new Bishop(color2, 2, Direction.UP));
			screen.setFigureToPosition(13, 6, new Queen(color2, 2, Direction.UP));
			screen.setFigureToPosition(13, 7, new King(color2, 2, Direction.UP));
			screen.setFigureToPosition(13, 8, new Bishop(color2, 2, Direction.UP));
			screen.setFigureToPosition(13, 9, new Knight(color2, 2, Direction.UP));
			screen.setFigureToPosition(13, 10, new Rook(color2, 2, Direction.UP));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(12, i, new Pawn(color2, 2, Direction.UP));
			}
		}
		if (properties.playerCount >= 3) {
			players[2].playerNameLabel = screen.playerName3;
			players[2].name = !screen.userInterfaceThread.preGame.textField_player2Name.getText().equals("") ? screen.userInterfaceThread.preGame.textField_player2Name.getText() : "Unnamed 3";
			screen.playerName3.setText(players[2].name);
			
			screen.setFigureToPosition(3, 0, new Rook(color3, 3, Direction.RIGHT));
			screen.setFigureToPosition(4, 0, new Knight(color3, 3, Direction.RIGHT));
			screen.setFigureToPosition(5, 0, new Bishop(color3, 3, Direction.RIGHT));
			screen.setFigureToPosition(6, 0, new Queen(color3, 3, Direction.RIGHT));
			screen.setFigureToPosition(7, 0, new King(color3, 3, Direction.RIGHT));
			screen.setFigureToPosition(8, 0, new Bishop(color3, 3, Direction.RIGHT));
			screen.setFigureToPosition(9, 0, new Knight(color3, 3, Direction.RIGHT));
			screen.setFigureToPosition(10, 0, new Rook(color3, 3, Direction.RIGHT));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(i, 1, new Pawn(color3, 3, Direction.RIGHT));
			}
		}
		if (properties.playerCount >= 4) {
			players[3].playerNameLabel = screen.playerName4;
			players[3].name = !screen.userInterfaceThread.preGame.textField_player3Name.getText().equals("") ? screen.userInterfaceThread.preGame.textField_player3Name.getText() : "Unnamed 4";
			screen.playerName4.setText(players[3].name);
			
			screen.setFigureToPosition(3, 13, new Rook(color4, 4, Direction.LEFT));
			screen.setFigureToPosition(4, 13, new Knight(color4, 4, Direction.LEFT));
			screen.setFigureToPosition(5, 13, new Bishop(color4, 4, Direction.LEFT));
			screen.setFigureToPosition(6, 13, new Queen(color4, 4, Direction.LEFT));
			screen.setFigureToPosition(7, 13, new King(color4, 4, Direction.LEFT));
			screen.setFigureToPosition(8, 13, new Bishop(color4, 4, Direction.LEFT));
			screen.setFigureToPosition(9, 13, new Knight(color4, 4, Direction.LEFT));
			screen.setFigureToPosition(10, 13, new Rook(color4, 4, Direction.LEFT));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(i, 12, new Pawn(color4, 4, Direction.LEFT));
			}
		}
		nextPlayerTurn();
		
		
	}
	
}
