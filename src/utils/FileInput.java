package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import model.Board;

public class FileInput {
	public static String saveDataPath = System.getProperty("user.dir") + File.separator + "bin" + File.separator
			+ "Save" + File.separator;

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
//in bang dang choi ra file
}