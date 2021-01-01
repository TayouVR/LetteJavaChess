import javax.swing.*;
import java.awt.*;

public class MainMenu {
	
	UserInterfaceThread userInterfaceThread;
	
	// UI Elements
	public JPanel panel1;
	private JButton playButton;
	private JButton settingsButton;
	private JButton exitButton;
	
	public MainMenu(UserInterfaceThread userInterfaceThread) {
		this.userInterfaceThread = userInterfaceThread;
		
		playButton.addActionListener(e -> userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1));
		settingsButton.addActionListener(e -> new SettingsWindow(userInterfaceThread));
		exitButton.addActionListener(e -> System.exit(0));
	}
}
