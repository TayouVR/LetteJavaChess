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
		if (players[currentPlayerTurn-1].isAi) {
			players[currentPlayerTurn-1].doAutomaticMove();
			nextPlayerTurn();
		}
	}

	public void setPlayerCount(int count) {
		properties.playerCount = count;
		for (int i = 0; i < 4; i++) {
			if (i < count) {
				if (players[i] == null) {
					players[i] = new Player();
				}
			} else {
				players[i] = null;
			}
		}
		if (properties.playerCount >= 1) {
			players[0].colorIndex = UserInterfaceThread.preGame.comboBox_player0color.getSelectedIndex();
			players[0].name = !UserInterfaceThread.preGame.textField_player0Name.getText().equals("") ? UserInterfaceThread.preGame.textField_player0Name.getText() : "Unnamed 1";
			players[0].isAi = UserInterfaceThread.preGame.checkbox_ai_player1.isSelected();
			players[0].playerNameLabel = UserInterfaceThread.game.playerName1;
		}
		if (properties.playerCount >= 2) {
			players[1].colorIndex = UserInterfaceThread.preGame.comboBox_player1color.getSelectedIndex();
			players[1].name = !UserInterfaceThread.preGame.textField_player1Name.getText().equals("") ? UserInterfaceThread.preGame.textField_player1Name.getText() : "Unnamed 2";
			players[1].isAi = UserInterfaceThread.preGame.checkbox_ai_player2.isSelected();
			players[1].playerNameLabel = UserInterfaceThread.game.playerName2;
		}
		if (properties.playerCount >= 3) {
			players[2].colorIndex = UserInterfaceThread.preGame.comboBox_player2color.getSelectedIndex();
			players[2].name = !UserInterfaceThread.preGame.textField_player2Name.getText().equals("") ? UserInterfaceThread.preGame.textField_player2Name.getText() : "Unnamed 3";
			players[2].isAi = UserInterfaceThread.preGame.checkbox_ai_player3.isSelected();
			players[2].playerNameLabel = UserInterfaceThread.game.playerName3;
		}
		if (properties.playerCount >= 4) {
			players[3].colorIndex = UserInterfaceThread.preGame.comboBox_player3color.getSelectedIndex();
			players[3].name = !UserInterfaceThread.preGame.textField_player3Name.getText().equals("") ? UserInterfaceThread.preGame.textField_player3Name.getText() : "Unnamed 4";
			players[3].isAi = UserInterfaceThread.preGame.checkbox_ai_player4.isSelected();
			players[3].playerNameLabel = UserInterfaceThread.game.playerName4;
		}
	}
	
	/**
	 * initialize all the players and their figures
	 * @param screen GameScreen object which has the field
	 */
	public void initializeGame(GameScreen screen) {
		
		int playerCount = properties.playerCount;
		screen.playerName1.setVisible(playerCount >= 1);
		screen.playerName2.setVisible(playerCount >= 2);
		screen.playerName3.setVisible(playerCount >= 3);
		screen.playerName4.setVisible(playerCount >= 4);
		
		if (properties.playerCount >= 1) {
			screen.playerName1.setText(players[0].name);
			
			players[0].figures.add(screen.setFigureToPosition(0, 3, new Rook(players[0].colorIndex, 1, Direction.DOWN)));
			players[0].figures.add(screen.setFigureToPosition(0, 4, new Knight(players[0].colorIndex, 1, Direction.DOWN)));
			players[0].figures.add(screen.setFigureToPosition(0, 5, new Bishop(players[0].colorIndex, 1, Direction.DOWN)));
			players[0].figures.add(screen.setFigureToPosition(0, 6, new Queen(players[0].colorIndex, 1, Direction.DOWN)));
			players[0].figures.add(screen.setFigureToPosition(0, 7, new King(players[0].colorIndex, 1, Direction.DOWN)));
			players[0].figures.add(screen.setFigureToPosition(0, 8, new Bishop(players[0].colorIndex, 1, Direction.DOWN)));
			players[0].figures.add(screen.setFigureToPosition(0, 9, new Knight(players[0].colorIndex, 1, Direction.DOWN)));
			players[0].figures.add(screen.setFigureToPosition(0, 10, new Rook(players[0].colorIndex, 1, Direction.DOWN)));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				players[0].figures.add(screen.setFigureToPosition(1, i, new Pawn(players[0].colorIndex, 1, Direction.DOWN)));
			}
		}
		if (properties.playerCount >= 2) {
			screen.playerName2.setText(players[1].name);
			
			players[1].figures.add(screen.setFigureToPosition(13, 3, new Rook(players[1].colorIndex, 2, Direction.UP)));
			players[1].figures.add(screen.setFigureToPosition(13, 4, new Knight(players[1].colorIndex, 2, Direction.UP)));
			players[1].figures.add(screen.setFigureToPosition(13, 5, new Bishop(players[1].colorIndex, 2, Direction.UP)));
			players[1].figures.add(screen.setFigureToPosition(13, 6, new Queen(players[1].colorIndex, 2, Direction.UP)));
			players[1].figures.add(screen.setFigureToPosition(13, 7, new King(players[1].colorIndex, 2, Direction.UP)));
			players[1].figures.add(screen.setFigureToPosition(13, 8, new Bishop(players[1].colorIndex, 2, Direction.UP)));
			players[1].figures.add(screen.setFigureToPosition(13, 9, new Knight(players[1].colorIndex, 2, Direction.UP)));
			players[1].figures.add(screen.setFigureToPosition(13, 10, new Rook(players[1].colorIndex, 2, Direction.UP)));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				players[1].figures.add(screen.setFigureToPosition(12, i, new Pawn(players[1].colorIndex, 2, Direction.UP)));
			}
		}
		if (properties.playerCount >= 3) {
			screen.playerName3.setText(players[2].name);
			
			players[2].figures.add(screen.setFigureToPosition(3, 0, new Rook(players[2].colorIndex, 3, Direction.RIGHT)));
			players[2].figures.add(screen.setFigureToPosition(4, 0, new Knight(players[2].colorIndex, 3, Direction.RIGHT)));
			players[2].figures.add(screen.setFigureToPosition(5, 0, new Bishop(players[2].colorIndex, 3, Direction.RIGHT)));
			players[2].figures.add(screen.setFigureToPosition(6, 0, new Queen(players[2].colorIndex, 3, Direction.RIGHT)));
			players[2].figures.add(screen.setFigureToPosition(7, 0, new King(players[2].colorIndex, 3, Direction.RIGHT)));
			players[2].figures.add(screen.setFigureToPosition(8, 0, new Bishop(players[2].colorIndex, 3, Direction.RIGHT)));
			players[2].figures.add(screen.setFigureToPosition(9, 0, new Knight(players[2].colorIndex, 3, Direction.RIGHT)));
			players[2].figures.add(screen.setFigureToPosition(10, 0, new Rook(players[2].colorIndex, 3, Direction.RIGHT)));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				players[2].figures.add(screen.setFigureToPosition(i, 1, new Pawn(players[2].colorIndex, 3, Direction.RIGHT)));
			}
		}
		if (properties.playerCount >= 4) {
			screen.playerName4.setText(players[3].name);
			
			players[3].figures.add(screen.setFigureToPosition(3, 13, new Rook(players[3].colorIndex, 4, Direction.LEFT)));
			players[3].figures.add(screen.setFigureToPosition(4, 13, new Knight(players[3].colorIndex, 4, Direction.LEFT)));
			players[3].figures.add(screen.setFigureToPosition(5, 13, new Bishop(players[3].colorIndex, 4, Direction.LEFT)));
			players[3].figures.add(screen.setFigureToPosition(6, 13, new Queen(players[3].colorIndex, 4, Direction.LEFT)));
			players[3].figures.add(screen.setFigureToPosition(7, 13, new King(players[3].colorIndex, 4, Direction.LEFT)));
			players[3].figures.add(screen.setFigureToPosition(8, 13, new Bishop(players[3].colorIndex, 4, Direction.LEFT)));
			players[3].figures.add(screen.setFigureToPosition(9, 13, new Knight(players[3].colorIndex, 4, Direction.LEFT)));
			players[3].figures.add(screen.setFigureToPosition(10, 13, new Rook(players[3].colorIndex, 4, Direction.LEFT)));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				players[3].figures.add(screen.setFigureToPosition(i, 12, new Pawn(players[3].colorIndex, 4, Direction.LEFT)));
			}
		}
		nextPlayerTurn();
		
		
	}
	
}
