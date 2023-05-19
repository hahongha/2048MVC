package model;

import java.util.Random;
import java.util.Stack;

import javax.sound.sampled.Clip;

import utils.FileInput;
//di chuyen 3 o
//them luckyTile
//theo cac huong di cheo
//count
//thay doi cach ghep so lien nhau
public class Board {
	protected Tile[][] board;
	protected int score = 0;
	protected int highScore = FileInput.highScore();
	protected boolean win = false;
	protected boolean dead = false;
	public static final int ROWS = 4;
	public static final int COLS = 4;
	protected Sound sound;
	protected boolean gameOn = true;
	private Stack<Tile[][]> boardHistory = new Stack<>();
	private Stack<Tile[][]> redoBoard = new Stack<>();
	private Stack <Integer> ScoreHistory = new Stack<Integer>();
	private Stack <Integer> redoScore = new Stack<Integer>();
	int count=0;


	public Board() {
		board = new Tile[ROWS][COLS];
		sound = new Sound();
		addTile();
		addTile();
	}

//them ngau nhien
	private void addTile() {
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
	private void addTile2(int value, Tile[][]newBoard,boolean lucky) {
		Random random = new Random();
		boolean notValid = true;

		while (notValid) {// kiem tra o trong
			// chuyen doi tu 1 chieu sang 2 chieu
			int location = random.nextInt(ROWS * COLS);
			int row = location / COLS;
			int col = location % ROWS;
			Tile current = newBoard[row][col];
			if (current == null) {// kiểm tra vị trí hiện tại có ô nào không
				Tile tile = new Tile(value, row, col,lucky);
				newBoard[row][col] = tile;
				notValid = false;
			}
		}
	}

	//them o may man
	private void addLucky() {
		Random random = new Random();
		boolean notValid = true;

		while (notValid) {// kiem tra o ton tai
			// chuyen doi tu 1 chieu sang 2 chieu
			int location = random.nextInt(ROWS * COLS);
			int row = location / COLS;
			int col = location % ROWS;
			Tile current = board[row][col];
			if (current != null) {// kiểm tra vị trí hiện tại có ô nào không
				current.setLucky(true);
				notValid= false;
			}
		}
	}
	//xao tron bang
	public void mixTile() {
		Tile[][] newBoard = new Tile[ROWS][COLS];
		for(int i =0; i<ROWS; i++) {
			for(int j =0; j<COLS; j++) {
				if(board[i][j]!=null) {
					int value = board[i][j].getValue();
					boolean lucky =board[i][j].isLucky();
					addTile2(value, newBoard,lucky);
				}
			}
		}
		board= newBoard;
	}
	//kiem tra xem co o nao l o may man khong neu co thi xoa o may man di
	private void checkLucky() {
		for(int i =0; i<ROWS; i++) {
			for(int j =0; j<COLS; j++) {
				Tile current = board[i][j];
				if(current!= null && current.isLucky()) {
					current.setLucky(false);
				}
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
			if (board[newRow][newCol] == null||board[newRow][newCol].getValue()==0) {
				board[newRow][newCol] = current;
				board[newRow - verticalDirection][newCol - horizontalDirection] = null;// xóa các ô đã trượt qua
				canMove = true;
			} else if (board[newRow][newCol].getValue() == current.getValue()) {
				int value = board[newRow][newCol].getValue() + current.getValue();
				if(board[newRow][newCol].isLucky()||current.isLucky()) {
					value = board[newRow][newCol].getValue() + current.getValue();
					board[newRow][newCol] = new Tile(value, newRow, newCol);
				}
				else board[newRow][newCol] = new Tile(value, newRow, newCol);
				board[newRow - verticalDirection][newCol - horizontalDirection] = null;
				canMove = true;
				// add to score
				score += board[newRow][newCol].getValue();
				//3 o cong lai
//			} else if (board[newRow][newCol].getValue() == current.getValue()) {
//				int nCol = col-horizontalDirection;
//				int nRow = row- verticalDirection ;
//				try {
//				if (board[nRow][nCol]!=null&&board[nRow][nCol].getValue() == current.getValue()) {
//				board[newRow][newCol] = new Tile(board[newRow][newCol].getValue() * 3, newRow, newCol);
//				board[newRow - verticalDirection][newCol - horizontalDirection] = null;
//				board[nRow][nCol] = null;
//				canMove = true;
//				// add to score
//				score += board[newRow][newCol].getValue();
//				}else {
//					board[newRow][newCol] = new Tile(board[newRow][newCol].getValue() * 2, newRow, newCol);
//					board[newRow - verticalDirection][newCol - horizontalDirection] = null;
//					canMove = true;
//					// add to score
//					score += board[newRow][newCol].getValue();
//				}
//				}catch(ArrayIndexOutOfBoundsException e) {
//					continue;
//				}
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
			return col > COLS -1;
		} else if (dir == Direction.UP) {
			return row < 0;
		} else if (dir == Direction.DOWN) {
			return row > ROWS - 1;
		}else if(dir==Direction.cheo7) {
			return col<0||row<0;
		}else if(dir==Direction.cheo9) {
			return col > COLS -1||row<0;
		}else if(dir==Direction.cheo1) {
			return col<0||row > ROWS - 1;
		}else if(dir==Direction.cheo3) {
			return col > COLS -1||row > ROWS - 1;
		}
		return false;
	}

//dich chuyen
	public void moveTiles(Direction dir) {
		count++;
		if(count>0&&count%7==0) addLucky();
		if(count>0&&count%5==0) checkLucky();
		boolean canMove = false;// bien co the di chuyen
		saveBoard(boardHistory, ScoreHistory);
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
		}else if (dir == Direction.cheo7) {
			verticalDirection = -1;
			horizontalDirection=-1;
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove)
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					else
						move(row, col, horizontalDirection, verticalDirection, dir);
				}
			}
		}else if (dir == Direction.cheo9) {
			verticalDirection = -1;
			horizontalDirection=1;
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove)
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					else
						move(row, col, horizontalDirection, verticalDirection, dir);
				}
			}
		}else if (dir == Direction.cheo1) {
			verticalDirection = 1;
			horizontalDirection=-1;
			for (int row = 0; row < ROWS; row++) {
				for (int col = 0; col < COLS; col++) {
					if (!canMove)
						canMove = move(row, col, horizontalDirection, verticalDirection, dir);
					else
						move(row, col, horizontalDirection, verticalDirection, dir);
				}
			}
		}else if (dir == Direction.cheo3) {
			verticalDirection = 1;
			horizontalDirection=1;
			for (int row = 0; row < ROWS; row++) {
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
//				if(count>10) {
//					dead = true;
//					gameOn = false;
//					checkScore();
//				}
//				System.out.println("count:"+count);
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
		System.out.println();
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (board[row][col] == null||board[row][col].getValue()==0)// nếu còn ô trông là chưa chết
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
	private void saveBoard(Stack<Tile[][]> add, Stack<Integer> add2) {
		Tile[][] prevBoard = new Tile[ROWS][COLS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				prevBoard[i][j] = board[i][j];
			}
		}
		add.push(prevBoard);
		add2.push(score);
	}
	public void undo() {
		if (!boardHistory.isEmpty()) { // kiểm tra xem stack lịch sử còn trống không
//				saveBoard(redoBoard);
			redoBoard.push(board);
			board = boardHistory.pop();
			redoScore.push(score);
			score= ScoreHistory.pop();
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
			ScoreHistory.push(score);
			score= redoScore.pop();
			
			// lấy trạng thái trước đó từ stack và khôi phục lại trạng thái của bàn chơi
		}
		if (!gameOn)
			gameOn = true;
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

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}
}