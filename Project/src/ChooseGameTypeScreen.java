import javax.swing.*;

/**
 * Main Window screen to choose the game type (local, host, join)
 */
public class ChooseGameTypeScreen {
	
	UserInterfaceThread userInterfaceThread;
	
	// UI Elements
	public JPanel panel1;
	private JButton btn_localGame;
	private JButton btn_joinMultiplayerGame;
	private JButton btn_hostMultiplayerGame;
	private JButton btn_backToMainMenu;
	
	public ChooseGameTypeScreen() {
		this.userInterfaceThread = Client.userInterfaceThread;
		
		btn_localGame.addActionListener(e -> {
			Client.instance.startNewGame(true);
			Client.instance.localGame.setPlayerCount(4);
			UserInterfaceThread.setPanel(UserInterfaceThread.preGame.panel1);
		});
		btn_joinMultiplayerGame.setEnabled(false);
		//btn_joinMultiplayerGame.addActionListener(e -> {});
		btn_hostMultiplayerGame.addActionListener(e -> {
			Client.instance.startNewGame(true);
			Client.instance.localGame.setPlayerCount(4);
			UserInterfaceThread.setPanel(UserInterfaceThread.preGame.panel1);
		});
		btn_backToMainMenu.addActionListener(e -> UserInterfaceThread.setPanel(UserInterfaceThread.mainMenu.panel1));
	}
}
