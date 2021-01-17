/**
 * move logic for knight
 */
public class Knight extends Figure {
	
	public Knight(int color, int associatedPlayerId, Direction direction) {
		super(FigureType.KNIGHT, direction, associatedPlayerId, color);
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
					
					if (((rowDelta == 1) && (colDelta == 2)) || ((rowDelta == 2) && (colDelta == 1))) {
						if (field.getFigure() == null) {
							field.setValidMove(Field.Move.MOVE);
							validMoveCount++;
						} else if (field.getFigure().direction != direction) {
							field.setValidMove(Field.Move.ATTACK);
							validMoveCount++;
						} else {
							field.setValidMove(Field.Move.DEFAULT);
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
