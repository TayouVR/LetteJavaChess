import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UserInterfaceThread extends Thread {
	
	Client client;

	JFrame window = new JFrame();
	MainMenu mainMenu;
	ChooseGameTypeScreen chooseGameType;
	GameConfigScreen preGame;
	GameScreen game;
	
	public UserInterfaceThread(Client client) {
		this.client = client;
	}
	
	/**
	 * When Thread is started
	 */
	@Override
	public void run() {

		// set system look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		chooseGameType = new ChooseGameTypeScreen(this);
		preGame = new GameConfigScreen(this);
		mainMenu = new MainMenu(this);
		game = new GameScreen(this);
		
		
		setPanel(mainMenu.panel1);

		window.setTitle("Four Player Chess");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setSize(400, 300);
		setApplicationFullscreenMode(client.config.getFullscreenMode());
		window.setVisible(true);
	}
	
	public void setPanel(JPanel panel) {
		window.setContentPane(panel);
		window.revalidate();
		window.repaint();
	}
	
	public JPanel CreatePlayFieldForPlayers(int playercount) {
		JPanel panel = new JPanel();
		
		
		
		
		
		return panel;
	}
	
	public void setApplicationFullscreenMode (Config.FullscreenMode mode) {
		client.config.setFullscreenMode(mode);
		System.out.println("Changed Fullscreen mode to " + mode);
		
		window.dispose();
		switch (mode) {
			case FULLSCREEN -> {
				window.setUndecorated(true);
				window.pack();
				window.setExtendedState( window.getExtendedState()|JFrame.MAXIMIZED_BOTH );
			}
			case WINDOWED -> {
				window.setUndecorated(false);
				window.pack();
				window.setExtendedState( JFrame.NORMAL );
				Object[] res = client.config.getResolution();
				window.setSize((int)res[0], (int)res[1]);
			}
			case MAXIMIZED -> {
				window.setUndecorated(false);
				window.pack();
				window.setExtendedState( window.getExtendedState()|JFrame.MAXIMIZED_BOTH );
				Object[] res = client.config.getResolution();
				window.setSize((int)res[0], (int)res[1]);
			}
		}
		window.setVisible(true);
	}
}
