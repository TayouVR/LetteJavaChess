import javax.swing.*;

public class ChooseGameTypeScreen {
	
	UserInterfaceThread userInterfaceThread;
	
	// UI Elements
	public JPanel panel1;
	private JButton btn_localGame;
	private JButton btn_joinMultiplayerGame;
	private JButton btn_hostMultiplayerGame;
	private JButton btn_backToMainMenu;
	
	public ChooseGameTypeScreen(UserInterfaceThread userInterfaceThread) {
		this.userInterfaceThread = userInterfaceThread;
		
		btn_localGame.addActionListener(e -> {
			userInterfaceThread.client.startNewGame(true);
			userInterfaceThread.client.localGame.properties.playerCount = 4;
			userInterfaceThread.setPanel(userInterfaceThread.preGame.panel1);
		});
		//btn_joinMultiplayerGame.addActionListener(e -> new SettingsWindow(this));
		btn_hostMultiplayerGame.addActionListener(e -> {
			userInterfaceThread.client.startNewGame(true);
			userInterfaceThread.client.localGame.properties.playerCount = 4;
			userInterfaceThread.setPanel(userInterfaceThread.preGame.panel1);
		});
		btn_backToMainMenu.addActionListener(e -> userInterfaceThread.setPanel(userInterfaceThread.mainMenu.panel1));
	}
}
