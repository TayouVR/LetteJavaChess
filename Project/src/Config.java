import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Config {

	JSONObject jsonObject;

	private String configFilePath;

	private int selectedProfile = 0; // ID of last selected profile
	private ArrayList<Integer> resolution = new ArrayList<>(); // [0] = width, [1] = height
	private ArrayList<String> existingFigureSkins = new ArrayList<>(); // paths to all skin folders, that are currently loaded
	private Skin[] loadedFigureSkins; // all skins, that are currently loaded

	public Config(String configFilePath) {
		this.configFilePath = configFilePath;

		existingFigureSkins.add("Default/");
		resolution.add(1920);
		resolution.add(1080);

		// Initialize JSON Object with default values
		jsonObject = new JSONObject();
		jsonObject.put("selectedProfile", selectedProfile);
		jsonObject.put("resolution", resolution);
		jsonObject.put("existingFigureSkins", existingFigureSkins);
	}

	public void LoadConfig() {
		System.out.println("loading Config");

		try {
			jsonObject = (JSONObject) new JSONParser().parse(Files.readString(Path.of(configFilePath), StandardCharsets.UTF_8));

			System.out.println("Config File successfully loaded");
		} catch (Exception e) {
			System.out.println("Could not load config File:");
			e.printStackTrace();
		}
	}

	public void SaveConfig() {
		System.out.println("saving Config");

		try {
			Files.writeString(Path.of(configFilePath), jsonObject.toJSONString(), StandardCharsets.UTF_8);

			System.out.println("Config File successfully saved");
		} catch (Exception e) {
			System.out.println("Could not save config File:");
			e.printStackTrace();
		}
	}
}
