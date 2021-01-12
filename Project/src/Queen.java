import java.util.ArrayList;

/**
 * move logic for queen
 */
public class Queen extends Figure {
	
	public Queen(int color, int associatedPlayerId, Direction direction) {
		super(FigureType.QUEEN, direction, associatedPlayerId, color);
	}
	
	@Override
	public void setMovableFields(GameScreen screen, Field srcField) {
		ArrayList<ArrayList<Field>> list = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			list.add(new ArrayList<>());
		}
		for (Field[] fields: screen.felder) {
			for (Field field: fields) {
				if (field != null) {
					
					int rowDelta = field.x - srcField.x;
					int colDelta = field.y - srcField.y;
					
					if (rowDelta > 0 && colDelta == rowDelta) {
						list.get(0).add(field);
					} else if (rowDelta > 0 && colDelta == (rowDelta * -1)) {
						list.get(1).add(field);
					} else if (rowDelta < 0 && colDelta == rowDelta) {
						list.get(2).add(field);
					} else if (rowDelta < 0 && colDelta == (rowDelta * -1)) {
						list.get(3).add(field);
					} else if (rowDelta == 0 && colDelta > 0) {
						list.get(4).add(field);
					} else if (rowDelta == 0 && colDelta < 0) {
						list.get(5).add(field);
					} else if (rowDelta > 0 && colDelta == 0) {
						list.get(6).add(field);
					} else if (rowDelta < 0 && colDelta == 0) {
						list.get(7).add(field);
					} else {
						field.setValidMove(Field.Move.DEFAULT);
					}
				}
			}
		}
		setValidStraightLineFields(list, srcField);
	}
}
