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

	public SettingsWindow() {

		JPanel tabs = new JPanel(new GridLayout(6, 0));

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

		window.add(tabs, BorderLayout.WEST);

		playerSettings();

		window.setTitle("Settings");
		window.setSize(300, 500);
		window.setVisible(true);
	}

	private void closeWindow() {
		window.setVisible(false);
		window.dispose();
	}

	private void displayTab(JPanel panel) {
		try {
			window.remove(selectedTabPanel);
		} catch (Exception e) {
			e.printStackTrace();
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

		String[] resolutionStrings = { "3840x2160", "1360x768", "1280x800", "Rabbit", "Pig" };


		JComboBox resolutionList = new JComboBox(resolutionStrings);

		resolutionList.setSelectedIndex(0);

		resolutionList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox cb = (JComboBox)e.getSource();
				String resolution = (String)cb.getSelectedItem();
				String[] size = resolution.split("x");
				window.setSize(Integer.getInteger (size[0]), Integer.getInteger(size[1]));

			}
		});

		String[] skinStrings = {"Standard","Spaceships"};

		JComboBox skinList = new JComboBox(skinStrings);

		skinList.setSelectedIndex(0);

		resolutionList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox cb = (JComboBox)e.getSource();
				String resolution = (String)cb.getSelectedItem();
				//window.set();

			}
		});
		return panel;
	}

	private JPanel audioSettings() {
		JPanel panel = new JPanel();

		return panel;
	}
}
