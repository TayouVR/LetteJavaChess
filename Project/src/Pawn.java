public class Pawn extends Figure {
	
	public Pawn(int color, int associatedPlayerId, Direction direction) {
		super(FigureType.PAWN, direction, associatedPlayerId, color);
	}
	
	@Override
	public void setMovableFields(GameScreen screen, Field srcField) {
		for (Field[] fields: screen.felder) {
			for (Field field: fields) {
				if (field != null) {
					int rowDelta = field.x - srcField.x;
					int colDelta = field.y - srcField.y;
					
					int rowDeltaAbs = Math.abs(field.x - srcField.x);
					int colDeltaAbs = Math.abs(field.y - srcField.y);
					
					switch (direction) {
						case UP -> {
							if (field.getFigure() != null) {
								if (field.getFigure().direction != direction) {
									if (rowDelta == -1 && colDeltaAbs == 1) {
										field.setValidMove(Field.Move.ATTACK);
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							} else {
								if (isFirstMove) {
									if (rowDelta == -1 && colDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
									} else if (rowDelta == -2 && colDeltaAbs == 0 && screen.felder[srcField.x-1][srcField.y].getFigure() == null) {
										field.setValidMove(Field.Move.MOVE);
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								} else {
									if (rowDelta == -1 && colDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
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
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							} else {
								if (isFirstMove) {
									if (rowDelta == 1 && colDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
									} else if (rowDelta == 2 && colDeltaAbs == 0 && screen.felder[srcField.x+1][srcField.y].getFigure() == null) {
										field.setValidMove(Field.Move.MOVE);
									}  else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								} else {
									if (rowDelta == 1 && colDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
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
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							} else {
								if (isFirstMove) {
									if (colDelta == -1 && rowDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
									} else if (colDelta == -2 && rowDeltaAbs == 0 && screen.felder[srcField.x][srcField.y-1].getFigure() == null) {
										field.setValidMove(Field.Move.MOVE);
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								} else {
									if (colDelta == -1 && rowDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
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
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								}
							} else {
								if (isFirstMove) {
									if (colDelta == 1 && rowDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
									} else if (colDelta == 2 && rowDeltaAbs == 0 && screen.felder[srcField.x][srcField.y+1].getFigure() == null) {
										field.setValidMove(Field.Move.MOVE);
									} else {
										field.setValidMove(Field.Move.DEFAULT);
									}
								} else {
									if (colDelta == 1 && rowDeltaAbs == 0) {
										field.setValidMove(Field.Move.MOVE);
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
		
	}
}
