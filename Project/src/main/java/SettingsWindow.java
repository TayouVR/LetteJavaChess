import javax.swing.*;
import java.awt.*;

public class SettingsWindow {

    JFrame window = new JFrame();
    Color selectedColor = Color.BLACK;

    public SettingsWindow() {

        JPanel tabs = new JPanel(new GridLayout(6, 0));

        JButton playerTabButton = new JButton("Player");
        JButton graphicsTabButton = new JButton("Graphics");
        JButton audioTabButton = new JButton("Audio");
        JButton closeButton = new JButton("Close");

        //playerTabButton.addActionListener(e -> );
        //graphicsTabButton.addActionListener(e -> );
        //audioTabButton.addActionListener(e -> );
        closeButton.addActionListener(e -> closeWindow());

        tabs.add(playerTabButton);
        tabs.add(graphicsTabButton);
        tabs.add(audioTabButton);
        tabs.add(closeButton);

        window.add(tabs, BorderLayout.WEST);

        JButton colorButton = new JButton("Choose");
        colorButton.addActionListener(e -> selectedColor = JColorChooser.showDialog(null, "Choose Preferred color", null));

        window.add(colorButton);

        window.setTitle("Settings");
        window.setSize(300, 500);
        window.setVisible(true);
    }

    private void closeWindow() {
        window.setVisible(false);
        window.dispose();
    }
}
