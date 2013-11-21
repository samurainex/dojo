import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Googlepass {

	public static void main(String[] args) {
		Scanner scanner = null;
		Writer out = null;

		try {
			scanner = new Scanner(new File("googlepass.txt"));
			out = new OutputStreamWriter(new FileOutputStream(
					"googlepass-result.txt"));
			int numOfCase = scanner.nextInt();

			for (int i = 1; i <= numOfCase; i++) {
				String[] charLine = scanner.nextLine().split(" ");
				int charTyped = Integer.valueOf(charLine[0]);
				int charTotal = Integer.valueOf(charLine[1]);

				String[] probLine = scanner.nextLine().split(" ");
				Double[] probTyped = null;
				for (int j = 0; j < probLine.length; j++) {
					probTyped[j] = Double.valueOf(probLine[j]);
				}

				// keep typing | backspace i | enter right away
				List<Double> expected = new ArrayList<Double>();

				expected.add(charTotal + 2.0); // enter right away

				// keep typing
				Double probTotal = 0.0;
				for (int k = 0; k < probTyped.length; k++) {
					probTotal += probTyped[i];
				}

				if (i != numOfCase) {
					out.write("\n");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
