import javax.swing.*;
import java.awt.*;

public class Field extends JButton {
    public int x;
    public int y;
    private Figure figure;
    
    public boolean isBlack;
    private boolean isValidMove;
    
    private GameScreen gameScreen;
    
    public Field(GameScreen screen, boolean isBlack) {
        this.isBlack = isBlack;
        setBackground(isBlack ? new Color(100,100,100) : Color.WHITE);
        gameScreen = screen;
        addActionListener(e -> {
            if (isValidMove && gameScreen.selectedField != null) {
                setFigure(gameScreen.selectedField.figure);
                gameScreen.selectedField.figure.isFirstMove = false;
                gameScreen.selectedField.setFigure(null);
                for (Field[] fields: gameScreen.felder) {
                    for (Field field: fields) {
                        if (field != null) {
                            field.setValidMove(false);
                        }
                    }
                }
            } else if (figure != null) {
                setMoveable();
            }
        });
    }
    
    public void setFigure(Figure figure) {
        if (figure != null) {
            this.figure = figure;
            setIcon(figure.getSingleImage());
        } else {
            this.figure = null;
            setIcon(null);
        }
    }
    
    public Figure getFigure() {
        return figure;
    }
    
    public void setMoveable() {
        gameScreen.selectedField = this;
        switch (figure.type) {
            case KING -> {
                for (Field[] fields: gameScreen.felder) {
                    for (Field field: fields) {
                        if (field != null) {
                
                            int rowDelta = Math.abs(field.x - x);
                            int colDelta = Math.abs(field.y - y);
                
                            if ((rowDelta == 1 && colDelta <= 1) || (colDelta == 1 && rowDelta <= 1)) {
                                field.setValidMove(true);
                            } else {
                                field.setValidMove(false);
                            }
                        }
                    }
                }
            }
            case QUEEN -> {
                for (Field[] fields: gameScreen.felder) {
                    for (Field field: fields) {
                        if (field != null) {
                
                            int rowDelta = Math.abs(field.x - x);
                            int colDelta = Math.abs(field.y - y);
    
                            // diagonal & straight
                            if (rowDelta == colDelta && rowDelta != 0) {
                                field.setValidMove(true);
                            } else if ((rowDelta == 0 && colDelta != 0) || (colDelta == 0 && rowDelta != 0)) {
                                field.setValidMove(true);
                            } else {
                                field.setValidMove(false);
                            }
                        }
                    }
                }
            }
            case ROOK -> {
                for (Field[] fields: gameScreen.felder) {
                    for (Field field: fields) {
                        if (field != null) {
                
                            int rowDelta = Math.abs(field.x - x);
                            int colDelta = Math.abs(field.y - y);
    
                            // straight
                            if ((rowDelta == 0 && colDelta != 0) || (colDelta == 0 && rowDelta != 0)) {
                                field.setValidMove(true);
                            } else {
                                field.setValidMove(false);
                            }
                        }
                    }
                }
            }
            case BISHOP -> {
                for (Field[] fields: gameScreen.felder) {
                    for (Field field: fields) {
                        if (field != null) {
                
                            int rowDelta = Math.abs(field.x - x);
                            int colDelta = Math.abs(field.y - y);
    
                            // diagonal
                            if (rowDelta == colDelta && rowDelta != 0) {
                                field.setValidMove(true);
                            } else {
                                field.setValidMove(false);
                            }
                        }
                    }
                }
            }
            case KNIGHT -> {
                for (Field[] fields: gameScreen.felder) {
                    for (Field field: fields) {
                        if (field != null) {
                            
                            int rowDelta = Math.abs(field.x - x);
                            int colDelta = Math.abs(field.y - y);
    
                            if (((rowDelta == 1) && (colDelta == 2)) || ((rowDelta == 2) && (colDelta == 1))) {
                                field.setValidMove(true);
                            } else {
                                field.setValidMove(false);
                            }
                        }
                    }
                }
            }
            case PAWN -> {
                for (Field[] fields: gameScreen.felder) {
                    for (Field field: fields) {
                        if (field != null) {
                            int rowDelta = field.x - x;
                            int colDelta = field.y - y;

                            int rowDeltaAbs = Math.abs(field.x - x);
                            int colDeltaAbs = Math.abs(field.y - y);

                            switch (figure.direction) {
                                case UP -> {
                                    if (field.figure != null) {
                                        if (field.figure.direction != figure.direction) {
                                            if (rowDelta == -1 && colDeltaAbs == 1) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        }
                                    } else {
                                        if (figure.isFirstMove) {
                                            if ((rowDelta == -1 || rowDelta == -2) && colDeltaAbs == 0) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        } else {
                                            if (rowDelta == -1 && colDeltaAbs == 0) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        }
                                    }
                                }
                                case DOWN -> {
                                    if (field.figure != null) {
                                        if (field.figure.direction != figure.direction) {
                                            if (rowDelta == 1 && colDeltaAbs == 1) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        }
                                    } else {
                                        if (figure.isFirstMove) {
                                            if ((rowDelta == 1 || rowDelta == 2) && colDeltaAbs == 0) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        } else {
                                            if (rowDelta == 1 && colDeltaAbs == 0) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        }
                                    }
                                }
                                case LEFT -> {
                                    if (field.figure != null) {
                                        if (field.figure.direction != figure.direction) {
                                            if (colDelta == -1 && rowDeltaAbs == 1) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        }
                                    } else {
                                        if (figure.isFirstMove) {
                                            if ((colDelta == -1 || colDelta == -2) && rowDeltaAbs == 0) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        } else {
                                            if (colDelta == -1 && rowDeltaAbs == 0) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        }
                                    }
                                }
                                case RIGHT -> {
                                    if (field.figure != null) {
                                        if (field.figure.direction != figure.direction) {
                                            if (colDelta == 1 && rowDeltaAbs == 1) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        }
                                    } else {
                                        if (figure.isFirstMove) {
                                            if ((colDelta == 1 || colDelta == 2) && rowDeltaAbs == 0) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        } else {
                                            if (colDelta == 1 && rowDeltaAbs == 0) {
                                                field.setValidMove(true);
                                            } else {
                                                field.setValidMove(false);
                                            }
                                        }
                                    }
                                }
                            }
                            // pawn directions
                        }
                    }
                }
            }
            // cases
        }
    }
    
    public void setValidMove(boolean state) {
        isValidMove = state;
        if (state) {
            setBackground(Color.CYAN);
        } else {
            setBackground(isBlack ? new Color(100,100,100) : Color.WHITE);
        }
    }
}
