package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Board;
import model.Direction;
import utils.FileInput;
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
		if(e.getKeyCode()==KeyEvent.VK_9) {
			if(gameBoard.isGameOn()) {
			gameBoard.moveTiles(Direction.cheo9);
			gameBoard.show();
			gamePanel.repaint();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_7) {
			if(gameBoard.isGameOn()) {
			gameBoard.moveTiles(Direction.cheo7);
			gameBoard.show();
			gamePanel.repaint();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_1) {
			if(gameBoard.isGameOn()) {
			gameBoard.moveTiles(Direction.cheo1);
			gameBoard.show();
			gamePanel.repaint();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_3) {
			if(gameBoard.isGameOn()) {
			gameBoard.moveTiles(Direction.cheo3);
			gameBoard.show();
			gamePanel.repaint();
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_N) {
			gameBoard.reset();
			gameBoard.setGameOn(true);
			gamePanel.repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_X) {
			if(gameBoard.isGameOn()) {
			gameBoard.setDead(true);
			FileInput.writeBoard(gameBoard);
			gameBoard.setGameOn(false);
			}
			gamePanel.repaint();
			System.exit(0);
		}
		if(e.getKeyCode()== KeyEvent.VK_P) {
			if(gameBoard.isGameOn()) 
				{
				gameBoard.setGameOn(false);
				FileInput.writeBoard(gameBoard);
				}
			else {
				gameBoard.setGameOn(true);
			}
			gamePanel.repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_S) {
			gameBoard.getSound().change();
		}
		
		if(e.getKeyCode()==KeyEvent.VK_Z) {
			gameBoard.undo();
			gamePanel.repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_Y) {
			gameBoard.redo();
			gamePanel.repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_M) {
			gameBoard.mixTile();
			gamePanel.repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
