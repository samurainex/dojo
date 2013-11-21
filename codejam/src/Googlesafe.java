import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Googlesafe {
	public static void main (String[] args) {
		
		Scanner scanner = null;
		Writer out = null;
		
		try {
			scanner = new Scanner(new File("googlesafe.txt"));
			out = new OutputStreamWriter(new FileOutputStream("googlesafe-result.txt"));
			int numOfCase = scanner.nextInt();
			scanner.nextLine();

			for (int i = 1; i <= numOfCase; i++) {
				String[] line = scanner.nextLine().split(" ");
				Integer[] lineInt = new Integer[line.length];
				for (int j = 0; j < line.length; j++) {
					lineInt[j] = Integer.valueOf(line[j]);
				}
				int contNum = lineInt[0];
				
				Integer[] contPoint = new Integer[lineInt.length - 1];
				int totalPoint = 0;
				for (int j = 0; j < contNum; j++) {
					contPoint[j] = lineInt[j + 1];
					totalPoint += contPoint[j];
				}
				
				Double[] contVote = new Double[lineInt.length];
				for (int j = 0; j < contNum; j++) {
					double point = contPoint[j];
					double total = totalPoint;
					contVote[j] = ((total - point)/total * 100);
				}
				
				String resultString = "";
				for (int j = 0; j < contVote.length - 1; j++) {
					resultString = resultString + String.valueOf(contVote[j]) + " ";
				}
//				Ji+X*Yi
				
				System.out.println("Case #" + i + ": " + resultString);
				out.write("Case #" + i + ": " + resultString);
				
				if (i != numOfCase) {
					out.write("\n");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
