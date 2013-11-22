package twelve;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class CardGame {
	public static void main(String[] args) {
		Scanner scanner = null;
		Writer out = null;

		try {
			scanner = new Scanner(new File("card.txt"));
			out = new OutputStreamWriter(new FileOutputStream(
					"card-result.txt"));
			int numOfCase = scanner.nextInt();

			for (int i = 1; i <= numOfCase; i++) {
				scanner.nextLine();

				int total = 0;
				int n = scanner.nextInt();
				int k = scanner.nextInt();

				scanner.nextLine();

				int[] a = new int[n];

				scanner.nextLine();

				for (int j = 0; j < a.length; j++) {
					a[j] = scanner.nextInt();
				}

				for (int m = 0; m < n; m++) {
					for (int o = 0; o < k; o++) {

					}
				}

			}

		} catch (Exception ex) {

		} finally {
			scanner.close();
			try {
				out.close();
			} catch (Exception ex) {

			}
		}
	}
}
