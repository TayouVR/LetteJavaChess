import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Manages all UI
 */
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
		
		// Create Panels for all Pages
		chooseGameType = new ChooseGameTypeScreen(this);
		preGame = new GameConfigScreen(this);
		mainMenu = new MainMenu(this);
		game = new GameScreen(this);
		
		// Set Main menu panel
		setPanel(mainMenu.panel1);
		
		window.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				client.config.setResolution(window.getWidth(), window.getHeight());
				client.config.saveConfig();
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			
			}
			
			@Override
			public void componentShown(ComponentEvent e) {
			
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			
			}
		});
		
		// Set Window properties
		window.setTitle("Four Player Chess");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setApplicationFullscreenMode(client.config.getFullscreenMode());
		window.setVisible(true);
	}
	
	/**
	 * Set Panel as Content Pane
	 * @param panel panel to set
	 */
	public void setPanel(JPanel panel) {
		window.setContentPane(panel);
		window.revalidate();
		window.repaint();
	}
	
	/**
	 * Set Fullscreen mode
	 * @param mode
	 */
	public void setApplicationFullscreenMode (FullscreenMode mode) {
		client.config.setFullscreenMode(mode);
		System.out.println("Changed Fullscreen mode to " + mode);
		
		window.dispose();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		switch (mode) {
			case FULLSCREEN -> {
				window.setUndecorated(true);
				window.pack();
				window.setExtendedState( window.getExtendedState()|JFrame.MAXIMIZED_BOTH );
				window.setBounds(0, 0, screenSize.width, screenSize.height);
			}
			case WINDOWED -> {
				window.setUndecorated(false);
				window.pack();
				window.setExtendedState( JFrame.NORMAL );
				Object[] res = client.config.getResolution();
				window.setBounds(screenSize.width / 2 - ((int)res[0]/2), screenSize.height / 2 - ((int)res[1]/2), (int)res[0], (int)res[1]);
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
