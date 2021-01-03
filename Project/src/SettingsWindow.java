import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow {

	JFrame window = new JFrame();
	Color selectedColor = Color.BLACK;

	UserInterfaceThread uiThread;
	
	// UI Elements
	private JTabbedPane tabbedPane1;
	private JPanel panel1;
	private JButton closeButton;
	private JButton btn_preferredColor;
	private JLabel label_preferredColor;
	private JComboBox comboBox_resolutions;
	private JComboBox comboBox_fullscreenMode;
	private JComboBox comboBox_skins;
	private JLabel label_resolutions;
	private JLabel label_skins;
	private JLabel label_fullscreenMode;
	private JSlider slider1;
	private JComboBox comboBox_audioDevice;
	
	public SettingsWindow(UserInterfaceThread uiThread) {
		this.uiThread = uiThread;
		
		btn_preferredColor.addActionListener(e -> selectedColor = JColorChooser.showDialog(null, "Choose Preferred color", null));
		closeButton.addActionListener(e -> closeWindow());
		
		graphicsSettings();
		
		window.setContentPane(panel1);

		window.setTitle("Settings");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setBounds(screenSize.width / 2 - 375, screenSize.height / 2 - 250, 750, 500);
		window.setVisible(true);
	}

	private void closeWindow() {
		window.setVisible(false);
		window.dispose();
	}

	private void graphicsSettings() {
		String[] resolutionStrings = {"3840x2160", "1920x1080", "1600x1050", "1280x800", "1600x900", "1360x768", "1280x1024", "1024x768", "1200x720", "420x360"};
		for (String resolution: resolutionStrings) {
			comboBox_resolutions.addItem(resolution);
		}
		
		comboBox_resolutions.setSelectedIndex(0);
		
		comboBox_resolutions.addActionListener(e -> {
			if (uiThread.client.config.getFullscreenMode() != FullscreenMode.FULLSCREEN) {
				JComboBox cb = (JComboBox) e.getSource();
				String resolution = (String) cb.getSelectedItem();
				System.out.println(resolution);
				String[] size = resolution.split("x", 2);
				for (String s : size) {
					System.out.println(s);
				}
				uiThread.window.setSize(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
				uiThread.client.config.setResolution(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
				uiThread.client.config.SaveConfig();
			}
		});

		String[] skinStrings = {"Standard", "Spaceships"};
		for (String skin: skinStrings) {
			comboBox_skins.addItem(skin);
		}
		
		comboBox_skins.setSelectedIndex(0);
		
		comboBox_skins.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			String resolution = (String) cb.getSelectedItem();
			//window.set();
			uiThread.client.config.SaveConfig();
		});
		
		
		for (FullscreenMode option: FullscreenMode.values()) {
			comboBox_fullscreenMode.addItem(option);
		}
		
		comboBox_fullscreenMode.setSelectedIndex(0);
		
		comboBox_fullscreenMode.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			uiThread.setApplicationFullscreenMode(FullscreenMode.valueOf(cb.getSelectedIndex()));
			window.requestFocusInWindow();
			window.toFront();
			window.repaint();
			uiThread.client.config.SaveConfig();
		});
	}

	private void audioSettings() {

		//TODO Shyguy hier diesen Slider und Dropdown via .forms datei machen (action listener bleibt hier)
		
		comboBox_audioDevice.setSelectedIndex(0);
		
		comboBox_audioDevice.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			String audio = (String) cb.getSelectedItem();
			System.out.println(audio);
		});
	}
}

