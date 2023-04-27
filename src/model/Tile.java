package model;

import java.awt.Color;
import java.awt.Font;

public class Tile {
	private int value;
	private int row;
	private int col;
	public Tile() {
	}

	public Tile(int value, int row, int col) {
		this.value = value;
		this.row = row;
		this.col = col;
	}



	public int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}

	public boolean isEmpty() {
		if (getValue() == 0)
			return true;
		return false;
	}

	public Color setBackGround() {
		Color background = new Color(0);
		if (value == 2) {
			background = new Color(0xFD8A8A);
		} else if (value == 4) {
			background = new Color(0xF1F7B5);
		} else if (value == 8) {
			background = new Color(0xA8D1D1);
		} else if (value == 16) {
			background = new Color(0x9EA1D4);
		} else if (value == 32) {
			background = new Color(0xFEA1BF);
		} else if (value == 64) {
			background = new Color(0xE5BA73);
		} else if (value == 128) {
			background = new Color(0xCCD6A6);
		} else if (value == 256) {
			background = new Color(0xBA94D1);
		} else if (value == 512) {
			background = new Color(0x65647C);
		} else if (value == 1024) {
			background = new Color(0x4e09b);
		} else if (value == 2048) {
			background = new Color(0x252A34);
		} else {
			background = new Color(0x3C6255);
		}
		return background;
	}
	public Color setText() {
		Color text = new Color(0);
		if (value == 2) {
			text = new Color(0x000000);
		} else if (value == 4) {
			text = new Color(0x000000);
		} else if (value == 8) {
			text = new Color(0xffffff);
		} else if (value == 16) {
			text = new Color(0xffffff);
		} else if (value == 32) {
			text = new Color(0xffffff);
		} else if (value == 64) {
			text = new Color(0xffffff);
		} else if (value == 128) {
			text = new Color(0xffffff);
		} else if (value == 256) {
			text = new Color(0xffffff);
		} else if (value == 512) {
			text = new Color(0xffffff);
		} else if (value == 1024) {
			text = new Color(0xffffff);
		} else if (value == 2048) {
			text = new Color(0xffffff);
		}else {
			text = new Color(0x3C6255);
		}
		return text;
	}
	public Font setFont() {
		Font font = new Font("Bebas Neue Regular", Font.PLAIN, 28);
		if (value <= 64) {
			font = font.deriveFont(45f);
		} else {
			font = font.deriveFont(35f);
		}
		return font;
	}



	public int getRow() {
		return row;
	}



	public void setRow(int row) {
		this.row = row;
	}



	public int getCol() {
		return col;
	}



	public void setCol(int col) {
		this.col = col;
	}
}
