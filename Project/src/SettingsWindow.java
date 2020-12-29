import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow {

	JFrame window = new JFrame();
	Color selectedColor = Color.BLACK;

	JPanel playerSettings;
	JPanel graphicSettings;
	JPanel audioSettings;

	JPanel selectedTabPanel;
	
	UserInterfaceThread uiThread;

	public SettingsWindow(UserInterfaceThread uiThread) {
		this.uiThread = uiThread;

		JPanel tabs = new JPanel(new GridLayout(12, 0));

		// Initialize Tab buttons
		JButton playerTabButton = new JButton("Player");
		JButton graphicsTabButton = new JButton("Graphics");
		JButton audioTabButton = new JButton("Audio");
		JButton closeButton = new JButton("Close");

		// Initialize Tabs
		playerSettings = playerSettings();
		graphicSettings = graphicsSettings();
		audioSettings = audioSettings();

		playerTabButton.addActionListener(e -> displayTab(playerSettings));
		graphicsTabButton.addActionListener(e -> displayTab(graphicSettings));
		audioTabButton.addActionListener(e -> displayTab(audioSettings));
		closeButton.addActionListener(e -> closeWindow());

		tabs.add(playerTabButton);
		tabs.add(graphicsTabButton);
		tabs.add(audioTabButton);
		tabs.add(closeButton);

		tabs.setSize(300, window.getHeight());

		window.add(tabs, BorderLayout.WEST);

		playerSettings();

		window.setTitle("Settings");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setBounds(screenSize.width / 2 - 375, screenSize.height / 2 - 250, 750, 500);
		window.setVisible(true);
	}

	private void closeWindow() {
		window.setVisible(false);
		window.dispose();
	}

	private void displayTab(JPanel panel) {
		if (selectedTabPanel != null) {
			window.remove(selectedTabPanel);
		}
		selectedTabPanel = panel;
		window.add(panel, BorderLayout.CENTER);
		window.revalidate();
		window.repaint();
	}

	private JPanel playerSettings() {
		JPanel panel = new JPanel();

		JButton colorButton = new JButton("Choose preferred color");
		colorButton.addActionListener(e -> selectedColor = JColorChooser.showDialog(null, "Choose Preferred color", null));

		panel.add(colorButton);

		return panel;
	}

	private JPanel graphicsSettings() {
		JPanel panel = new JPanel();

		String[] resolutionStrings = { "3840x2160", "1920x1080", "1600x1050", "1280x800", "1600x900", "1360x768", "1280x1024", "1024x768", "1200x720", "420x360" };


		JComboBox resolutionList = new JComboBox(resolutionStrings);

		resolutionList.setSelectedIndex(0);

		resolutionList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (uiThread.client.config.getFullscreenMode() != Config.FullscreenMode.FULLSCREEN) {
					
					JComboBox cb = (JComboBox)e.getSource();
					String resolution = (String)cb.getSelectedItem();
					System.out.println(resolution);
					String[] size = resolution.split("x", 2);
					for (String s: size) {
						System.out.println(s);
					}
					uiThread.window.setSize(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
					uiThread.client.config.SaveConfig();
				}
			}
		});

		String[] skinStrings = {"Standard","Spaceships"};

		JComboBox skinList = new JComboBox(skinStrings);

		skinList.setSelectedIndex(0);
		
		skinList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox cb = (JComboBox)e.getSource();
				String resolution = (String)cb.getSelectedItem();
				//window.set();
				uiThread.client.config.SaveConfig();

			}
		});
		
		JComboBox fullscreenModes = new JComboBox( Config.FullscreenMode.values());
		
		fullscreenModes.setSelectedIndex(0);
		
		fullscreenModes.addActionListener(e -> {
			
			JComboBox cb = (JComboBox)e.getSource();
			uiThread.setApplicationFullscreenMode(Config.FullscreenMode.valueOf(cb.getSelectedIndex()));
			window.requestFocusInWindow();
			window.toFront();
			window.repaint();
			uiThread.client.config.SaveConfig();
			
		});
		
		panel.add(resolutionList);
		panel.add(skinList);
		panel.add(fullscreenModes);
		
		return panel;
	}

	private JPanel audioSettings() {
		JPanel panel = new JPanel();

		return panel;
	}
}
