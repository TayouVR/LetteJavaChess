/**
 * move logic for king
 */
public class King extends Figure {
	
	public King(int color, int associatedPlayerId, Direction direction) {
		super(FigureType.KING, direction, associatedPlayerId, color);
	}
	
	@Override
	public boolean setMovableFields() {
		int validMoveCount = 0;
		Field srcField = field;
		for (Field[] fields: UserInterfaceThread.game.felder) {
			for (Field field: fields) {
				if (field != null) {
					
					int rowDelta = Math.abs(field.pos.x - srcField.pos.x);
					int colDelta = Math.abs(field.pos.y - srcField.pos.y);
					
					if ((rowDelta == 1 && colDelta <= 1) || (colDelta == 1 && rowDelta <= 1)) {
						if (field.getFigure() == null) {
							field.setValidMove(Field.Move.MOVE);
							validMoveCount++;
						} else if (field.getFigure().direction != direction) {
							field.setValidMove(Field.Move.ATTACK);
							validMoveCount++;
						}
					} else {
						field.setValidMove(Field.Move.DEFAULT);
					}
				}
			}
		}
		return validMoveCount != 0;
	}
}
