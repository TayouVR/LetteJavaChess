public class Game {
	
	public boolean isServer;
	
	public GameProperties properties = new GameProperties();
	
	public void placeFigures(GameScreen screen) {
		if (properties.playerCount >= 1) {
			screen.setFigureToPosition(0, 3, new Figure(FigureType.ROOK));
			screen.setFigureToPosition(0, 4, new Figure(FigureType.KNIGHT));
			screen.setFigureToPosition(0, 5, new Figure(FigureType.BISHOP));
			screen.setFigureToPosition(0, 6, new Figure(FigureType.QUEEN));
			screen.setFigureToPosition(0, 7, new Figure(FigureType.KING));
			screen.setFigureToPosition(0, 8, new Figure(FigureType.BISHOP));
			screen.setFigureToPosition(0, 9, new Figure(FigureType.KNIGHT));
			screen.setFigureToPosition(0, 10, new Figure(FigureType.ROOK));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(1, i, new Figure(FigureType.PAWN));
			}
		}
		if (properties.playerCount >= 2) {
			screen.setFigureToPosition(13, 3, new Figure(FigureType.ROOK));
			screen.setFigureToPosition(13, 4, new Figure(FigureType.KNIGHT));
			screen.setFigureToPosition(13, 5, new Figure(FigureType.BISHOP));
			screen.setFigureToPosition(13, 6, new Figure(FigureType.QUEEN));
			screen.setFigureToPosition(13, 7, new Figure(FigureType.KING));
			screen.setFigureToPosition(13, 8, new Figure(FigureType.BISHOP));
			screen.setFigureToPosition(13, 9, new Figure(FigureType.KNIGHT));
			screen.setFigureToPosition(13, 10, new Figure(FigureType.ROOK));
			
			// Bauern
			for (int i = 3; i < 11; i++) {
				screen.setFigureToPosition(12, i, new Figure(FigureType.PAWN));
			}
		}
	}
	
}
