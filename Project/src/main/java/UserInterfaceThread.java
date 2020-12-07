import javax.swing.*;
import java.awt.*;

public class UserInterfaceThread extends Thread {

    JFrame window = new JFrame();
    JPanel mainMenu;
    JPanel preGame;
    JPanel game;

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

        mainMenu = constructMainMenu();
        window.add(mainMenu);

        window.setTitle("Four Player Chess");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(400, 300);
        window.setExtendedState( window.getExtendedState()|JFrame.MAXIMIZED_BOTH );
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

        //playButton.addActionListener(e -> );
        settingsButton.addActionListener(e -> new SettingsWindow());
        exitButton.addActionListener(e -> System.exit(0));

        mainMenu.add(playButton);
        mainMenu.add(settingsButton);
        mainMenu.add(exitButton);
        mainMenu.add(Box.createVerticalGlue());

        JPanel panel=new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
        panel.setLayout(boxLayout);
        panel.add(Box.createHorizontalGlue());
        panel.add(mainMenu);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }
}
