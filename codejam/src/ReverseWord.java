

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

public class ReverseWord {
	public static void main (String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		Writer out = null;
		try {
			fr = new FileReader(args[0]);
			br = new BufferedReader(fr);
			out = new OutputStreamWriter(new FileOutputStream("/home/abuazzam/Dev/workspace/1rstwap/codejam/bin/result.txt"));
			String numOfLine = br.readLine();
			int line = 0;
			if (numOfLine != null) {
				line = Integer.valueOf(numOfLine);
			} else {
				return;
			}

			String lineData;
			String[] wordsArray;

			for (int i = 1; i <= line; i++) {
				String reversedLine = "";
				lineData = br.readLine();
				wordsArray = lineData.split(" ");
				int numOfWords = wordsArray.length;
				if (numOfWords != 0) {
					for (int j = 0; j < numOfWords ; j++) {
						reversedLine = reversedLine + wordsArray[numOfWords - j - 1] + " ";
					}
				};
				reversedLine = "Case #" + i + ": " + reversedLine.trim();
				out.write(reversedLine);
				if (i != line) {
					out.write("\n");
				}
				System.out.println(reversedLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
				out.close();
			} catch(Exception e) {}
		}
	}
}
