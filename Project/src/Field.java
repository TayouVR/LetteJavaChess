import javax.swing.*;
import java.awt.*;

/**
 * Tile on playfield
 */
public class Field extends JButton {
    public Vector2int pos;
    private Figure figure;
    
    public boolean isBlack;
    private Move isValidMove;
    
    private GameScreen gameScreen;
    
    /**
     * Constructor
     * @param screen GameScreen which has the whole field
     * @param isBlack wether field is black or white
     */
    public Field(GameScreen screen, boolean isBlack) {
        this.isBlack = isBlack;
        setBackground(isBlack ? new Color(100,100,100) : Color.WHITE);
        gameScreen = screen;
        addActionListener(e -> {
            if (isValidMove == Move.DEFAULT) {
                screen.setAllFieldsDeselected();
            }
            if (isValidMove == Move.MOVE || isValidMove == Move.ATTACK && gameScreen.selectedField != null) {
                setFigure(gameScreen.selectedField.figure);
                figure.field = this;
                gameScreen.selectedField.figure.isFirstMove = false;
                gameScreen.selectedField.setFigure(null);
                for (Field[] fields: gameScreen.felder) {
                    for (Field field: fields) {
                        if (field != null) {
                            field.setValidMove(Move.DEFAULT);
                        }
                    }
                }
                screen.setAllFieldsDeselected();
                screen.userInterfaceThread.client.localGame.nextPlayerTurn();
            } else if (figure != null && screen.userInterfaceThread.client.localGame.getCurrentPlayerTurn() == figure.associatedPlayerId) {
                select();
            }
        });
    }
    
    /**
     * Places a figure on this field
     * @param figure figure to place
     */
    public void setFigure(Figure figure) {
        if (figure != null) {
            this.figure = figure;
            setIcon(figure.getSingleImage(gameScreen.playfieldSize/14 - 5));
        } else {
            this.figure = null;
            setIcon(null);
        }
    }
    
    /**
     * Gets the figure currently on the field
     * @return figure
     */
    public Figure getFigure() {
        return figure;
    }
    
    /**
     * Selects field
     */
    public void select() {
        gameScreen.selectedField = this;
        setBackground(isBlack ? new Color(32, 32, 163) : new Color(79, 79, 255));
        figure.setMovableFields();
    }
    
    /**
     * Set if this field is a valid move for the current figure.
     * Sets colors accordingly
     * @param state
     */
    public void setValidMove(Move state) {
        isValidMove = state;
        if (state == Move.MOVE) {
            setBackground(isBlack ? new Color(0, 203, 203) : new Color(0, 255, 255));
        } else if (state == Move.ATTACK) {
            setBackground(isBlack ? new Color(203, 0, 0) : new Color(255, 0, 0));
        } else {
            setBackground(isBlack ? new Color(100,100,100) : Color.WHITE);
        }
    }
    
    /**
     * All movable options
     */
    public enum Move {
        DEFAULT,
        MOVE,
        ATTACK
    }
}
