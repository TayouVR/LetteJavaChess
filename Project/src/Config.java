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
	private FulllscreenMode fullscreenMode = FulllscreenMode.FULLSCREEN; // all skins, that are currently loaded
	
	/////////////////////////
	/// Getter and Setter ///
	/////////////////////////
	
	public int getSelectedProfile() {
		return selectedProfile;
	}
	
	public void setSelectedProfile(int selectedProfile) {
		this.selectedProfile = selectedProfile;
	}
	
	public Object[] getResolution() {
		return resolution.toArray();
	}
	
	public void setResolution(ArrayList<Integer> resolution) {
		this.resolution = resolution;
	}
	
	public ArrayList<String> getExistingFigureSkins() {
		return existingFigureSkins;
	}
	
	public void setExistingFigureSkins(ArrayList<String> existingFigureSkins) {
		this.existingFigureSkins = existingFigureSkins;
	}
	
	public Skin[] getLoadedFigureSkins() {
		return loadedFigureSkins;
	}
	
	public void setLoadedFigureSkins(Skin[] loadedFigureSkins) {
		this.loadedFigureSkins = loadedFigureSkins;
	}
	
	public FulllscreenMode getFullscreenMode() {
		return fullscreenMode;
	}
	
	public void setFullscreenMode(FulllscreenMode fullscreenMode) {
		this.fullscreenMode = fullscreenMode;
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
		jsonObject.put("fullscreenMode", fullscreenMode);
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
	
	public enum FulllscreenMode {
		FULLSCREEN(0),
		WINDOWED(1),
		MAXIMIZED(2);
		
		private int value;
		private static Map map = new HashMap<>();
		
		private FulllscreenMode(int value) {
			this.value = value;
		}
		
		static {
			for (FulllscreenMode pageType : FulllscreenMode.values()) {
				map.put(pageType.value, pageType);
			}
		}
		
		public static FulllscreenMode valueOf(int pageType) {
			return (FulllscreenMode) map.get(pageType);
		}
		
		public int getValue() {
			return value;
		}
		
		@Override
		public String toString() {
			return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase(Locale.ROOT);
		}
	}
}
