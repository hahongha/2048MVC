package test;

import javax.swing.JFrame;

import view.GamePanel;



public class Game2048 {
	public static final int WIDTH = 460;
	public static final int HEIGHT = 700;
	
	public static void main(String[] args) {
	JFrame window = new JFrame("2048 test");
	GamePanel game = new GamePanel();
	window.setSize(WIDTH, HEIGHT);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//tắt chương trình khi nhấn X
	window.add(game);
	window.setResizable(false);//chặn không cho thay đổi kích thước
	window.setLocationRelativeTo(null);//căn giữa
	window.setVisible(true);//hiển thị
}
}
