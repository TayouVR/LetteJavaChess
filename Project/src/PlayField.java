import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum PlayField {
	REGULAR(0),
	THREE_PLAYERS(1),
	FOUR_PLAYERS(2);
	
	private int value;
	private static Map map = new HashMap<>();
	
	private PlayField(int value) {
		this.value = value;
	}
	
	static {
		for (PlayField playField : PlayField.values()) {
			map.put(playField.value, playField);
		}
	}
	
	public static PlayField valueOf(int fieldType) {
		return (PlayField) map.get(fieldType);
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		String outName = "";
		for (String s: name().split("_")) {
			outName += name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase(Locale.ROOT);
		}
		return outName;
	}
}