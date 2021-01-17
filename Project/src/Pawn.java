/**
 * move logic for pawn
 */
public class Pawn extends Figure {
	
	public Pawn(int color, int associatedPlayerId, Direction direction) {
		super(FigureType.PAWN, direction, associatedPlayerId, color);
	}
	
	@Override
	public boolean setMovableFields() {
		int validMoveCount = 0;
		Field srcField = field;
		for (Field[] fields: UserInterfaceThread.game.felder) {
			for (Field field: fields) {
				if (field != null) {
					int rowDelta = field.pos.x - srcField.pos.x;
					int colDelta = field.pos.y - srcField.pos.y;
					
					int rowDeltaAbs = Math.abs(field.pos.x - srcField.pos.x);
					int colDeltaAbs = Math.abs(field.pos.y - srcField.pos.y);
					
					// go through all directions the pawn could be moving in
					switch (direction) {
						case UP -> {
							if (field.getFigure() != null) {
								if (field.getFigure().direction != direction) {
									if (rowDelta == -1 && colDeltaAbs == 1) {
										field.setValidMove(Field.Move.ATTACK);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							} else {
								if (isFirstMove) {
									if (rowDelta == -1 && colDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else if (rowDelta == -2 && colDeltaAbs == 0 && UserInterfaceThread.game.getField(new Vector2int(srcField.pos.x-1, srcField.pos.y)).getFigure() == null) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								} else {
									if (rowDelta == -1 && colDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							}
						}
						case DOWN -> {
							if (field.getFigure() != null) {
								if (field.getFigure().direction != direction) {
									if (rowDelta == 1 && colDeltaAbs == 1) {
										field.setValidMove(Field.Move.ATTACK);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							} else {
								if (isFirstMove) {
									if (rowDelta == 1 && colDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else if (rowDelta == 2 && colDeltaAbs == 0 && UserInterfaceThread.game.felder[srcField.pos.x+1][srcField.pos.y].getFigure() == null) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									}  else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								} else {
									if (rowDelta == 1 && colDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							}
						}
						case LEFT -> {
							if (field.getFigure() != null) {
								if (field.getFigure().direction != direction) {
									if (colDelta == -1 && rowDeltaAbs == 1) {
										field.setValidMove(Field.Move.ATTACK);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							} else {
								if (isFirstMove) {
									if (colDelta == -1 && rowDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else if (colDelta == -2 && rowDeltaAbs == 0 && UserInterfaceThread.game.felder[srcField.pos.x][srcField.pos.y-1].getFigure() == null) {
										field.setValidMove(Field.Move.MOVE);
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								} else {
									if (colDelta == -1 && rowDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							}
						}
						case RIGHT -> {
							if (field.getFigure() != null) {
								if (field.getFigure().direction != direction) {
									if (colDelta == 1 && rowDeltaAbs == 1) {
										field.setValidMove(Field.Move.ATTACK);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							} else {
								if (isFirstMove) {
									if (colDelta == 1 && rowDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else if (colDelta == 2 && rowDeltaAbs == 0 && UserInterfaceThread.game.felder[srcField.pos.x][srcField.pos.y+1].getFigure() == null) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								} else {
									if (colDelta == 1 && rowDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
										validMoveCount++;
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							}
						}
					}
					// directions
				}
			}
		}
		return validMoveCount != 0;
		
	}
}
