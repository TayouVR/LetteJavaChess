import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

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
