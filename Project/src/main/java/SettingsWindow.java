import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    private void panels(){

    JPanel graphicpanel = new JPanel();

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
                window.set();

            }
        });

    JPanel playerpanel = new JPanel();

    JPanel AudioPanel = new JPanel();

    }

}
