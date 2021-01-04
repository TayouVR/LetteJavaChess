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
	
	public boolean isFirstMove = true;
	
	private int color;
	
	public Figure(FigureType type, int color, Direction direction) {
		this.type = type;
		this.color = color;
		this.direction = direction;
	}
	
	public void setMovableFields(GameScreen screen, Field srcField) {
		System.out.println("No Specific Figure Targetted, No moves set");
	}
	
	public void setValidStraightLineFields(ArrayList<ArrayList<Field>> directions, Field srcField, int count) {
		for (int i = 0; i < count; i++) {
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
	
	public ImageIcon getSingleImage() {
		BufferedImage srcImage = getImage("chess_2.png");
		int oneFigureSize = srcImage.getWidth()/6;
		
		
		switch (type) {
			case KING -> {
				srcImage = cropImage(srcImage, new Rectangle(oneFigureSize*5, oneFigureSize*color, oneFigureSize, oneFigureSize));
			}
			case QUEEN -> {
				srcImage = cropImage(srcImage, new Rectangle(oneFigureSize*4, oneFigureSize*color, oneFigureSize, oneFigureSize));
			}
			case ROOK -> {
				srcImage = cropImage(srcImage, new Rectangle(oneFigureSize*3, oneFigureSize*color, oneFigureSize, oneFigureSize));
			}
			case BISHOP -> {
				srcImage = cropImage(srcImage, new Rectangle(oneFigureSize*2, oneFigureSize*color, oneFigureSize, oneFigureSize));
			}
			case KNIGHT -> {
				srcImage = cropImage(srcImage, new Rectangle(oneFigureSize, oneFigureSize*color, oneFigureSize, oneFigureSize));
			}
			case PAWN -> {
				srcImage = cropImage(srcImage, new Rectangle(0, oneFigureSize*color, oneFigureSize, oneFigureSize));
			}
		}
		
		
		return new ImageIcon(srcImage.getScaledInstance(64, 64, 1));
	}
	
	private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
		BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
		return dest;
	}
}
