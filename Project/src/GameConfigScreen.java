import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
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
	private JSpinner spinner_gameTimeWarning;
	private JSpinner spinner_moveTimeWarning;
	private JPanel mainPanel;
	public JTextField textField_player0Name;
	public JTextField textField_player1Name;
	public JTextField textField_player2Name;
	public JTextField textField_player3Name;
	private JComboBox comboBox_player0color;
	private JComboBox comboBox_player1color;
	private JComboBox comboBox_player2color;
	private JComboBox comboBox_player3color;
	private JLabel label_player1;
	private JLabel label_player2;
	private JLabel label_player3;
	private JLabel label_player4;
	
	// Timers
	java.util.Timer timer_move = new Timer();
	
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

		//Warning Time
		spinner_gameTimeWarning.addChangeListener(e -> {
				userInterfaceThread.client.localGame.properties.warningTimer = (int)spinner_gameTimeLimit.getValue();
		});


		// Buttons
		backButton.addActionListener(e -> {
			userInterfaceThread.setPanel(userInterfaceThread.chooseGameType.panel1);
		});
		startButton.addActionListener(e -> {
			userInterfaceThread.client.localGame.initializeGame(userInterfaceThread.game,
				comboBox_player0color.getSelectedIndex(),
				comboBox_player1color.getSelectedIndex(),
				comboBox_player2color.getSelectedIndex(),
				comboBox_player3color.getSelectedIndex());
			userInterfaceThread.setPanel(userInterfaceThread.game.panel1);
			
			if (userInterfaceThread.client.localGame.properties.fullGameTimer != 0) {
				runGameTimer();
			} else {
				userInterfaceThread.game.Timer.setText("No Time Limit");
			}

			if (userInterfaceThread.client.localGame.properties.perMoveTimer != 0) {
				runMoveTimer();
			} else {
				userInterfaceThread.game.playertime.setText("No Move Time Limit");
			}

		});


	}
	
	private void setUIStyles() {
		spinner_moveTimeLimit.setBorder(new LineBorder(spinner_moveTimeLimit.getBackground().darker()));
		spinner_gameTimeLimit.setBorder(new LineBorder(spinner_gameTimeLimit.getBackground().darker()));
		spinner_gameTimeWarning.setBorder(new LineBorder(spinner_gameTimeWarning.getBackground().darker()));
		spinner_moveTimeWarning.setBorder(new LineBorder(spinner_moveTimeWarning.getBackground().darker()));
		mainPanel.setBorder(new TitledBorder(new LineBorder(SystemColor.windowBorder), "Game Config"));
		textField_player0Name.setBorder(new LineBorder(textField_player0Name.getBackground().darker()));
		textField_player1Name.setBorder(new LineBorder(textField_player1Name.getBackground().darker()));
		textField_player2Name.setBorder(new LineBorder(textField_player2Name.getBackground().darker()));
		textField_player3Name.setBorder(new LineBorder(textField_player3Name.getBackground().darker()));
		
	}
	
	public void cancelMoveTimer() {
		timer_move.cancel();
		timer_move.purge();
	}
	
	public void runMoveTimer() {
		userInterfaceThread.game.playertime.setText("Time left:" + userInterfaceThread.client.localGame.properties.perMoveTimer);
		timer_move.cancel();
		timer_move.purge();
		timer_move.scheduleAtFixedRate(new TimerTask() {
			int seconds = userInterfaceThread.client.localGame.properties.perMoveTimer;
			@Override
			public void run() {
				seconds--;
				userInterfaceThread.game.playertime.setText("Time left:" + seconds);
				if (seconds <= (int)spinner_moveTimeWarning.getValue()) {
					userInterfaceThread.client.playCountdownSound((float)seconds / (int)spinner_gameTimeWarning.getValue());
				}
				if(seconds <= 0){
					userInterfaceThread.game.setAllFieldsDeselected();
					userInterfaceThread.client.localGame.nextPlayerTurn();
					runMoveTimer();
				}
			}
		},1000,1000);
	}
	
	public void runGameTimer() {
		userInterfaceThread.game.Timer.setText("Time left:" + userInterfaceThread.client.localGame.properties.fullGameTimer);
		java.util.Timer timer_game = new Timer();
		timer_game.scheduleAtFixedRate(new TimerTask() {
			int seconds = userInterfaceThread.client.localGame.properties.fullGameTimer;
			@Override
			public void run() {
				seconds--;
				userInterfaceThread.game.Timer.setText("Time left:" + seconds);
				if (seconds <= (int)spinner_gameTimeWarning.getValue()) {
					userInterfaceThread.client.playCountdownSound((float)seconds / (int)spinner_gameTimeWarning.getValue());
				}
				if(seconds <= 0){
					timer_game.cancel();
					timer_game.purge();
				}
			}
		},1000,1000);
	}
}
