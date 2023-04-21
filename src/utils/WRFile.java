package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Board;

public class WRFile {
	public static String saveDataPath = System.getProperty("user.dir") + File.separator + "bin" + File.separator
			+ "input" + File.separator;

	// doc list diem cao nhat
	public static List<Integer> highScores() throws IOException {
		List<Integer> highScores = new ArrayList<Integer>();// oblect khong dc dung lam doi so chung
		FileReader input = new FileReader(saveDataPath + "score.txt");
		BufferedReader reader = new BufferedReader(input);
		while (reader.readLine() != null) {
			int a;
			try {
				a = Integer.parseInt(reader.readLine());
				highScores.add(a);
				reader.readLine();
			} catch (NumberFormatException e) {
				highScores.add(0);
			}
			continue;
		}
		try {
			reader.close();
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return highScores;
	}

	//luu 5 diem co he so cao nhat
	public static void setHighScore(List<Integer> list) {
		FileWriter output = null;
		try {
			output = new FileWriter(saveDataPath + "score.txt");
			BufferedWriter writer = new BufferedWriter(output);
			for (int i = 0; i < 4; i++) {
				writer.write("" + list.get(i));
				writer.newLine();
			}
			writer.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// doc bang de tiep tuc choi
	/*
	public static int[][] readBoard(){
		int [][]saveBoard = new int[Board.ROWS][Board.COLS];
		FileReader input;
		try {
			input = new FileReader(saveDataPath + "saveBoard.txt");
			BufferedReader reader = new BufferedReader(input);
			for (int i = 0; i < Board.ROWS; i++) {
				String b = null;
//				String []c =  null;
				try {
					b = reader.readLine();
					String []c= b.split("|");
				} catch (IOException e1) {
					String []c= null;
				}
				for (int j = 0; j < Board.COLS; j++) {
					try {
						saveBoard[i][j] = Integer.parseInt(c[j]);
					} catch (NumberFormatException e) {
						saveBoard[i][j] = 0;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saveBoard;
	}

*/
}