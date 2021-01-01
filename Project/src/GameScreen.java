import javax.swing.*;

public class GameScreen {
	
	UserInterfaceThread userInterfaceThread;
	
	// UI Elements
	public JPanel panel1;
	private JButton btn_leaveGame;
	private JButton btn_skipMove;
	
	public GameScreen(UserInterfaceThread userInterfaceThread) {
		this.userInterfaceThread = userInterfaceThread;
		
		btn_leaveGame.addActionListener(e -> userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1));
		
		
		
		/////////////////////////////////////////////////////////////////////////
		//TODO Jasko, mach hier in das panel1 die ganzen Spielfeld sachen rein //
		/////////////////////////////////////////////////////////////////////////
		
		
		
	}
}
