import javax.swing.*;
import java.awt.*;

public class Field extends JButton {
    public int x;
    public int y;
    private Figure figure;
    
    public boolean isBlack;
    private Move isValidMove;
    
    private GameScreen gameScreen;
    
    public Field(GameScreen screen, boolean isBlack) {
        this.isBlack = isBlack;
        setBackground(isBlack ? new Color(100,100,100) : Color.WHITE);
        gameScreen = screen;
        addActionListener(e -> {
            if (isValidMove == Move.MOVE || isValidMove == Move.ATTACK && gameScreen.selectedField != null) {
                setFigure(gameScreen.selectedField.figure);
                gameScreen.selectedField.figure.isFirstMove = false;
                gameScreen.selectedField.setFigure(null);
                for (Field[] fields: gameScreen.felder) {
                    for (Field field: fields) {
                        if (field != null) {
                            field.setValidMove(Move.DEFAULT);
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
        figure.setMovableFields(gameScreen, this);
    }
    
    public void setValidMove(Move state) {
        isValidMove = state;
        if (state == Move.MOVE) {
            setBackground(isBlack ? new Color(25, 225, 225) : new Color(50, 255, 255));
        } else if (state == Move.ATTACK) {
            setBackground(new Color(255, isBlack ? 25 : 75, isBlack ? 25 : 75));
        } else {
            setBackground(isBlack ? new Color(100,100,100) : Color.WHITE);
        }
    }
    
    public enum Move {
        DEFAULT,
        MOVE,
        ATTACK
    }
}
