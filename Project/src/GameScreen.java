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

	private FieldCoordinates[][] felder = new FieldCoordinates[14][14];


	//Chessboard Elements


	public GameScreen(UserInterfaceThread userInterfaceThread) {
		this.userInterfaceThread = userInterfaceThread;

		btn_leaveGame.addActionListener(e -> userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1));

		//swing ui designer gui form
		gamePanel.setLayout(new GridLayout(14,14));
		for (int i = 0; i < 14; i++) {    // i rows , j columns
			for (int j = 0; j < 14; j++) {
				if (j > 2 && i < 3 && j < 11 ||
							 i > 2 && i < 11 ||
							 j > 2 && i > 10 && j < 11) {
					felder[i][j] = new FieldCoordinates();
					felder[i][j].x = i;
					felder[i][j].y = j;
					felder[i][j].setBorder(null);
					if ((i + j) % 2 == 0) {
						felder[i][j].setBackground(Color.white);


					} else {
						felder[i][j].setBackground(Color.black);
					}
					gamePanel.add(felder[i][j]);


				}
				else{
					gamePanel.add(new JLabel());
				}

				/////////////////////////////////////////////////////////////////////////
				//TODO Jasko, mach hier in das panel1 die ganzen Spielfeld sachen rein //
				/////////////////////////////////////////////////////////////////////////


			}
		}
	}
}
