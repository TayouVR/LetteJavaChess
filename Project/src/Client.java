import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The Game Client
 */
public class Client {

	public Config config;
	UserInterfaceThread userInterfaceThread;

	private String configFilePath = "config.json";
	
	public Game localGame;

	public static void main(String[] args) {
		new Client();
	}
	
	/**
	 * Constructor
	 */
	public Client() {
		
		// create config object
		config = new Config(configFilePath, this);
		
		// Try load config file
		LoadConfig();

		// start UI up
		userInterfaceThread = new UserInterfaceThread(this);
		userInterfaceThread.start();

	}
	
	/**
	 * Responsible for playing the sound of the countdown
	 * @param distanceFromZero the normalized value for distance away from 0
	 */
	public void playCountdownSound(float distanceFromZero) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("blip c 07.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-15.0f + (1 - distanceFromZero) * 10); // Reduce volume by 10 decibels.
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Start a Game instance
	 * @param isLocal whether the instance is running as a server or is connected to one
	 */
	public void startNewGame(boolean isLocal) {
		userInterfaceThread.game = new GameScreen(userInterfaceThread);
		localGame = new Game();
		localGame.isServer = !isLocal;
	}
	
	/**
	 * Try to load config file, if the file doesn't exist save new one
	 */
	private void LoadConfig() {
		System.out.println("loading Config initially");

		if (Files.exists(Path.of(configFilePath))) {
			config.loadConfig();
		} else {
			config.saveConfig();
		}
	}
}

