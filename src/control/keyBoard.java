package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.Board;
import model.Direction;
import utils.FileInput;
import view.GamePanel;

public class keyBoard extends KeyAdapter{
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
		if(e.getKeyCode() == KeyEvent.VK_X) {
			gameBoard.setDead(true);
			gameBoard.setGameOn(false);
			gamePanel.repaint();
			FileInput.writeBoard(gameBoard);
//			System.exit(0);
		}
		if(e.getKeyCode()== KeyEvent.VK_P) {
			if(gameBoard.isGameOn()) 
				{
				gameBoard.setGameOn(false);
				FileInput.writeBoard(gameBoard);
				}
			else {
				gameBoard.setGameOn(true);
				FileInput.readBoard();
			}
			gamePanel.repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_S) {
			gameBoard.Music();
		}
		if(e.getKeyCode()==KeyEvent.VK_Z) {
			gameBoard.undo();
			gamePanel.repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_Y) {
			gameBoard.redo();
			gamePanel.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
