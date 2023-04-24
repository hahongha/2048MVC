package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Board;
import model.Tile;

public class FileInput {
	public static String saveDataPath = System.getProperty("user.dir") + File.separator + "bin" + File.separator
			+ "Save" + File.separator;
	public static int score;

//doc diem cao nhat tu file
	public static int highScore() {
		int highScore = 0;
		FileReader input = null;
		BufferedReader reader = null;
		try {
			input = new FileReader(saveDataPath + "score.txt");
			reader = new BufferedReader(input);
			try {
				highScore = Integer.parseInt(reader.readLine());
			} catch (NumberFormatException e) {
				highScore = 0;
			} catch (IOException e) {
				highScore = 0;
			}
		} catch (FileNotFoundException e) {
			highScore = 0;
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (input != null)
					input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return highScore;
	}

//in diem cao nhat ra file
	public static void setHighScore(int highScore) {
		FileWriter output = null;
		BufferedWriter w = null;
		try {
			output = new FileWriter(saveDataPath + "score.txt");
			w = new BufferedWriter(output);
			w.write("" + highScore);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (w != null)
					w.close();
				if (output != null)
					output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

//doc bang tu file
		public static Tile[][] readBoard(){
			Tile[][]tiles = new Tile[Board.ROWS][Board.COLS];
			FileReader input = null;
			BufferedReader reader = null;
			try {
				input = new FileReader(saveDataPath+"saveBoard.txt");
				reader = new BufferedReader(input);
				try {
					String[] b = null;
					String c = null;
					try {
						b = reader.readLine().split(" ");
					}catch (NullPointerException e) {
						// TODO: handle exception
					return new Tile[Board.ROWS][Board.COLS];
					}
					for (int i = 0; i <Board.ROWS* Board.COLS ; i++) {
						int row = i/Board.COLS;
						int col = i/Board.ROWS;
						
						try {
						int a = Integer.parseInt(b[i]);
							tiles[row][col] = new Tile(a, row, col);
						}catch (NumberFormatException e) {
							continue;
						}
					}
					
					try {
						c = reader.readLine();
						score = Integer.parseInt(c);
					}catch (NullPointerException e) {
						score= 0;
					}catch (NumberFormatException e) {
						score=0;
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
			return	tiles = new Tile[Board.ROWS][Board.COLS];
			}finally {
				try {
					if (reader != null)
						reader.close();
					if (input != null)
						input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			deleteFile();
			return tiles;
		}

////	
//in bang dang choi ra file
		public static void writeBoard(Board board) {
			FileWriter output = null;
			BufferedWriter w = null;
			try {
				output = new FileWriter(saveDataPath + "saveBoard.txt");
				w = new BufferedWriter(output);
				for (int i = 0; i < Board.ROWS; i++) {
					for (int j = 0; j < Board.COLS; j++) {
						if(board.getBoard()[i][j]!= null) {
							w.write(board.getBoard()[i][j].getValue()+" ");
						}
						else {
							w.write("0 ");
						}
					}
				}
				w.newLine();
				w.write(""+board.getScore());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (w != null)
						w.close();
					if (output != null)
						output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		private static void deleteFile() {
			File file = new File(saveDataPath + "saveBoard.txt");
			if(file.exists()) {
				if(file.delete()) System.out.println("da xoa file");
				else System.out.println("khong xoa dc");
			}else System.out.println("file khong ton tai");
		}
}