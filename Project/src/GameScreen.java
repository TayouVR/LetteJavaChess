import javax.swing.*;
import java.awt.*;

/**
 * Manage the Playfield and game actions
 */
public class GameScreen {

	UserInterfaceThread userInterfaceThread;

	// UI Elements
	public JPanel panel1;
	private JButton btn_leaveGame;
	private JButton btn_skipMove;

	//chessboard elements
	private JPanel gamePanel;
	public JLabel playerName1;
	public JLabel playerName3;
	public JLabel playerName2;
	public JLabel playerName4;
	private JPanel buttonPanel;
	public JLabel gameTimer;
	public JLabel turnTimer;
	
	
	public Field[][] felder = new Field[14][14];
	
	public Field selectedField;
	
	public int playfieldSize = 896; //default playfield size

	public GameScreen() {
		this.userInterfaceThread = Client.userInterfaceThread;

		btn_leaveGame.addActionListener(e -> {
			userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1);
		});
		
		btn_skipMove.addActionListener(e -> userInterfaceThread.client.localGame.nextPlayerTurn());

		//swing ui designer gui form
		gamePanel.setLayout(new GridLayout(14,14));
		for (int i = 0; i < 14; i++) {    // i rows , j columns
			for (int j = 0; j < 14; j++) {
				if (j > 2 && i < 3  && j < 11 ||
							 i > 2  && i < 11 ||
					j > 2 && i > 10 && j < 11) {
					if ((i + j) % 2 == 0) {
						felder[i][j] = new Field(this, false);
					} else {
						felder[i][j] = new Field(this, true);
					}
					
					felder[i][j].pos = new Vector2int(i, j);
					felder[i][j].setBorder(null);
					gamePanel.add(felder[i][j]);
				} else {
					gamePanel.add(new JLabel());
				}
			}
		}
		playfieldSize = UserInterfaceThread.window.getSize().height - buttonPanel.getHeight() - gameTimer.getFont().getSize() - 100;
		Dimension d = new Dimension(playfieldSize, playfieldSize);
		gamePanel.setPreferredSize(d);
		for (Field[] fields: felder) {
			for (Field field: fields) {
				if (field != null) {
					if (field.getFigure() != null) {
						field.setIcon(field.getFigure().getSingleImage(playfieldSize/14 - 5));
					}
				}
			}
		}
	}
	
	/**
	 * sets a figure to the target position on the field
	 * @param x target x
	 * @param y target y
	 * @param figure figure
	 */
	public Figure setFigureToPosition(int x, int y, Figure figure) {
		felder[x][y].setFigure(figure);
		figure.field = felder[x][y];
		return figure;
	}
	
	/**
	 * get figure from position
	 * @param pos position to get field from
	 * @return figure
	 */
	public Field getField(Vector2int pos) {
		return felder[pos.x][pos.y];
	}
	
	/**
	 * deselect all fields
	 */
	public void setAllFieldsDeselected() {
		for (Field[] fields: felder) {
			for (Field field: fields) {
				if (field != null) {
					field.setValidMove(Field.Move.DEFAULT);
				}
			}
		}
	}
}
