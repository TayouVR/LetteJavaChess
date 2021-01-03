import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public enum FullscreenMode {
	FULLSCREEN(0),
	WINDOWED(1),
	MAXIMIZED(2);
	
	private int value;
	private static Map map = new HashMap<>();
	
	private FullscreenMode(int value) {
		this.value = value;
	}
	
	static {
		for (FullscreenMode pageType : FullscreenMode.values()) {
			map.put(pageType.value, pageType);
		}
	}
	
	public static FullscreenMode valueOf(int pageType) {
		return (FullscreenMode) map.get(pageType);
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase(Locale.ROOT);
	}
}
