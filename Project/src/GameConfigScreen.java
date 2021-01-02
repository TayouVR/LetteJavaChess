import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			if (check_moveTimeLimit.isSelected()) {
				userInterfaceThread.client.localGame.properties.perMoveTimer = (int)spinner_gameTimeLimit.getValue();
			} else {
				userInterfaceThread.client.localGame.properties.perMoveTimer = -1;
			}
		});
		check_gameTimeLimit.addActionListener(e -> {
			if (check_moveTimeLimit.isSelected()) {
				userInterfaceThread.client.localGame.properties.perMoveTimer = Integer.parseInt(textField_moveTimeLimit.getText());
			} else {
				userInterfaceThread.client.localGame.properties.perMoveTimer = -1;
			}
		});
		
		// Buttons
		backButton.addActionListener(e -> {
			userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1);
		});
		startButton.addActionListener(e -> {
			userInterfaceThread.client.localGame.placeFigures(userInterfaceThread.game);
			userInterfaceThread.setPanel(userInterfaceThread.game.panel1);
		});
		
		
	}
}
