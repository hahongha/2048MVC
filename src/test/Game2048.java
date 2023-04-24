package test;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.changePanel;
import model.Board;
import view.GamePanel;
import view.Title;

public class Game2048 {
	public static final int WIDTH = 460;
	public static final int HEIGHT = 700;
	public JFrame window;
	private GamePanel game;
	private Title title;
	private changePanel change;
	public Game2048() {
		window = new JFrame("2048 test");
		title = new Title(this);
		game = new GamePanel(new Board());
		change = new changePanel(this, game, title);
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);// chan khong cho thay doi kich thuoc
		window.setBackground(Color.WHITE);
		window.setLocationRelativeTo(null);// can giua

		window.setLayout(new BorderLayout());
		JButton next = new JButton("PLAY");
		next.setBounds(Game2048.WIDTH/3, Game2048.HEIGHT/2,Game2048.WIDTH/3,70);
		next.addActionListener(change);
		JButton btnCon = new JButton("CONTINUE");
		btnCon.addActionListener(change);
		btnCon.setBounds(Game2048.WIDTH/4, Game2048.HEIGHT*2/3,Game2048.WIDTH/2,70);
        title.add(next);
        title.add(btnCon);
        
		window.add(title);
		
		window.setVisible(true);// hien thi
	}
	public void movePanels(JPanel panel1, JPanel panel2) {
		window.remove(panel1);
		window.add(panel2, BorderLayout.CENTER);
		panel2.requestFocusInWindow();
		window.validate();
	}
	
	public static void main(String[] args) {
		new Game2048();
	}
}
