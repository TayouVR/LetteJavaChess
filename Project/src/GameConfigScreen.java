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
	
	public GameConfigScreen(UserInterfaceThread userInterfaceThread) {
		this.userInterfaceThread = userInterfaceThread;
		
		mainPanel.setBorder(new TitledBorder(new LineBorder(SystemColor.windowBorder), "Game Config"));
		
		// Player Count
		slider_playerCount.addChangeListener(e -> {
			JSlider slider = (JSlider) e.getSource();
			userInterfaceThread.client.localGame.properties.playerCount = slider.getValue();
			slider_playerCount.setValue(slider.getValue());
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
