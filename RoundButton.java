import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class RoundButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String filePathString;
	
	public RoundButton(String string, Icon icon, String filePath) {
		
		this.filePathString = filePath;
		setText(string);
		setIcon(icon);
		setBorder(new EmptyBorder(0,0,0,0));
	}

	public static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
		
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2 = output.createGraphics();
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);

	    g2.dispose();

	    return output;
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g.create();
		try {
			BufferedImage image = ImageIO.read(new File(filePathString));
			BufferedImage rImage = makeRoundedCorner(image, 100);
			g2.drawImage(rImage, 0, 0, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.dispose();
	}
}
