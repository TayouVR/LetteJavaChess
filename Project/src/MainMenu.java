import javax.swing.*;

/**
 * houses settings, exit and game type choose buttons
 */
public class MainMenu {
	
	// UI Elements
	public JPanel panel1;
	private JButton playButton;
	private JButton settingsButton;
	private JButton exitButton;
	
	public MainMenu() {
		
		playButton.addActionListener(e -> UserInterfaceThread.setPanel(UserInterfaceThread.chooseGameType.panel1));
		settingsButton.addActionListener(e -> new SettingsWindow());
		exitButton.addActionListener(e -> System.exit(0));
	}
}
