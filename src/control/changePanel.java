package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Board;
import test.Game2048;
import utils.FileInput;
import view.GamePanel;
import view.Title;

public class changePanel implements ActionListener {
	private Game2048 jf;
//	private JFrame jf;
	private GamePanel jpanel1;
	private Title jpanel2;

	public changePanel(Game2048 jf, GamePanel jpanel1, Title jpanel2) {
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
		} else if (button.equals("CONTINUE")) {
			jpanel1 = new GamePanel(new Board(FileInput.readBoard(), FileInput.score));
			jf.movePanels(jpanel2, jpanel1);
		} else if (button.equals("PAUSE")) {
			jf.movePanels(jpanel1, jpanel2);
		}

	}

}
