package com.lightcraftmc.database.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UtilFile {

	public static void save(String category, String key, String value) {
		String path = "data\\" + category.toLowerCase()+ "\\" + key + ".txt";
		File file = new File(path);
		file.getParentFile().mkdirs();
        if (!file.exists()){
            try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
		try {
			PrintWriter out = new PrintWriter(file.getAbsolutePath());
			out.print(value.replace("\n", ">>NEWLN"));
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String load(String category, String key) {
		String path = "data\\" + category.toLowerCase() + "\\" + key + ".txt";
		File file = new File(path);
		String fileName = file.getAbsolutePath();
		String line = "";
		
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				bufferedReader.close();
				return line;
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		return line;
	}

}
