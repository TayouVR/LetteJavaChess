import java.util.ArrayList;

/**
 * Bishop Figure
 */
public class Bishop extends Figure {
	
	public Bishop(int color, int associatedPlayerId, Direction direction) {
		super(FigureType.BISHOP, direction, associatedPlayerId, color);
	}
	
	@Override
	public boolean setMovableFields() {
		Field srcField = field;
		ArrayList<ArrayList<Field>> list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			list.add(new ArrayList<>());
		}
		for (Field[] fields: UserInterfaceThread.game.felder) {
			for (Field field: fields) {
				if (field != null) {
					
					int rowDelta = field.pos.x - srcField.pos.x;
					int colDelta = field.pos.y - srcField.pos.y;
					
					if (rowDelta > 0 && colDelta == rowDelta) {
						list.get(0).add(field);
					} else if (rowDelta > 0 && colDelta == (rowDelta * -1)) {
						list.get(1).add(field);
					} else if (rowDelta < 0 && colDelta == rowDelta) {
						list.get(2).add(field);
					} else if (rowDelta < 0 && colDelta == (rowDelta * -1)) {
						list.get(3).add(field);
					} else {
						field.setValidMove(Field.Move.DEFAULT);
					}
				}
			}
		}
		return setValidStraightLineFields(list, srcField) != 0;
	}
}
