public class Game {
	
	public boolean isServer;
	
	public GameProperties properties = new GameProperties();
	
	public Player[] players = new Player[4];
	
	public void placeFigures(GameScreen screen, int color1, int color2, int color3, int color4) {
		if (properties.playerCount >= 1) {
			screen.setFigureToPosition(0, 3, new Rook(color1, Direction.DOWN));
			screen.setFigureToPosition(0, 4, new Knight(color1, Direction.DOWN));
			screen.setFigureToPosition(0, 5, new Bishop(color1, Direction.DOWN));
			screen.setFigureToPosition(0, 6, new Queen(color1, Direction.DOWN));
			screen.setFigureToPosition(0, 7, new King(color1, Direction.DOWN));
			screen.setFigureToPosition(0, 8, new Bishop(color1, Direction.DOWN));
			screen.setFigureToPosition(0, 9, new Knight(color1, Direction.DOWN));
			screen.setFigureToPosition(0, 10, new Rook(color1, Direction.DOWN));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(1, i, new Pawn(color1, Direction.DOWN));
			}
		}
		if (properties.playerCount >= 2) {
			screen.setFigureToPosition(13, 3, new Rook(color2, Direction.UP));
			screen.setFigureToPosition(13, 4, new Knight(color2, Direction.UP));
			screen.setFigureToPosition(13, 5, new Bishop(color2, Direction.UP));
			screen.setFigureToPosition(13, 6, new Queen(color2, Direction.UP));
			screen.setFigureToPosition(13, 7, new King(color2, Direction.UP));
			screen.setFigureToPosition(13, 8, new Bishop(color2, Direction.UP));
			screen.setFigureToPosition(13, 9, new Knight(color2, Direction.UP));
			screen.setFigureToPosition(13, 10, new Rook(color2, Direction.UP));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(12, i, new Pawn(color2, Direction.UP));
			}
		}
		if (properties.playerCount >= 3) {
			screen.setFigureToPosition(3, 0, new Rook(color3, Direction.RIGHT));
			screen.setFigureToPosition(4, 0, new Knight(color3, Direction.RIGHT));
			screen.setFigureToPosition(5, 0, new Bishop(color3, Direction.RIGHT));
			screen.setFigureToPosition(6, 0, new Queen(color3, Direction.RIGHT));
			screen.setFigureToPosition(7, 0, new King(color3, Direction.RIGHT));
			screen.setFigureToPosition(8, 0, new Bishop(color3, Direction.RIGHT));
			screen.setFigureToPosition(9, 0, new Knight(color3, Direction.RIGHT));
			screen.setFigureToPosition(10, 0, new Rook(color3, Direction.RIGHT));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(i, 1, new Pawn(color3, Direction.RIGHT));
			}
		}
		if (properties.playerCount >= 4) {
			screen.setFigureToPosition(3, 13, new Rook(color4, Direction.LEFT));
			screen.setFigureToPosition(4, 13, new Knight(color4, Direction.LEFT));
			screen.setFigureToPosition(5, 13, new Bishop(color4, Direction.LEFT));
			screen.setFigureToPosition(6, 13, new Queen(color4, Direction.LEFT));
			screen.setFigureToPosition(7, 13, new King(color4, Direction.LEFT));
			screen.setFigureToPosition(8, 13, new Bishop(color4, Direction.LEFT));
			screen.setFigureToPosition(9, 13, new Knight(color4, Direction.LEFT));
			screen.setFigureToPosition(10, 13, new Rook(color4, Direction.LEFT));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(i, 12, new Pawn(color4, Direction.LEFT));
			}
		}
	}
	
}
