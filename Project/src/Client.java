import java.nio.file.Files;
import java.nio.file.Path;

public class Client {

	public Config config;

	private String configFilePath = "config.json";

	public static void main(String[] args) {
		new Client();
	}

	public Client() {

		// start UI up
		UserInterfaceThread userInterfaceThread = new UserInterfaceThread();
		userInterfaceThread.start();

		config = new Config(configFilePath);

		LoadConfig();

	}

	private void LoadConfig() {
		System.out.println("loading Config");

		if (Files.exists(Path.of(configFilePath))) {
			config.LoadConfig();
		} else {
			config.SaveConfig();
		}
	}
}
