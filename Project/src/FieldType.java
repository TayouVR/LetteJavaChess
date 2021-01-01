import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum FieldType {
	REGULAR(0),
	THREE_PLAYERS(1),
	FOUR_PLAYERS(2);
	
	private int value;
	private static Map map = new HashMap<>();
	
	private FieldType(int value) {
		this.value = value;
	}
	
	static {
		for (FieldType fieldType : FieldType.values()) {
			map.put(fieldType.value, fieldType);
		}
	}
	
	public static FieldType valueOf(int fieldType) {
		return (FieldType) map.get(fieldType);
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