import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Config {

	JSONObject jsonObject;
	
	Client client;

	private String configFilePath;

	private int selectedProfile = 0; // ID of last selected profile
	private ArrayList<Integer> resolution = new ArrayList<>(); // [0] = width, [1] = height
	private ArrayList<String> existingFigureSkins = new ArrayList<>(); // paths to all skin folders, that are currently loaded
	private Skin[] loadedFigureSkins; // all skins, that are currently loaded
	private FullscreenMode fullscreenMode = FullscreenMode.FULLSCREEN; // all skins, that are currently loaded
	
	/////////////////////////
	/// Getter and Setter ///
	/////////////////////////
	
	public int getSelectedProfile() {
		return selectedProfile;
	}
	
	public void setSelectedProfile(int selectedProfile) {
		this.selectedProfile = selectedProfile;
		jsonObject.put("selectedProfile", selectedProfile);
	}
	
	public Object[] getResolution() {
		return resolution.toArray();
	}
	
	public void setResolution(ArrayList<Integer> resolution) {
		this.resolution = resolution;
		jsonObject.put("resolution", resolution);
	}
	
	public ArrayList<String> getExistingFigureSkins() {
		return existingFigureSkins;
	}
	
	public void setExistingFigureSkins(ArrayList<String> existingFigureSkins) {
		this.existingFigureSkins = existingFigureSkins;
		jsonObject.put("existingFigureSkins", existingFigureSkins);
	}
	
	public Skin[] getLoadedFigureSkins() {
		return loadedFigureSkins;
	}
	
	public void setLoadedFigureSkins(Skin[] loadedFigureSkins) {
		this.loadedFigureSkins = loadedFigureSkins;
	}
	
	public FullscreenMode getFullscreenMode() {
		return fullscreenMode;
	}
	
	public void setFullscreenMode(FullscreenMode fullscreenMode) {
		this.fullscreenMode = fullscreenMode;
		jsonObject.put("fullscreenMode", fullscreenMode.getValue());
	}
	
	
	/////////////////////////////////////////////////
	
	

	public Config(String configFilePath, Client client) {
		this.configFilePath = configFilePath;
		this.client = client;

		existingFigureSkins.add("Default/");
		resolution.add(1920);
		resolution.add(1080);

		// Initialize JSON Object with default values
		jsonObject = new JSONObject();
		jsonObject.put("selectedProfile", selectedProfile);
		jsonObject.put("resolution", resolution);
		jsonObject.put("existingFigureSkins", existingFigureSkins);
		jsonObject.put("fullscreenMode", fullscreenMode.getValue());
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
			System.out.println(jsonObject.toJSONString());
			Files.writeString(Path.of(configFilePath), jsonObject.toJSONString(), StandardCharsets.UTF_8);

			System.out.println("Config File successfully saved");
		} catch (Exception e) {
			System.out.println("Could not save config File:");
			e.printStackTrace();
		}
	}
}
