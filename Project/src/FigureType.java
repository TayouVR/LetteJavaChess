import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * All types a figure can be
 */
public enum FigureType {

	/** König */
	KING(5),
	/** Königin */
	QUEEN(4),
	/** Turm */
	ROOK(3),
	/** Läufer */
	BISHOP(2),
	/** Pferd / Springer */
	KNIGHT(1),
	/** Bauer */
	PAWN(0);
	
	
	private int value;
	private static Map map = new HashMap<>();
	
	FigureType(int value) {
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
