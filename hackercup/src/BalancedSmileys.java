import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class BalancedSmileys {
	public static void main(String[] args) {

		final String YES = "YES";
		final String NO = "NO";

		Scanner scanner = null;
		Writer out = null;

		try {
			scanner = new Scanner(new File("smileys.txt"));
			out = new OutputStreamWriter(new FileOutputStream(
					"smileys-result.txt"));
			int numOfCase = scanner.nextInt();
			scanner.nextLine();

			for (int i = 1; i <= numOfCase; i++) {
				String balancea = YES;
				String line = scanner.nextLine();

				line = line.replace("(:)", "");
				line = line.replace(":(", "");
				line = line.replace(":)", "");

				ArrayList<Integer> openList = new ArrayList<Integer>();
				ArrayList<Integer> closeList = new ArrayList<Integer>();

				int nextOpenIndex = 0;
				int nextCloseIndex = 0;
				int openIdx = 0;
				int closeIdx = 0;

				if (line.contains("(") || line.contains(")")) {
					openIdx = line.indexOf("(", nextOpenIndex);
					if (openIdx != -1) {
						openList.add(openIdx);
						nextOpenIndex = openIdx + 1;
					}

					closeIdx = line.indexOf(")", nextCloseIndex);
					if (closeIdx != -1) {
						closeList.add(closeIdx);
						nextCloseIndex = closeIdx + 1;
					}
				}

				while ((line.contains("(") && nextOpenIndex <= line.lastIndexOf("("))
						|| (line.contains(")") && nextCloseIndex <= line.lastIndexOf(")"))) {
					openIdx = line.indexOf("(", nextOpenIndex);
					if (openIdx != -1) {
						openList.add(openIdx);
						nextOpenIndex = openIdx + 1;
					}

					closeIdx = line.indexOf(")", nextCloseIndex);
					if (closeIdx != -1) {
						closeList.add(closeIdx);
						nextCloseIndex = closeIdx + 1;
					}
				}

//				for (Integer integer : openList) {
//					System.out.print(integer + ",");
//				}
//				System.out.println();
//
//				for (Integer integer : closeList) {
//					System.out.print(integer + ",");
//				}
//				System.out.println();

				if (openList.size() != closeList.size()) {
					balancea = NO;
				} else {
					for (int j = 0; j < openList.size(); j++) {
						if (openList.get(j) > closeList.get(j)) {
							balancea = NO;
						}
					}
				}

//				System.out.println("Case #" + i + ": " + balancea);
				out.write("Case #" + i + ": " + balancea);
				if (i != numOfCase) {
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
