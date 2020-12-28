import java.nio.file.Files;
import java.nio.file.Path;

public class Client {

	public Config config;
	UserInterfaceThread userInterfaceThread;

	private String configFilePath = "config.json";

	public static void main(String[] args) {
		new Client();
	}

	public Client() {
		
		config = new Config(configFilePath, this);
		
		LoadConfig();

		// start UI up
		userInterfaceThread = new UserInterfaceThread(this);
		userInterfaceThread.start();

	}

	private void LoadConfig() {
		System.out.println("loading Config initially");

		if (Files.exists(Path.of(configFilePath))) {
			config.LoadConfig();
		} else {
			config.SaveConfig();
		}
	}
}
