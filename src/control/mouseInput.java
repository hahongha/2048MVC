package control;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import view.GamePanel;
import view.Title;

public class mouseInput implements MouseListener{
	private JFrame window;
	private Title title;
	private GamePanel game;
	
	public mouseInput(JFrame main, Title title, GamePanel game) {
		super();
		this.window = main;
		this.title = title;
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(game.getGameBoard().isGameOn()) {
			if(mx>=0&&mx<=50) {
				if(my>=0&&my<=50) {
					window.remove(game);
					window.add(title, BorderLayout.CENTER);
					title.requestFocusInWindow();
					window.validate();
				}
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
