package model;

import java.util.Random;
import java.util.Stack;

import javax.sound.sampled.Clip;

import utils.FileInput;

public class Board {
	protected Tile[][] board;
	protected int score = 0;
	protected int highScore = FileInput.highScore();
	protected boolean win = false;
	public boolean dead = false;
	public static final int ROWS = 4;
	public static final int COLS = 4;
	protected Sound sound;
	protected int count = 0;
	protected boolean gameOn = true;
	private Stack<Tile[][]> boardHistory = new Stack<>();
	private Stack<Tile[][]> redoBoard = new Stack<>();

	public Board() {
		board = new Tile[ROWS][COLS];
		sound = new Sound();
		addTile();
		addTile();
	}

	public Board(Tile[][] b, int score) {
		this.board = b;
		this.score = score;
		sound = new Sound();
		addTile();
		addTile();
		gameOn = true;
	}

//them ngau nhien
	protected void addTile() {
		Random random = new Random();
		boolean notValid = true;

		while (notValid) {// kiem tra o trong
			// chuyen doi tu 1 chieu sang 2 chieu
			int location = random.nextInt(ROWS * COLS);
			int row = location / COLS;
			int col = location % ROWS;
			Tile current = board[row][col];
			if (current == null) {// kiểm tra vị trí hiện tại có ô nào không
				int value = random.nextInt(10) < 8 ? 16 : 128;
				Tile tile = new Tile(value, row, col);
				board[row][col] = tile;
				notValid = false;
			}
		}
	}

//kiem tra xem cac o co di chuyen duoc khong
	private boolean move(int row, int col, int horizontalDirection, int verticalDirection, Direction dir) {
		boolean canMove = false;
		// lay o hien tai
		Tile current = board[row][col];
		if (current == null)// neu o hien tai khoong thoat khoi ham
			return false;
		boolean move = true;
		int newCol = col;
		int newRow = row;
		while (move) {
			newCol += horizontalDirection;// cot se truot theo chieu ngang
			newRow += verticalDirection; // cot se truot theo chieu doc
			if (checkOutOfBounds(dir, newRow, newCol))// kiem tra truot den vi tri hop le chua(den vach ngan chua)
				break;
			if (board[newRow][newCol] == null) {
				board[newRow][newCol] = current;
				board[newRow - verticalDirection][newCol - horizontalDirection] = null;// xóa các ô đã trượt qua
				canMove = true;
			} else if (board[newRow][newCol].getValue() == current.getValue()) {
				board[newRow][newCol] = new Tile(board[newRow][newCol].getValue() * 2, newRow, newCol);
				board[newRow - verticalDirection][newCol - horizontalDirection] = null;
				canMove = true;
				// add to score
				score += board[newRow][newCol].getValue();
			} else {
				move = false;
			}
		}

		return canMove;
	}

	// kiem tra truot den vi tri hop le chua(den vach ngan chua)
	private boolean checkOutOfBounds(Direction dir, int row, int col) {
		if (dir == Direction.LEFT) {
			return col < 0;
		} else if (dir == Direction.RIGHT) {
			return col > COLS - 1;
		} else if (dir == Direction.UP) {
			return row < 0;
		} else if (dir == Direction.DOWN) {
			return row > ROWS - 1;
		}
		return false;
	}

//dich chuyen
	public void moveTiles(Direction dir) {
		boolean canMove = false;// bien co the di chuyen
		saveBoard(boardHistory);
		int horizontalDirection = 0;// huong di chuyen theo chieu ngang
		int verticalDirection = 0;// huong di chuyen theo chieu doc
		sound.play("ting.wav", 0);
		if (dir == Direction.LEFT) {
			horizontalDirection = -1;
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove)
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					else
						move(row, col, horizontalDirection, verticalDirection, dir);
				}
			}
		} else if (dir == Direction.RIGHT) {
			horizontalDirection = 1;
			for (int row = 0; row < ROWS; row++) {
				for (int col = COLS - 1; col >= 0; col--) {
					if (!canMove) {
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
						// kiểm tra xem các ô có di chuyển được hay không
					} else
						move(row, col, horizontalDirection, verticalDirection, dir);
				}
			}
		} else if (dir == Direction.UP) {
			verticalDirection = -1;
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove)
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					else
						move(row, col, horizontalDirection, verticalDirection, dir);
				}
			}
		} else if (dir == Direction.DOWN) {
			verticalDirection = 1;
			for (int row = ROWS - 1; row >= 0; row--) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove)
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					else
						move(row, col, horizontalDirection, verticalDirection, dir);
				}
			}
		}
		// neu con co the di chuyen thi tao ra o moi bat ki
		if (canMove) {
			if (checkWin()) {
				win = true;
				gameOn = false;
				checkScore();
			} else {
				addTile();
//			addTile();
			}
		} else {
			if (checkDead()) {
				dead = true;
				gameOn = false;
				checkScore();
			}
		}

	}

	private boolean checkDead() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (board[row][col] == null)// nếu còn ô trông là chưa chế
					return false;
				if (checkSurroundingTiles(row, col, board[row][col])) {
					return false;
				}
			}
		}
		sound.stop("piano.wav");
		sound.play("lose.wav", 0);
		return true;
	}

