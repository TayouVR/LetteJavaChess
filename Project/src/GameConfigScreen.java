import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;


public class GameConfigScreen {
	
	UserInterfaceThread userInterfaceThread;
	
	// UI stuff
	public JPanel panel1;
	private JButton backButton;
	private JButton startButton;
	private JTextField textField_moveTimeLimit;
	private JTextField textField_gameTimeLimit;
	private JCheckBox check_gameTimeLimit;
	private JCheckBox check_moveTimeLimit;
	private JSlider slider_playerCount;
	private JSpinner spinner_moveTimeLimit;
	private JSpinner spinner_gameTimeLimit;
	private JPanel mainPanel;
	private JTextField PlayerName1;
	private JTextField PlayerName2;
	private JTextField PlayerName3;
	private JTextField PlayerName4;

	public GameConfigScreen(UserInterfaceThread userInterfaceThread) {
		this.userInterfaceThread = userInterfaceThread;
		
		mainPanel.setBorder(new TitledBorder(new LineBorder(SystemColor.windowBorder), "Game Config"));

		// Player Count
		slider_playerCount.addChangeListener(e -> {
			JSlider slider = (JSlider) e.getSource();
			userInterfaceThread.client.localGame.properties.playerCount = slider.getValue();
			slider_playerCount.setValue(slider.getValue());
			if(slider.getValue() == 1){

				PlayerName1.setVisible(true);
				PlayerName2.setVisible(false);
				PlayerName3.setVisible(false);
				PlayerName4.setVisible(false);

			}
			else if (slider.getValue() == 2){

				PlayerName1.setVisible(true);
				PlayerName2.setVisible(true);
				PlayerName3.setVisible(false);
				PlayerName4.setVisible(false);

			}
			else if(slider.getValue() == 3){

				PlayerName1.setVisible(true);
				PlayerName2.setVisible(true);
				PlayerName3.setVisible(true);
				PlayerName4.setVisible(false);

			}
			else if(slider.getValue() == 4){

				PlayerName1.setVisible(true);
				PlayerName2.setVisible(true);
				PlayerName3.setVisible(true);
				PlayerName4.setVisible(true);

			}
			else{

				PlayerName1.setVisible(false);
				PlayerName2.setVisible(false);
				PlayerName3.setVisible(false);
				PlayerName4.setVisible(false);

			}

			/*switch(userInterfaceThread.client.localGame.properties.playerCount){
				case 0:
					if(slider.getValue() == 1){

						PlayerName1.setVisible(true);

					}
					break;
				case 1:
					if(slider.getValue() == 2){

						PlayerName2.setVisible(true);

					}
					break;
				case 2:
					if(slider.getValue() == 3){

						PlayerName3.setVisible(true);

					}
					break;
				case 3:
					if(slider.getValue() == 4){

						PlayerName4.setVisible(true);

					}
					break;
				default:
					System.out.println("Zu wenig/viele spieler");
					break;
			}*/
		});
		
		// Move Time
		spinner_moveTimeLimit.addChangeListener(e -> {
			if (check_moveTimeLimit.isSelected()) {
				userInterfaceThread.client.localGame.properties.perMoveTimer = (int)spinner_moveTimeLimit.getValue();
			} else {
				userInterfaceThread.client.localGame.properties.perMoveTimer = -1;
			}
		});
		check_moveTimeLimit.addActionListener(e -> {
			if (check_moveTimeLimit.isSelected()) {
				userInterfaceThread.client.localGame.properties.perMoveTimer = (int)spinner_moveTimeLimit.getValue();
			} else {
				userInterfaceThread.client.localGame.properties.perMoveTimer = -1;
			}
		});
		
		// Game Time
		spinner_gameTimeLimit.addChangeListener(e -> {
			if (check_gameTimeLimit.isSelected()) {
				userInterfaceThread.client.localGame.properties.fullGameTimer = (int)spinner_gameTimeLimit.getValue();
			} else {
				userInterfaceThread.client.localGame.properties.fullGameTimer = -1;
			}
		});
		check_gameTimeLimit.addActionListener(e -> {
			if (check_gameTimeLimit.isSelected()) {
				userInterfaceThread.client.localGame.properties.fullGameTimer = (int)spinner_gameTimeLimit.getValue();
			} else {
				userInterfaceThread.client.localGame.properties.fullGameTimer = -1;
			}
		});
		
		// Buttons
		backButton.addActionListener(e -> {
			userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1);
		});
		startButton.addActionListener(e -> {
			userInterfaceThread.client.localGame.placeFigures(userInterfaceThread.game);
			userInterfaceThread.setPanel(userInterfaceThread.game.panel1);
			userInterfaceThread.game.Timer.setText("Time left:" + Integer.toString(userInterfaceThread.client.localGame.properties.fullGameTimer));

			java.util.Timer countdown = new Timer();
			countdown.scheduleAtFixedRate(new TimerTask() {
				int sekunden = userInterfaceThread.client.localGame.properties.fullGameTimer;
				@Override
				public void run() {
					sekunden--;
					userInterfaceThread.game.Timer.setText("Time left:" + Integer.toString(sekunden));
					if(sekunden ==0){
						countdown.cancel();
					}
				}
			},1000,1000);


		});
		
		
	}
}
