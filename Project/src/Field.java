import javax.swing.*;
import java.awt.*;

/**
 * Tile on playfield
 */
public class Field extends JButton {
    public Vector2int pos;
    private Figure figure;
    
    public boolean isBlack;
    
    public Move getIsValidMove() {
        return isValidMove;
    }
    
    private Move isValidMove;
    
    /**
     * Constructor
     * @param isBlack whether field is black or white
     */
    public Field(boolean isBlack) {
        this.isBlack = isBlack;
        setBackground(isBlack ? new Color(100,100,100) : Color.WHITE);
        addActionListener(e -> {
            if (isValidMove == Move.DEFAULT) {
                UserInterfaceThread.game.setAllFieldsDeselected();
            }
            if (isValidMove == Move.MOVE || isValidMove == Move.ATTACK && UserInterfaceThread.game.selectedField != null) {
                moveSelectedFigureHere();
            } else if (figure != null && UserInterfaceThread.game.userInterfaceThread.client.localGame.getCurrentPlayerTurn() == figure.associatedPlayerId) {
                select();
            }
        });
    }
    
    public void moveSelectedFigureHere() {
        setFigure(UserInterfaceThread.game.selectedField.figure);
        if (figure != null) {
            figure.field = this;
        }
        UserInterfaceThread.game.selectedField.figure.isFirstMove = false;
        UserInterfaceThread.game.selectedField.setFigure(null);
        for (Field[] fields: UserInterfaceThread.game.felder) {
            for (Field field: fields) {
                if (field != null) {
                    field.setValidMove(Move.DEFAULT);
                }
            }
        }
        UserInterfaceThread.game.setAllFieldsDeselected();
        UserInterfaceThread.game.userInterfaceThread.client.localGame.nextPlayerTurn();
        
    }
    
    /**
     * Places a figure on this field
     * @param figure figure to place
     */
    public void setFigure(Figure figure) {
        if (figure != null) {
            this.figure = figure;
            this.figure.field = this;
            setIcon(figure.getSingleImage(UserInterfaceThread.game.playfieldSize/14 - 5));
        } else {
            Client.instance.localGame.players[this.figure.associatedPlayerId-1].figures.remove(this.figure);
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
    public boolean select() {
        UserInterfaceThread.game.selectedField = this;
        setBackground(isBlack ? new Color(32, 32, 163) : new Color(79, 79, 255));
        return figure.setMovableFields();
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
