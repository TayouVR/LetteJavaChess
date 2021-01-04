import java.util.ArrayList;

public class Rook extends Figure {
	
	public Rook(int color, Direction direction) {
		super(FigureType.ROOK, color, direction);
	}
	
	@Override
	public void setMovableFields(GameScreen screen, Field srcField) {
		
		ArrayList<ArrayList<Field>> list = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			list.add(new ArrayList<>());
		}
		for (Field[] fields: screen.felder) {
			for (Field field: fields) {
				if (field != null) {
					
					int rowDelta = field.x - srcField.x;
					int colDelta = field.y - srcField.y;
					
					if (rowDelta == 0 && colDelta > 0) {
						list.get(0).add(field);
					} else if (rowDelta == 0 && colDelta < 0) {
						list.get(1).add(field);
					} else if (rowDelta > 0 && colDelta == 0) {
						list.get(2).add(field);
					} else if (rowDelta < 0 && colDelta == 0) {
						list.get(3).add(field);
					} else {
						field.setValidMove(Field.Move.DEFAULT);
					}
				}
			}
		}
		setValidStraightLineFields(list, srcField, 4);
	}
}
