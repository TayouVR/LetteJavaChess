import javax.swing.*;
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
	
	public GameConfigScreen(UserInterfaceThread userInterfaceThread) {
		this.userInterfaceThread = userInterfaceThread;
		
		slider_playerCount.addChangeListener(e -> {
			JSlider slider = (JSlider) e.getSource();
			userInterfaceThread.client.localGame.properties.playerCount = slider.getValue();
			slider_playerCount.setValue(slider.getValue());
		});
		
		ActionListener moveTimeAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check_moveTimeLimit.isSelected()) {
					userInterfaceThread.client.localGame.properties.perMoveTimer = Integer.parseInt(textField_moveTimeLimit.getText());
				} else {
					userInterfaceThread.client.localGame.properties.perMoveTimer = -1;
				}
			}
		};
		
		textField_moveTimeLimit.addActionListener(moveTimeAL);
		
		check_moveTimeLimit.addActionListener(moveTimeAL);
		
		ActionListener gameTimeAL = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check_moveTimeLimit.isSelected()) {
					userInterfaceThread.client.localGame.properties.perMoveTimer = Integer.parseInt(textField_moveTimeLimit.getText());
				} else {
					userInterfaceThread.client.localGame.properties.perMoveTimer = -1;
				}
			}
		};
		
		textField_gameTimeLimit.addActionListener(gameTimeAL);
		
		check_gameTimeLimit.addActionListener(gameTimeAL);
		
		backButton.addActionListener(e -> {
			userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1);
		});
		
		startButton.addActionListener(e -> {
			userInterfaceThread.setPanel(userInterfaceThread.game.panel1);
		});
		
		
	}
}
