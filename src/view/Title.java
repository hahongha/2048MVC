package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import test.Game2048;
import utils.DrawUtils;

public class Title extends JPanel{

	private static final long serialVersionUID = 1L;
	private String saveDataPath = System.getProperty("user.dir") + File.separator + "bin" + File.separator
			+ "lib" + File.separator;
	private Font font = new Font("Times New Roman", Font.BOLD, 30);

	public Title(Game2048 window) {
		setLayout(null);
		setSize(Game2048.WIDTH, Game2048.HEIGHT);
//		setFocusable(true);
		setBackground(Color.WHITE);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawRect(Game2048.WIDTH/3, Game2048.HEIGHT/2,Game2048.WIDTH/3,70);
		g.drawString("PLAY", Game2048.WIDTH/2-DrawUtils.getMessageWidth("PLAY", font, g)/2,
				Game2048.HEIGHT/2 + DrawUtils.getMessageWidth("PLAY", font, g)/2);
		
		//continue
		g.drawRect(Game2048.WIDTH/4, Game2048.HEIGHT*2/3,Game2048.WIDTH/2,70);
		g.drawString("CONTINUE", Game2048.WIDTH/2-DrawUtils.getMessageWidth("CONTINUE", font, g)/2,
				Game2048.HEIGHT*2/3 + DrawUtils.getMessageWidth("CONTINUE", font, g)/3);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(saveDataPath+"/2048logo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, Game2048.WIDTH/2 - 100, 50,200, 200, null);
	}
}