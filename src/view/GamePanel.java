package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import control.keyBoard;
import model.Board;
import model.Tile;
import test.Game2048;
import utils.DrawUtils;

public class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	protected Board gameBoard;
	public static final int TILE_WIDTH = 100;
	public static final int TILE_HEIGHT = 100;
	public static int SPACING = TILE_WIDTH / 10; // khoảng cách giữa các ô
	public static int BOARD_WIDTH = (Board.COLS + 1) * SPACING + Board.COLS * TILE_WIDTH;
	public static int BOARD_HEIGHT = (Board.ROWS + 1) * SPACING + Board.ROWS * TILE_HEIGHT;
	public static final int ARC_WIDTH = 15;// đô bo góc
	public static final int ARC_HEIGHT = 15;

	private keyBoard key;

	public GamePanel(Board board) {
		setSize(Game2048.WIDTH, Game2048.HEIGHT);
		setFocusable(true);
		setLayout(null);
		gameBoard = board;
		key = new keyBoard(this, gameBoard);
		addKeyListener(key);
		setBackground(new Color(0x61876E));
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (gameBoard.isGameOn()) {
			renderPlay(g);
		} else {
			renderEnd(g, Game2048.WIDTH, Game2048.HEIGHT);
		}
	}

	// ve o

	private void renderPlay(Graphics g) {
		g.setColor(new Color(0x61876E));
		g.fillRect(0, 0, Game2048.WIDTH, Game2048.HEIGHT);
		g.setColor(new Color(0x3C6255));
		g.fillRoundRect(195, 80, 118, 50, 15, 15);
		g.fillRoundRect(325, 80, 118, 50, 15, 15);

		g.setColor(Color.white);
		g.setFont(new Font("Monospaced", Font.BOLD, 40));
		g.drawString("---2048---", 110, 50);

		g.setColor(Color.white);
		g.setFont(new Font("NewellsHand", Font.BOLD, 17));
		g.drawString("SCORE", 220, 100);
		g.drawString("HIGH SCORE", 330, 100);

		g.setFont(new Font("NewellsHand", Font.PLAIN, 20));
		if (gameBoard.getScore() < 10)
			g.drawString("" + gameBoard.getScore(), 250, 125);
		else if (gameBoard.getScore() >= 10 && gameBoard.getScore() < 100)
			g.drawString("" + gameBoard.getScore(), 240, 125);
		else if (gameBoard.getScore() >= 100 && gameBoard.getScore() < 1000)
			g.drawString("" + gameBoard.getScore(), 235, 125);
		else if (gameBoard.getScore() > 1000)
			g.drawString("" + gameBoard.getScore(), 230, 125);

		if (gameBoard.getHighScore() < 10)
			g.drawString("" + gameBoard.getHighScore(), 380, 125);
		else if (gameBoard.getHighScore() >= 10 && gameBoard.getHighScore() < 100)
			g.drawString("" + gameBoard.getHighScore(), 375, 125);
		else if (gameBoard.getHighScore() > 100 && gameBoard.getHighScore() < 1000)
			g.drawString("" + gameBoard.getHighScore(), 370, 125);
		else if (gameBoard.getHighScore() >= 1000)
			g.drawString("" + gameBoard.getHighScore(), 365, 125);
		int y = Game2048.HEIGHT - BOARD_HEIGHT - 40;
		g.setColor(new Color(0x61876E));
		g.fillRect(0, y, BOARD_WIDTH, BOARD_HEIGHT);
		g.setColor(Color.GREEN);
		// ve bang
		for (int j = 0; j < Board.COLS; j++) {
			for (int i = 0; i < Board.ROWS; i++) {
				g.setColor(new Color(0x3C6255));
				g.drawRoundRect(getTileX(i), y + getTileY(j), TILE_WIDTH, TILE_HEIGHT, ARC_WIDTH, ARC_HEIGHT);
				g.fillRoundRect(getTileX(i), y + getTileY(j), TILE_WIDTH, TILE_HEIGHT, ARC_WIDTH, ARC_HEIGHT);
			}
		}
		for (int j = 0; j < Board.COLS; j++) {
			for (int i = 0; i < Board.ROWS; i++) {
				Tile current = gameBoard.getBoard()[j][i];
				if (current != null) {
					g.setColor(current.setBackGround());
					g.drawRoundRect(getTileX(i), y + getTileY(j), TILE_WIDTH, TILE_HEIGHT, ARC_WIDTH, ARC_HEIGHT);
					g.fillRoundRect(getTileX(i), y + getTileY(j), TILE_WIDTH, TILE_HEIGHT, ARC_WIDTH, ARC_HEIGHT);
					g.setFont(current.setFont());
					// nho can lai le
					g.setColor(current.setText());
					g.drawString("" + current.getValue(),
							getTileX(i) + TILE_WIDTH / 2
									- DrawUtils.getMessageWidth("" + current.getValue(), current.setFont(), g) / 2,
							y + getTileY(j) + TILE_HEIGHT / 2
									+ DrawUtils.getMessageHeight("" + current.getValue(), current.setFont(), g) / 2);
				}
			}
		}
		g.dispose();
	}

	private void renderEnd(Graphics g, int width, int height) {
		Font fontString = new Font("Monospaced", Font.BOLD, 50);
		Font fontforScore = new Font("Monospaced", Font.BOLD, 40);
		// can giua
		int y = height / 3;
		g.setFont(fontString);
		if (gameBoard.isWin()) {
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			int x1 = width / 2 - DrawUtils.getMessageWidth("You Win", fontString, g) / 2;
			g.setColor(new Color(209, 77, 114));
			g.drawString("You Win", x1, y);
			g.setColor(new Color(209, 77, 114));
			g.drawRoundRect(width / 4, y + 100, width / 2, 60, 35, 35);
			g.fillRoundRect(width / 4, y + 100, width / 2, 60, 35, 35);
			g.setFont(fontforScore);
			if (gameBoard.checkScore())
				g.drawString("HIGH SCORE", width / 2 - DrawUtils.getMessageWidth("HIGH SCORE", fontforScore, g) / 2,
						y + 50);
			else
				g.drawString("Score", width / 2 - DrawUtils.getMessageWidth("Score", fontforScore, g) / 2, y + 50);
			g.setColor(Color.WHITE);
			g.setFont(fontforScore);
			g.drawString("" + gameBoard.getScore(),
					width / 2 - DrawUtils.getMessageWidth("" + gameBoard.getScore(), fontString, g) / 2, y + 150);
		} else if (gameBoard.isDead()) {
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			int x1 = width / 2 - DrawUtils.getMessageWidth("Game Over", fontString, g) / 2;
			g.setColor(new Color(122, 168, 116));
			g.drawString("Game Over", x1, y);
			g.setFont(fontforScore);
			if (gameBoard.checkScore())
				g.drawString("HIGH SCORE", width / 2 - DrawUtils.getMessageWidth("HIGH SCORE", fontforScore, g) / 2,
						y + 50);
			else
				g.drawString("Score", width / 2 - DrawUtils.getMessageWidth("Score", fontforScore, g) / 2, y + 50);
			g.setColor(new Color(122, 168, 116));
			g.drawRoundRect(x1 + 20, y + 100, width / 2, 60, 35, 35);
			g.fillRoundRect(x1 + 20, y + 100, width / 2, 60, 35, 35);
			g.setColor(Color.white);
			g.setFont(fontforScore);
			g.drawString("" + gameBoard.getScore(),
					width / 2 - DrawUtils.getMessageWidth("" + gameBoard.getScore(), fontString, g) / 2, y + 150);
		} else {
			g.setColor(Color.BLACK);
			fontforScore = fontforScore.deriveFont(25f);
			g.setFont(fontforScore);
			g.drawString("Press P to continue",
					width / 2 - DrawUtils.getMessageWidth("Press P to continue", fontforScore, g) / 2, y + 100);
		}
		g.setColor(Color.BLACK);
		fontforScore = fontforScore.deriveFont(25f);
		g.setFont(fontforScore);
		g.drawString("Press N to reset game",
				width / 2 - DrawUtils.getMessageWidth("Press N to reset game", fontforScore, g) / 2, y + 190);
		g.drawString("Press X to save game and exit",
				width / 2 - DrawUtils.getMessageWidth("Press X to save and exit game", fontforScore, g) / 2, y + 280);

		g.dispose();

	}

	private int getTileY(int row) {
		return SPACING + row * TILE_HEIGHT + row * SPACING;
	}

	private int getTileX(int col) {
		return SPACING + col * TILE_WIDTH + col * SPACING;
	}

	public Board getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}
}
