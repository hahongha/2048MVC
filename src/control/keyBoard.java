package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Board;
import model.Direction;
import view.GamePanel;

public class keyBoard implements KeyListener{
	private GamePanel gamePanel;
	private Board gameBoard;
	
	public keyBoard(GamePanel gamePanel, Board gameBoard) {
		super();
		this.gamePanel = gamePanel;
		this.gameBoard = gameBoard;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			if(gameBoard.isGameOn()) {
			gameBoard.moveTiles(Direction.UP);
			gameBoard.show();
			gamePanel.repaint();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(gameBoard.isGameOn()) {
			gameBoard.moveTiles(Direction.LEFT);
			gameBoard.show();
			gamePanel.repaint();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(gameBoard.isGameOn()) {
			gameBoard.moveTiles(Direction.RIGHT);
			gameBoard.show();
			gamePanel.repaint();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			if(gameBoard.isGameOn()) {
			gameBoard.moveTiles(Direction.DOWN);
			gameBoard.show();
			gamePanel.repaint();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_N) {
			gameBoard.reset();
			gameBoard.setGameOn(true);
			gamePanel.repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
