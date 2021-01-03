public class Game {
	
	public boolean isServer;
	
	public GameProperties properties = new GameProperties();
	
	public void placeFigures(GameScreen screen) {
		if (properties.playerCount >= 1) {
			screen.setFigureToPosition(0, 3, new Figure(FigureType.ROOK, 0, Direction.DOWN));
			screen.setFigureToPosition(0, 4, new Figure(FigureType.KNIGHT, 0, Direction.DOWN));
			screen.setFigureToPosition(0, 5, new Figure(FigureType.BISHOP, 0, Direction.DOWN));
			screen.setFigureToPosition(0, 6, new Figure(FigureType.QUEEN, 0, Direction.DOWN));
			screen.setFigureToPosition(0, 7, new Figure(FigureType.KING, 0, Direction.DOWN));
			screen.setFigureToPosition(0, 8, new Figure(FigureType.BISHOP, 0, Direction.DOWN));
			screen.setFigureToPosition(0, 9, new Figure(FigureType.KNIGHT, 0, Direction.DOWN));
			screen.setFigureToPosition(0, 10, new Figure(FigureType.ROOK, 0, Direction.DOWN));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(1, i, new Figure(FigureType.PAWN, 0, Direction.DOWN));
			}
		}
		if (properties.playerCount >= 2) {
			screen.setFigureToPosition(13, 3, new Figure(FigureType.ROOK, 1, Direction.UP));
			screen.setFigureToPosition(13, 4, new Figure(FigureType.KNIGHT, 1, Direction.UP));
			screen.setFigureToPosition(13, 5, new Figure(FigureType.BISHOP, 1, Direction.UP));
			screen.setFigureToPosition(13, 6, new Figure(FigureType.QUEEN, 1, Direction.UP));
			screen.setFigureToPosition(13, 7, new Figure(FigureType.KING, 1, Direction.UP));
			screen.setFigureToPosition(13, 8, new Figure(FigureType.BISHOP, 1, Direction.UP));
			screen.setFigureToPosition(13, 9, new Figure(FigureType.KNIGHT, 1, Direction.UP));
			screen.setFigureToPosition(13, 10, new Figure(FigureType.ROOK, 1, Direction.UP));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(12, i, new Figure(FigureType.PAWN, 1, Direction.UP));
			}
		}
		if (properties.playerCount >= 3) {
			screen.setFigureToPosition(3, 0, new Figure(FigureType.ROOK, 11, Direction.RIGHT));
			screen.setFigureToPosition(4, 0, new Figure(FigureType.KNIGHT, 11, Direction.RIGHT));
			screen.setFigureToPosition(5, 0, new Figure(FigureType.BISHOP, 11, Direction.RIGHT));
			screen.setFigureToPosition(6, 0, new Figure(FigureType.QUEEN, 11, Direction.RIGHT));
			screen.setFigureToPosition(7, 0, new Figure(FigureType.KING, 11, Direction.RIGHT));
			screen.setFigureToPosition(8, 0, new Figure(FigureType.BISHOP, 11, Direction.RIGHT));
			screen.setFigureToPosition(9, 0, new Figure(FigureType.KNIGHT, 11, Direction.RIGHT));
			screen.setFigureToPosition(10, 0, new Figure(FigureType.ROOK, 11, Direction.RIGHT));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(i, 1, new Figure(FigureType.PAWN, 11, Direction.RIGHT));
			}
		}
		if (properties.playerCount >= 4) {
			screen.setFigureToPosition(3, 13, new Figure(FigureType.ROOK, 10, Direction.LEFT));
			screen.setFigureToPosition(4, 13, new Figure(FigureType.KNIGHT, 10, Direction.LEFT));
			screen.setFigureToPosition(5, 13, new Figure(FigureType.BISHOP, 10, Direction.LEFT));
			screen.setFigureToPosition(6, 13, new Figure(FigureType.QUEEN, 10, Direction.LEFT));
			screen.setFigureToPosition(7, 13, new Figure(FigureType.KING, 10, Direction.LEFT));
			screen.setFigureToPosition(8, 13, new Figure(FigureType.BISHOP, 10, Direction.LEFT));
			screen.setFigureToPosition(9, 13, new Figure(FigureType.KNIGHT, 10, Direction.LEFT));
			screen.setFigureToPosition(10, 13, new Figure(FigureType.ROOK, 10, Direction.LEFT));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(i, 12, new Figure(FigureType.PAWN, 10, Direction.LEFT));
			}
		}
	}
	
}
