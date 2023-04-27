package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.Clip;
import javax.swing.JPanel;

import model.Board;
import test.Game2048;
import utils.FileInput;
import view.GamePanel;

public class changePanel implements ActionListener {
	private Game2048 jf;
//	private JFrame jf;
	private GamePanel jpanel1;
	private JPanel jpanel2;

	public changePanel(Game2048 jf, GamePanel jpanel1, JPanel jpanel2) {
		super();
		this.jf = jf;
		this.jpanel1 = jpanel1;
		this.jpanel2 = jpanel2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();
		if (button.equals("PLAY")) {
			jf.movePanels(jpanel2, jpanel1);
			jpanel1.getGameBoard().getSound().play("piano.wav", Clip.LOOP_CONTINUOUSLY);
		} else if (button.equals("CONTINUE")) {
			Board board = new Board();
			board.setBoard(FileInput.readBoard());
			board.setScore(FileInput.score);
			jpanel1 = new GamePanel(board);
			jf.movePanels(jpanel2, jpanel1);
			jpanel1.getGameBoard().getSound().play("piano.wav", Clip.LOOP_CONTINUOUSLY);
		}
	}

}
