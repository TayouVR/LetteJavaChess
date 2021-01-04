public class Knight extends Figure {
	
	public Knight(int color, Direction direction) {
		super(FigureType.KNIGHT, color, direction);
	}
	
	@Override
	public void setMovableFields(GameScreen screen, Field srcField) {
		for (Field[] fields: screen.felder) {
			for (Field field: fields) {
				if (field != null) {
					
					int rowDelta = Math.abs(field.x - srcField.x);
					int colDelta = Math.abs(field.y - srcField.y);
					
					if (((rowDelta == 1) && (colDelta == 2)) || ((rowDelta == 2) && (colDelta == 1))) {
						if (field.getFigure() == null) {
							field.setValidMove(Field.Move.MOVE);
						} else if (field.getFigure().direction != direction) {
							field.setValidMove(Field.Move.ATTACK);
						} else {
							field.setValidMove(Field.Move.DEFAULT);
						}
					} else {
						field.setValidMove(Field.Move.DEFAULT);
					}
				}
			}
		}
	}
}
