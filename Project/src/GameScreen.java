import javax.swing.*;
import java.awt.*;

public class GameScreen {

	UserInterfaceThread userInterfaceThread;

	// UI Elements
	public JPanel panel1;
	private JButton btn_leaveGame;
	private JButton btn_skipMove;

	//chessboard elements
	private JPanel gamePanel;
	private JLabel playerName1;
	private JLabel playerName3;
	private JLabel playerName2;
	private JLabel playerName4;
	private JPanel buttonPanel;
	public JLabel Timer;


	public Field[][] felder = new Field[14][14];
	
	public Field selectedField;
	
	public int playfieldSize = 896;

	public GameScreen(UserInterfaceThread userInterfaceThread) {
		this.userInterfaceThread = userInterfaceThread;

		btn_leaveGame.addActionListener(e -> {
			userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1);
		});

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
					
					felder[i][j].x = i;
					felder[i][j].y = j;
					felder[i][j].setBorder(null);
					gamePanel.add(felder[i][j]);
				} else {
					gamePanel.add(new JLabel());
				}
			}
		}
		playfieldSize = userInterfaceThread.window.getSize().height - buttonPanel.getSize().height;
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
	
	public void setFigureToPosition(int x, int y, Figure figure) {
		felder[x][y].setFigure(figure);
	}
	
	public Field getFigureFromPosition(int x, int y) {
		return felder[x][y];
	}
}
