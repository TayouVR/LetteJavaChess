public class Game {
	
	public boolean isServer;
	
	public GameProperties properties = new GameProperties();
	
	public void placeFigures(GameScreen screen) {
		if (properties.playerCount >= 1) {
			screen.setFigureToPosition(0, 3, new Rook(0, Direction.DOWN));
			screen.setFigureToPosition(0, 4, new Knight(0, Direction.DOWN));
			screen.setFigureToPosition(0, 5, new Bishop(0, Direction.DOWN));
			screen.setFigureToPosition(0, 6, new Queen(0, Direction.DOWN));
			screen.setFigureToPosition(0, 7, new King(0, Direction.DOWN));
			screen.setFigureToPosition(0, 8, new Bishop(0, Direction.DOWN));
			screen.setFigureToPosition(0, 9, new Knight(0, Direction.DOWN));
			screen.setFigureToPosition(0, 10, new Rook(0, Direction.DOWN));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(1, i, new Pawn(0, Direction.DOWN));
			}
		}
		if (properties.playerCount >= 2) {
			screen.setFigureToPosition(13, 3, new Rook(1, Direction.UP));
			screen.setFigureToPosition(13, 4, new Knight(1, Direction.UP));
			screen.setFigureToPosition(13, 5, new Bishop(1, Direction.UP));
			screen.setFigureToPosition(13, 6, new Queen(1, Direction.UP));
			screen.setFigureToPosition(13, 7, new King(1, Direction.UP));
			screen.setFigureToPosition(13, 8, new Bishop(1, Direction.UP));
			screen.setFigureToPosition(13, 9, new Knight(1, Direction.UP));
			screen.setFigureToPosition(13, 10, new Rook(1, Direction.UP));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(12, i, new Pawn(1, Direction.UP));
			}
		}
		if (properties.playerCount >= 3) {
			screen.setFigureToPosition(3, 0, new Rook(11, Direction.RIGHT));
			screen.setFigureToPosition(4, 0, new Knight(11, Direction.RIGHT));
			screen.setFigureToPosition(5, 0, new Bishop(11, Direction.RIGHT));
			screen.setFigureToPosition(6, 0, new Queen(11, Direction.RIGHT));
			screen.setFigureToPosition(7, 0, new King(11, Direction.RIGHT));
			screen.setFigureToPosition(8, 0, new Bishop(11, Direction.RIGHT));
			screen.setFigureToPosition(9, 0, new Knight(11, Direction.RIGHT));
			screen.setFigureToPosition(10, 0, new Rook(11, Direction.RIGHT));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(i, 1, new Pawn(11, Direction.RIGHT));
			}
		}
		if (properties.playerCount >= 4) {
			screen.setFigureToPosition(3, 13, new Rook(10, Direction.LEFT));
			screen.setFigureToPosition(4, 13, new Knight(10, Direction.LEFT));
			screen.setFigureToPosition(5, 13, new Bishop(10, Direction.LEFT));
			screen.setFigureToPosition(6, 13, new Queen(10, Direction.LEFT));
			screen.setFigureToPosition(7, 13, new King(10, Direction.LEFT));
			screen.setFigureToPosition(8, 13, new Bishop(10, Direction.LEFT));
			screen.setFigureToPosition(9, 13, new Knight(10, Direction.LEFT));
			screen.setFigureToPosition(10, 13, new Rook(10, Direction.LEFT));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(i, 12, new Pawn(10, Direction.LEFT));
			}
		}
	}
	
}
