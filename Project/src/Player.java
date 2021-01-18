import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
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
		Figure randomFigure = figures.get(new Random().nextInt(figures.size()-1));
		System.out.println("A automatic move will be attempted with a: " + randomFigure.type);
		if (randomFigure.field.select()) {
			ArrayList<Field> applicableFields = new ArrayList<>();
			for (Field[] fields: UserInterfaceThread.game.felder) {
				for (Field field: fields) {
					if (field != null) {
						if (field.getIsValidMove() == Field.Move.MOVE || field.getIsValidMove() == Field.Move.ATTACK) {
							applicableFields.add(field);
						}
					}
				}
			}
			applicableFields.get(new Random().nextInt(applicableFields.size())).moveSelectedFigureHere();
			UserInterfaceThread.game.setAllFieldsDeselected();
			System.out.println("Successfully performed AI move");
		} else {
			UserInterfaceThread.game.setAllFieldsDeselected();
			doAutomaticMove();
		}
		
	}
}
