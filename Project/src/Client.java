import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


public class Client {

	public Config config;
	UserInterfaceThread userInterfaceThread;
	AudioInputStream audioInputStream;

	private String configFilePath = "config.json";
	
	public Game localGame;

	public static void main(String[] args) {
		new Client();
	}

	public Client() {
		
		// create config object
		config = new Config(configFilePath, this);
		
		// Try load config file
		LoadConfig();

		// start UI up
		userInterfaceThread = new UserInterfaceThread(this);
		userInterfaceThread.start();
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("blip c 07.wav"));
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	public void playCountdownSound() {
		Thread t = new SoundThread();
		t.start();
	}
	
	public class SoundThread extends Thread {
		
		@Override
		public void run() {
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
				clip.start();
				while (true) {
					if (!clip.isRunning()) {
						clip.stop();
						clip.close();
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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