//kiem tra xem xung quanh co di chuyen duoc hay khong
	private boolean checkSurroundingTiles(int row, int col, Tile tile) {
		Tile current = board[row][col];
		if (row > 0) {
			Tile check = board[row - 1][col];
			if (check == null)
				return true;
			if (current.getValue() == check.getValue())
				return true;
		}

		if (row < ROWS - 1) {
			Tile check = board[row + 1][col];
			if (check == null)
				return true;
			if (current.getValue() == check.getValue())
				return true;
		}

		if (col > 0) {
			Tile check = board[row][col - 1];
			if (check == null)
				return true;
			if (current.getValue() == check.getValue())
				return true;
		}

		if (col < COLS - 1) {
			Tile check = board[row][col + 1];
			if (check == null)
				return true;
			if (current.getValue() == check.getValue())
				return true;
		}
		return false;
	}

	public Tile[][] getBoard() {
		return board;
	}

	public void setBoard(Tile[][] board) {
		this.board = board;
	}

	private boolean checkWin() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (board[i][j] == null)
					continue;
				else if (board[i][j].getValue() == 2048) {
					sound.stop("piano.wav");
					sound.play("victory.wav", 0);
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkScore() {
		if (score > highScore) {
			highScore = score;
			FileInput.setHighScore(highScore);
			return true;
		}
		return false;
	}

	public void reset() {
		board = new Tile[ROWS][COLS];
		score = 0;
		sound.play("piano.wav", Clip.LOOP_CONTINUOUSLY);
		addTile();
		addTile();
	}

	public void show() {
		System.out.println("-----------" + score + "-------------");
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (board[i][j] != null)
					System.out.print(board[i][j].getValue() + "  ");
				else
					System.out.print("0    ");
			}
			System.out.println();
		}
	}

//	public static void main(String[] args) {
//		String dir = "";
//		Scanner sc = new Scanner(System.in);
//		Board game = new Board(FileInput.readBoard(), FileInput.score);
//		while (dir != "x") {
//			game.show();
//			dir = sc.nextLine();
//			switch (dir) {
//			case "4":
//				game.moveTiles(Direction.LEFT);
//				break;
//			case "6":
//				game.moveTiles(Direction.RIGHT);
//
//				break;
//			case "8":
//				game.moveTiles(Direction.UP);
//
//				break;
//			case "2":
//				game.moveTiles(Direction.DOWN);
//
//				break;
//			case "n":
//				game.reset();
//				break;
//			case "s":
//				FileInput.writeBoard(game);
//				break;
//			default:
//				break;
//			}
//		}
//		sc.close();
//	}
	private void saveBoard(Stack<Tile[][]> add) {
		Tile[][] prevBoard = new Tile[ROWS][COLS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				prevBoard[i][j] = board[i][j];
			}
		}
		add.push(prevBoard);
	}
	public void undo() {
		if (!boardHistory.isEmpty()) { // kiểm tra xem stack lịch sử còn trống không
//				saveBoard(redoBoard);
			redoBoard.push(board);
			board = boardHistory.pop();
			// lấy trạng thái trước đó từ stack và khôi phục lại trạng thái của bàn chơi
		} else {
			board = new Tile[ROWS][COLS];
			addTile();
			addTile();
		}
		if (!gameOn)
			gameOn = true;
	}
	public void redo() {
		if (!redoBoard.isEmpty()) { // kiểm tra xem stack lịch sử còn trống không
			boardHistory.push(board);
			board = redoBoard.pop();
			// lấy trạng thái trước đó từ stack và khôi phục lại trạng thái của bàn chơi
		}
		if (!gameOn)
			gameOn = true;
	}
	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isGameOn() {
		return gameOn;
	}

	public void setGameOn(boolean gameOn) {
		this.gameOn = gameOn;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public void Music() {
		sound.change("piano.wav");
	}
	/*
	0    0    0    16  
	0    16  32  256  
	0    0    128  64  
	128  16  64  16
	 */
}