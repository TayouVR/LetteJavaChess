import javax.swing.*;
import java.awt.*;

public class FieldCoordinates extends JButton {
    public int x;
    public int y;
    private Figure figure;
    
    public boolean isBlack;
    private boolean isValidMode;
    
    private GameScreen gameScreen;
    
    public FieldCoordinates(GameScreen screen, boolean isBlack) {
        this.isBlack = isBlack;
        setBackground(isBlack ? new Color(100,100,100) : Color.WHITE);
        gameScreen = screen;
        addActionListener(e -> {
            if (isValidMode && gameScreen.selectedFigure != null) {
                setFigure(gameScreen.selectedFigure.figure);
                gameScreen.selectedFigure.setFigure(null);
                for (FieldCoordinates[] fields: gameScreen.felder) {
                    for (FieldCoordinates field: fields) {
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
        gameScreen.selectedFigure = this;
        switch (figure.type) {
            case KING -> {
                for (FieldCoordinates[] fields: gameScreen.felder) {
                    for (FieldCoordinates field: fields) {
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
                for (FieldCoordinates[] fields: gameScreen.felder) {
                    for (FieldCoordinates field: fields) {
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
                for (FieldCoordinates[] fields: gameScreen.felder) {
                    for (FieldCoordinates field: fields) {
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
                for (FieldCoordinates[] fields: gameScreen.felder) {
                    for (FieldCoordinates field: fields) {
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
                for (FieldCoordinates[] fields: gameScreen.felder) {
                    for (FieldCoordinates field: fields) {
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
                for (FieldCoordinates[] fields: gameScreen.felder) {
                    for (FieldCoordinates field: fields) {
                        if (field != null) {
                
                            int rowDelta = field.x - x;
                            int colDelta = field.y - y;
                            
                            int rowDeltaAbs = Math.abs(field.x - x);
                            int colDeltaAbs = Math.abs(field.y - y);
    
                            switch (figure.direction) {
                                case UP -> {
                                    if (rowDelta == -1 && colDeltaAbs == 1) {
                                        field.setValidMove(true);
                                    } else {
                                        field.setValidMove(false);
                                    }
                                }
                                case DOWN -> {
                                    if (rowDelta == 1 && colDeltaAbs == 1) {
                                        field.setValidMove(true);
                                    } else {
                                        field.setValidMove(false);
                                    }
                                }
                                case LEFT -> {
                                    if (colDelta == -1 && rowDeltaAbs == 1) {
                                        field.setValidMove(true);
                                    } else {
                                        field.setValidMove(false);
                                    }
                                }
                                case RIGHT -> {
                                    if (colDelta == 1 && rowDeltaAbs == 1) {
                                        field.setValidMove(true);
                                    } else {
                                        field.setValidMove(false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setValidMove(boolean state) {
        isValidMode = state;
        if (state) {
            setBackground(Color.CYAN);
        } else {
            setBackground(isBlack ? new Color(100,100,100) : Color.WHITE);
        }
    }
}
