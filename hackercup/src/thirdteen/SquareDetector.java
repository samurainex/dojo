package thirdteen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class SquareDetector {
	public static void main(String[] args) {
		Scanner scanner = null;
		Writer out = null;

		try {
			scanner = new Scanner(new File("square_detector.txt"));
			out = new OutputStreamWriter(new FileOutputStream(
					"square-result.txt"));
			int tCase = scanner.nextInt();
			scanner.nextLine();


			for (int i = 1; i <= tCase; i++) {
				int dimen = scanner.nextInt();
				scanner.nextLine();

				String result = "NO";

				if (dimen == 1) {
					String line = scanner.nextLine();
					if (line.equals("#")) {
						result = "YES";
					}
					out.write("Case #" + i + ": " + result);
					if (i != tCase) {
						out.write("\n");
					}
					continue;
				}

				char[][] neo = new char[dimen][dimen];
				for (int j = 0; j < neo.length; j++) {
					String line = scanner.nextLine();
					neo[j] = line.toCharArray();
				}

				ArrayList<ArrayList<Integer>> blackList = new ArrayList<ArrayList<Integer>>();

				for (int j = 0; j < neo.length; j++) {
					ArrayList<Integer> rowList = new ArrayList<Integer>();
					for (int k = 0; k < neo.length; k++) {
						if (neo[j][k] == '#') {
							rowList.add(k);
						}
					}
					blackList.add(rowList);
				}

				int rowCount = 0;
				int rowMatch = 0;
				int rowLength = 0;
				ArrayList<Integer> prevArrayList = new ArrayList<Integer>();
				for (ArrayList<Integer> arrayList : blackList) {
					boolean rowFound = false;

					if (arrayList.size() == 0) {
						continue;
					}

					for (Integer integer : arrayList) {
						if (!rowFound) {
							rowFound = true;
							rowCount++;
							rowLength = arrayList.size();
						}
					}
					if (rowCount == 1) {
						prevArrayList = arrayList;
					} else if (rowCount > 1 &&
							prevArrayList.toString().equals(arrayList.toString())) {
						prevArrayList = arrayList;
						rowMatch++;

						if (rowLength == rowMatch + 1) {
							result = "YES";
						}
					} else {
						rowCount = 0;
						rowMatch = 0;
						result = "NO";
					}
				}

				out.write("Case #" + i + ": " + result);
				if (i != tCase) {
					out.write("\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				scanner.close();
				out.close();
			} catch (Exception e) {
			}
		}
	}
}
