import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;


public class GameConfigScreen {
	
	UserInterfaceThread userInterfaceThread;
	
	// UI stuff
	public JPanel panel1;
	private JButton backButton;
	private JButton startButton;
	private JSlider slider_playerCount;
	private JSpinner spinner_moveTimeLimit;
	private JSpinner spinner_gameTimeLimit;
	private JPanel mainPanel;
	private JTextField textField_player0Name;
	private JTextField textField_player1Name;
	private JTextField textField_player2Name;
	private JTextField textField_player3Name;
	private JComboBox comboBox_player0color;
	private JComboBox comboBox_player1color;
	private JComboBox comboBox_player2color;
	private JComboBox comboBox_player3color;
	private JLabel label_player1;
	private JLabel label_player2;
	private JLabel label_player3;
	private JLabel label_player4;
	
	public GameConfigScreen(UserInterfaceThread userInterfaceThread) {
		this.userInterfaceThread = userInterfaceThread;
		
		setUIStyles();

		// Player Count
		slider_playerCount.addChangeListener(e -> {
			JSlider slider = (JSlider) e.getSource();
			int sliderValue = slider.getValue();
			userInterfaceThread.client.localGame.properties.playerCount = sliderValue;
			slider_playerCount.setValue(sliderValue);
			
			textField_player0Name.setVisible(sliderValue >= 1);
			textField_player1Name.setVisible(sliderValue >= 2);
			textField_player2Name.setVisible(sliderValue >= 3);
			textField_player3Name.setVisible(sliderValue >= 4);
			
			comboBox_player0color.setVisible(sliderValue >= 1);
			comboBox_player1color.setVisible(sliderValue >= 2);
			comboBox_player2color.setVisible(sliderValue >= 3);
			comboBox_player3color.setVisible(sliderValue >= 4);
			
			label_player1.setVisible(sliderValue >= 1);
			label_player2.setVisible(sliderValue >= 2);
			label_player3.setVisible(sliderValue >= 3);
			label_player4.setVisible(sliderValue >= 4);
			
		});
		
		String[] availableColorOptions = {"White", "Black", "Dark Grey", "Light Grey", "Pink", "Red", "Orange", "Yellow", "Green", "Blue", "Cyan", "Purple"};
		
		for (String option: availableColorOptions) {
			comboBox_player0color.addItem(option);
			comboBox_player1color.addItem(option);
			comboBox_player2color.addItem(option);
			comboBox_player3color.addItem(option);
		}
		comboBox_player0color.setSelectedIndex(0);
		comboBox_player1color.setSelectedIndex(1);
		comboBox_player2color.setSelectedIndex(10);
		comboBox_player3color.setSelectedIndex(11);
		/*
		ActionListener colorActionListener = e -> {
			comboBox_player0color.getItemAt(0);
			comboBox_player1color.addActionListener(colorActionListener);
			comboBox_player2color.addActionListener(colorActionListener);
			comboBox_player3color.addActionListener(colorActionListener);
		};
		
		comboBox_player0color.addActionListener(colorActionListener);
		comboBox_player1color.addActionListener(colorActionListener);
		comboBox_player2color.addActionListener(colorActionListener);
		comboBox_player3color.addActionListener(colorActionListener);*/
		
		
		// Move Time
		spinner_moveTimeLimit.addChangeListener(e -> {
			userInterfaceThread.client.localGame.properties.perMoveTimer = (int)spinner_moveTimeLimit.getValue();
		});
		
		// Game Time
		spinner_gameTimeLimit.addChangeListener(e -> {
			userInterfaceThread.client.localGame.properties.fullGameTimer = (int)spinner_gameTimeLimit.getValue();
		});
		
		// Buttons
		backButton.addActionListener(e -> {
			userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1);
		});
		startButton.addActionListener(e -> {
			userInterfaceThread.client.localGame.placeFigures(userInterfaceThread.game,
				comboBox_player0color.getSelectedIndex(),
				comboBox_player1color.getSelectedIndex(),
				comboBox_player2color.getSelectedIndex(),
				comboBox_player3color.getSelectedIndex());
			userInterfaceThread.setPanel(userInterfaceThread.game.panel1);
			
			int playerCount = userInterfaceThread.client.localGame.properties.playerCount;
			userInterfaceThread.game.playerName1.setVisible(playerCount >= 1);
			userInterfaceThread.game.playerName2.setVisible(playerCount >= 2);
			userInterfaceThread.game.playerName3.setVisible(playerCount >= 3);
			userInterfaceThread.game.playerName4.setVisible(playerCount >= 4);
			
			userInterfaceThread.game.playerName1.setText(!textField_player0Name.getText().equals("") ? textField_player0Name.getText() : "Unnamed 1");
			userInterfaceThread.game.playerName2.setText(!textField_player1Name.getText().equals("") ? textField_player1Name.getText() : "Unnamed 2");
			userInterfaceThread.game.playerName3.setText(!textField_player2Name.getText().equals("") ? textField_player2Name.getText() : "Unnamed 3");
			userInterfaceThread.game.playerName4.setText(!textField_player3Name.getText().equals("") ? textField_player3Name.getText() : "Unnamed 4");
			
			userInterfaceThread.game.Timer.setText("Time left:" + Integer.toString(userInterfaceThread.client.localGame.properties.fullGameTimer));

			java.util.Timer countdown = new Timer();
			countdown.scheduleAtFixedRate(new TimerTask() {
				int sekunden = userInterfaceThread.client.localGame.properties.fullGameTimer;
				@Override
				public void run() {
					sekunden--;
					userInterfaceThread.game.Timer.setText("Time left:" + Integer.toString(sekunden));
					if(sekunden ==0){
						countdown.cancel();
					}
				}
			},1000,1000);


		});
		
		
	}
	
	private void setUIStyles() {
		spinner_moveTimeLimit.setBorder(new LineBorder(spinner_moveTimeLimit.getBackground().darker()));
		spinner_gameTimeLimit.setBorder(new LineBorder(spinner_moveTimeLimit.getBackground().darker()));
		mainPanel.setBorder(new TitledBorder(new LineBorder(SystemColor.windowBorder), "Game Config"));
		textField_player0Name.setBorder(new LineBorder(spinner_moveTimeLimit.getBackground().darker()));
		textField_player1Name.setBorder(new LineBorder(spinner_moveTimeLimit.getBackground().darker()));
		textField_player2Name.setBorder(new LineBorder(spinner_moveTimeLimit.getBackground().darker()));
		textField_player3Name.setBorder(new LineBorder(spinner_moveTimeLimit.getBackground().darker()));
		
	}
}
