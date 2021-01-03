import javax.swing.*;

public class FieldCoordinates extends JButton {
    public int x;
    public int y;
    private Figure figure;
    
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
}
