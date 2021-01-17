import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * configure game settings like player count, names, colors and timers
 */
public class GameConfigScreen {
	
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
	public JComboBox comboBox_player0color;
	public JComboBox comboBox_player1color;
	public JComboBox comboBox_player2color;
	public JComboBox comboBox_player3color;
	public JLabel label_player1;
	public JLabel label_player2;
	public JLabel label_player3;
	public JLabel label_player4;
	public JCheckBox checkbox_ai_player1;
	public JCheckBox checkbox_ai_player2;
	public JCheckBox checkbox_ai_player3;
	public JCheckBox checkbox_ai_player4;
	public JPanel panel_player1Inputs;
	public JPanel panel_player2Inputs;
	public JPanel panel_player3Inputs;
	public JPanel panel_player4Inputs;
	
	// Timers
	java.util.Timer timer_move = new Timer();
	
	public GameConfigScreen() {
		
		setUIStyles();

		// Player Count
		slider_playerCount.addChangeListener(e -> {
			JSlider slider = (JSlider) e.getSource();
			int sliderValue = slider.getValue();
			Client.instance.localGame.setPlayerCount(sliderValue);
			slider_playerCount.setValue(sliderValue);
			
			panel_player1Inputs.setVisible(sliderValue >= 1);
			panel_player2Inputs.setVisible(sliderValue >= 2);
			panel_player3Inputs.setVisible(sliderValue >= 3);
			panel_player4Inputs.setVisible(sliderValue >= 4);
			
			label_player1.setVisible(sliderValue >= 1);
			label_player2.setVisible(sliderValue >= 2);
			label_player3.setVisible(sliderValue >= 3);
			label_player4.setVisible(sliderValue >= 4);
			
		});
		
		// set colors
		String[] availableColorOptions = {"White", "Black", "Dark Grey", "Light Grey", "Pink", "Red", "Orange", "Yellow", "Green", "Blue", "Cyan", "Purple"};
		
		for (String option: availableColorOptions) {
			comboBox_player0color.addItem(option);
			comboBox_player1color.addItem(option);
			comboBox_player2color.addItem(option);
			comboBox_player3color.addItem(option);
		}
		// set initially selected colors
		comboBox_player0color.setSelectedIndex(0);
		comboBox_player1color.setSelectedIndex(1);
		comboBox_player2color.setSelectedIndex(10);
		comboBox_player3color.setSelectedIndex(11);
		
		/*
		// Block taken colors from other dropdowns
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
		
		// Set player properties
		ActionListener playerActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				enterPlayerData();
			}
		};
		
		// set color
		comboBox_player0color.addActionListener(playerActionListener);
		comboBox_player1color.addActionListener(playerActionListener);
		comboBox_player2color.addActionListener(playerActionListener);
		comboBox_player3color.addActionListener(playerActionListener);
		
		// set name
		textField_player0Name.addActionListener(playerActionListener);
		textField_player1Name.addActionListener(playerActionListener);
		textField_player2Name.addActionListener(playerActionListener);
		textField_player3Name.addActionListener(playerActionListener);
		
		// set if AI
		checkbox_ai_player1.addActionListener(playerActionListener);
		checkbox_ai_player2.addActionListener(playerActionListener);
		checkbox_ai_player3.addActionListener(playerActionListener);
		checkbox_ai_player4.addActionListener(playerActionListener);
		
		// Move Time
		spinner_moveTimeLimit.addChangeListener(e -> {
			Client.instance.localGame.properties.perMoveTimer = (int)spinner_moveTimeLimit.getValue();
		});
		
		// Game Time
		spinner_gameTimeLimit.addChangeListener(e -> {
			Client.instance.localGame.properties.fullGameTimer = (int)spinner_gameTimeLimit.getValue();
		});

		//Warning Time
		spinner_gameTimeWarning.addChangeListener(e -> {
			Client.instance.localGame.properties.warningTimer = (int)spinner_gameTimeLimit.getValue();
		});


		// Buttons
		backButton.addActionListener(e -> {
			UserInterfaceThread.setPanel(UserInterfaceThread.chooseGameType.panel1);
		});
		startButton.addActionListener(e -> {
			enterPlayerData();
			
			Client.instance.localGame.initializeGame(UserInterfaceThread.game);
			UserInterfaceThread.setPanel(UserInterfaceThread.game.panel1);
			
			if (Client.instance.localGame.properties.fullGameTimer != 0) {
				runGameTimer();
			} else {
				UserInterfaceThread.game.gameTimer.setText("No Time Limit");
			}

			if (Client.instance.localGame.properties.perMoveTimer != 0) {
				runMoveTimer();
			} else {
				UserInterfaceThread.game.turnTimer.setText("No Move Time Limit");
			}

		});


	}
	
	
	public void enterPlayerData() {
		if (Client.instance.localGame.properties.playerCount >= 1) {
			Client.instance.localGame.players[0].colorIndex = comboBox_player0color.getSelectedIndex();
			Client.instance.localGame.players[0].name = !textField_player0Name.getText().equals("") ? textField_player0Name.getText() : "Unnamed 1";
			Client.instance.localGame.players[0].isAi = checkbox_ai_player1.isSelected();
		}
		if (Client.instance.localGame.properties.playerCount >= 2) {
			Client.instance.localGame.players[1].colorIndex = comboBox_player1color.getSelectedIndex();
			Client.instance.localGame.players[1].name = !textField_player1Name.getText().equals("") ? textField_player1Name.getText() : "Unnamed 2";
			Client.instance.localGame.players[1].isAi = checkbox_ai_player2.isSelected();
		}
		if (Client.instance.localGame.properties.playerCount >= 3) {
			Client.instance.localGame.players[2].colorIndex = comboBox_player2color.getSelectedIndex();
			Client.instance.localGame.players[2].name = !textField_player2Name.getText().equals("") ? textField_player2Name.getText() : "Unnamed 3";
			Client.instance.localGame.players[2].isAi = checkbox_ai_player3.isSelected();
		}
		if (Client.instance.localGame.properties.playerCount >= 4) {
			Client.instance.localGame.players[3].colorIndex = comboBox_player3color.getSelectedIndex();
			Client.instance.localGame.players[3].name = !textField_player3Name.getText().equals("") ? textField_player3Name.getText() : "Unnamed 4";
			Client.instance.localGame.players[3].isAi = checkbox_ai_player4.isSelected();
		}
	}
	
	/**
	 * sets titled border and borders (because they might be broken when theming on windows)
	 */
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
	
	/**
	 * cancel the move timer
	 */
	public void cancelMoveTimer() {
		timer_move.cancel();
		timer_move.purge();
	}
	
	/**
	 * Run the move timer
	 */
	public void runMoveTimer() {
		UserInterfaceThread.game.turnTimer.setText("Time left:" + Client.instance.localGame.properties.perMoveTimer);
		try	{
			timer_move.cancel();
			timer_move.purge();
		} catch (Exception e) {
			System.out.println("Move timer couldn't be cancelled and purged");
		}
		timer_move.scheduleAtFixedRate(new TimerTask() {
			int seconds = Client.instance.localGame.properties.perMoveTimer;
			@Override
			public void run() {
				seconds--;
				UserInterfaceThread.game.turnTimer.setText("Time left:" + seconds);
				if (seconds <= (int)spinner_moveTimeWarning.getValue()) {
					Client.instance.playCountdownSound((float)seconds / (int)spinner_gameTimeWarning.getValue());
				}
				if(seconds <= 0){
					UserInterfaceThread.game.setAllFieldsDeselected();
					Client.instance.localGame.nextPlayerTurn();
					runMoveTimer();
				}
			}
		},1000,1000);
	}
	
	/**
	 * Run the game timer
	 */
	public void runGameTimer() {
		UserInterfaceThread.game.gameTimer.setText("Time left:" + Client.instance.localGame.properties.fullGameTimer);
		java.util.Timer timer_game = new Timer();
		timer_game.scheduleAtFixedRate(new TimerTask() {
			int seconds = Client.instance.localGame.properties.fullGameTimer;
			@Override
			public void run() {
				seconds--;
				UserInterfaceThread.game.gameTimer.setText("Time left:" + seconds);
				if (seconds <= (int)spinner_gameTimeWarning.getValue()) {
					Client.instance.playCountdownSound((float)seconds / (int)spinner_gameTimeWarning.getValue());
				}
				if(seconds <= 0){
					timer_game.cancel();
					timer_game.purge();
				}
			}
		},1000,1000);
	}
}
