import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * player data class
 */
public class Player {
	
	public String name;
	public int colorIndex;
	public String skin;
	public boolean isAi;
	
	public JLabel playerNameLabel;
	
	/** Figures belonging to the Player */
	public ArrayList<Figure> figures = new ArrayList<>();
	
	/**
	 * WIP - makes the moves it thinks is the best given the situation
	 */
	public void doAutomaticMove() {
		Figure randomFigure = figures.get(new Random().nextInt(figures.size()));
		System.out.println("A automatic move will be attempted with a: " + randomFigure.type);
		if (randomFigure.setMovableFields()) {
			// logic here
			UserInterfaceThread.game.setAllFieldsDeselected();
		} else {
			UserInterfaceThread.game.setAllFieldsDeselected();
			doAutomaticMove();
		}
		
	}
}
