/**
 * move logic for king
 */
public class King extends Figure {
	
	public King(int color, int associatedPlayerId, Direction direction) {
		super(FigureType.KING, direction, associatedPlayerId, color);
	}
	
	@Override
	public void setMovableFields(GameScreen screen, Field srcField) {
		for (Field[] fields: screen.felder) {
			for (Field field: fields) {
				if (field != null) {
					
					int rowDelta = Math.abs(field.x - srcField.x);
					int colDelta = Math.abs(field.y - srcField.y);
					
					if ((rowDelta == 1 && colDelta <= 1) || (colDelta == 1 && rowDelta <= 1)) {
						if (field.getFigure() == null) {
							field.setValidMove(Field.Move.MOVE);
						} else if (field.getFigure().direction != direction) {
							field.setValidMove(Field.Move.ATTACK);
						}
					} else {
						field.setValidMove(Field.Move.DEFAULT);
					}
				}
			}
		}
	}
}
