import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum FigureType {

	KING(5),
	QUEEN(4),
	ROOK(3),   // Turm
	BISHOP(2), // Läufer
	KNIGHT(1), // Pferd / Springer
	PAWN(0);    // Bauer
	
	
	private int value;
	private static Map map = new HashMap<>();
	
	private FigureType(int value) {
		this.value = value;
	}
	
	static {
		for (FigureType pageType : FigureType.values()) {
			map.put(pageType.value, pageType);
		}
	}
	
	public static FigureType valueOf(int type) {
		return (FigureType) map.get(type);
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase(Locale.ROOT);
	}
}
