import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Figure {
	
	Image image;
	
	public FigureType type;
	public Direction direction;
	public int associatedPlayerId;
	
	public boolean isFirstMove = true;
	
	private int color;
	
	public Figure(FigureType type, Direction direction, int associatedPlayerId, int color) {
		this.type = type;
		this.direction = direction;
		this.associatedPlayerId = associatedPlayerId;
		this.color = color;
	}
	
	/**
	 * sets the fields to movable, that match the movement pattern of the figure
	 * needs to be overriden by child classes
	 * @param screen GameScreen ui class, that contains the entire game field
	 * @param srcField tile, from which the moves should be calculated from
	 */
	public void setMovableFields(GameScreen screen, Field srcField) {
		System.out.println("No Specific Figure Targetted, No moves set");
	}
	
	/**
	 * set the fields to movable, that are in a straight / diagonal line pattern from the figure
	 * @param directions fields in a line (one direction, e.g. X)
	 * @param srcField tile, from which the moves should be calculated from
	 */
	public void setValidStraightLineFields(ArrayList<ArrayList<Field>> directions, Field srcField) {
		for (int i = 0; i < directions.size(); i++) {
			int blockedPos = 1000;
			
			// look for blocked field (x+y = distance)
			for (Field field: directions.get(i)) {
				if (field.getFigure() != null && Math.abs(field.x - srcField.x) + Math.abs(field.y - srcField.y) < blockedPos) {
					blockedPos = Math.abs(field.x - srcField.x) + Math.abs(field.y - srcField.y);
				}
			}
			
			for (Field field: directions.get(i)) {
				// all fields, which are before blocked field = MOVE
				if (Math.abs(field.x - srcField.x) + Math.abs(field.y - srcField.y) < blockedPos) {
					field.setValidMove(Field.Move.MOVE);
				// the field, that is blocked = ATTACK, but only if its not the own players unit
				} else if (Math.abs(field.x - srcField.x) + Math.abs(field.y - srcField.y) == blockedPos && field.getFigure().direction != srcField.getFigure().direction) {
					field.setValidMove(Field.Move.ATTACK);
				// else set field to DEFAULT
				} else {
					field.setValidMove(Field.Move.DEFAULT);
				}
			}
			
		}
	}
	
	/**
	 * Gets the buffered Image from a file
	 * @param filename file path to look for the file
	 * @return Image as BufferedImage
	 */
	private BufferedImage getImage(String filename) {
		// This time, you can use an InputStream to load
		try {
			// Grab the InputStream for the image.
			InputStream in = Files.newInputStream(Path.of(filename));
			
			// Then read it.
			return ImageIO.read(in);
		} catch (IOException e) {
			System.out.println("The image was not loaded.");
			//System.exit(1);
		}
		
		return null;
	}
	
	/**
	 * get ImageIcon for figure at target resolution
	 * @param targetResolution target resolution to resize the image to
	 * @return ImageIcon for figure
	 */
	public ImageIcon getSingleImage(int targetResolution) {
		BufferedImage srcImage = getImage("chess_2.png");
		int oneFigureSize = srcImage.getWidth()/6;
		
		srcImage = cropImage(srcImage, new Rectangle(oneFigureSize*type.getValue(), oneFigureSize*color, oneFigureSize, oneFigureSize));
		
		return new ImageIcon(srcImage.getScaledInstance(targetResolution, targetResolution, 1));
	}
	
	/**
	 * crop image via rect
	 * @param src source Image
	 * @param rect position and size of cropped region
	 * @return cropped region
	 */
	private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
		BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
		return dest;
	}
}
