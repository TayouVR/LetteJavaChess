import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UserInterfaceThread extends Thread {
	
	Client client;

	JFrame window = new JFrame();
	JPanel mainMenu;
	JPanel chooseGameType;
	JPanel preGame;
	JPanel game;
	
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
		chooseGameType = CreateChooseGameTypeScreen();
		preGame = CreateGameConfigScreen();

		mainMenu = constructMainMenu();
		window.add(mainMenu);

		window.setTitle("Four Player Chess");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setSize(400, 300);
		setApplicationFullscreenMode(client.config.getFullscreenMode());
		window.setVisible(true);
	}

	/**
	 * create main menu panel and contents
	 * @return main menu JPanel
	 */
	private JPanel constructMainMenu() {
		JPanel mainMenu = new JPanel();
		BoxLayout boxLayout1 = new BoxLayout(mainMenu, BoxLayout.Y_AXIS);
		mainMenu.setLayout(boxLayout1);
		mainMenu.add(Box.createVerticalGlue());

		// create menu buttons
		JButton playButton = new JButton("Play");
		JButton settingsButton = new JButton("Settings");
		JButton exitButton = new JButton("Exit");

		Dimension menuButtonSize = new Dimension(100, 50);

		mainMenu.setPreferredSize(menuButtonSize);
		playButton.setPreferredSize(menuButtonSize);
		settingsButton.setPreferredSize(menuButtonSize);
		exitButton.setPreferredSize(menuButtonSize);

		playButton.addActionListener(e -> {
			window.setContentPane(chooseGameType);
			window.revalidate();
			window.repaint();
		});
		settingsButton.addActionListener(e -> new SettingsWindow(this));
		exitButton.addActionListener(e -> System.exit(0));

		mainMenu.add(playButton);
		mainMenu.add(settingsButton);
		mainMenu.add(exitButton);
		mainMenu.add(Box.createVerticalGlue());

		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
		panel.setLayout(boxLayout);
		panel.add(Box.createHorizontalGlue());
		panel.add(mainMenu);
		panel.add(Box.createHorizontalGlue());

		return panel;
	}
	
	public JPanel CreateChooseGameTypeScreen() {
		JPanel panel = new JPanel();
		BoxLayout boxLayout1 = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout1);
		panel.add(Box.createVerticalGlue());
		
		JButton btn_localGame = new JButton("Start a local Game");
		JButton btn_joinMultiplayerGame = new JButton("Join a multiplayer Game");
		JButton btn_hostMultiplayerGame = new JButton("Host a multiplayer Game");
		JButton btn_backToMainMenu = new JButton("Back");
		
		Dimension menuButtonSize = new Dimension(200, 50);
		
		panel.setPreferredSize(menuButtonSize);
		btn_localGame.setPreferredSize(menuButtonSize);
		btn_joinMultiplayerGame.setPreferredSize(menuButtonSize);
		btn_hostMultiplayerGame.setPreferredSize(menuButtonSize);
		btn_backToMainMenu.setPreferredSize(menuButtonSize);
		
		btn_localGame.addActionListener(e -> {
			client.startNewGame(true);
			window.setContentPane(preGame);
			window.revalidate();
			window.repaint();
		});
		//btn_joinMultiplayerGame.addActionListener(e -> new SettingsWindow(this));
		//btn_hostMultiplayerGame.addActionListener(e -> System.exit(0));
		btn_backToMainMenu.addActionListener(e -> {
			window.setContentPane(mainMenu);
			window.revalidate();
			window.repaint();
		});
		
		panel.add(btn_localGame);
		panel.add(btn_joinMultiplayerGame);
		panel.add(btn_hostMultiplayerGame);
		panel.add(btn_backToMainMenu);
		panel.add(Box.createVerticalGlue());
		
		JPanel panel2 = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel2, BoxLayout.LINE_AXIS);
		panel2.setLayout(boxLayout);
		panel2.add(Box.createHorizontalGlue());
		panel2.add(panel);
		panel2.add(Box.createHorizontalGlue());
		
		return panel2;
	}
	
	public JPanel CreateGameConfigScreen() {
		JPanel panel = new JPanel();
		
		JSlider playerCount = new JSlider();
		
		playerCount.setMinimum(1);
		playerCount.setMaximum(4);
		playerCount.setMinorTickSpacing(1);
		playerCount.setMajorTickSpacing(1);
		playerCount.createStandardLabels(1);
		playerCount.setPaintTicks(true);
		playerCount.setPaintLabels(true);
		playerCount.setSnapToTicks(true);
		
		playerCount.addChangeListener(e -> {
			JSlider slider = (JSlider) e.getSource();
			client.localGame.properties.playerCount = slider.getValue();
			playerCount.setValue(slider.getValue());
		});
		
		
		panel.add(playerCount);
		
		return panel;
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
	
	public enum FieldType {
		REGULAR(0),
		THREE_PLAYERS(1),
		FOUR_PLAYERS(2);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private FieldType(int value) {
			this.value = value;
		}
		
		static {
			for (FieldType fieldType : FieldType.values()) {
				map.put(fieldType.value, fieldType);
			}
		}
		
		public static FieldType valueOf(int fieldType) {
			return (FieldType) map.get(fieldType);
		}
		
		public int getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			String outName = "";
			for (String s: name().split("_")) {
				outName += name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase(Locale.ROOT);
			}
			return outName;
		}
	}
}
