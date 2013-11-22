package twelve;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class BeautifulStrings {
	public static void main(String[] args) {
		Scanner scanner = null;
		Writer out = null;

		try {
			scanner = new Scanner(new File("beautiful.txt"));
			out = new OutputStreamWriter(new FileOutputStream(
					"beautiful-result.txt"));
			int numOfCase = scanner.nextInt();
			scanner.nextLine();

			for (int i = 1; i <= numOfCase; i++) {
				String line = scanner.nextLine();

				HashMap<String, Integer> map = new HashMap<String, Integer>();
				for (int j = 0; j < line.length(); j++) {
					char cur = line.charAt(j);
					if (cur >= 65 && cur <= 122) {
						if (cur >= 97) {
							cur -= 32;
						}

						String curs = String.valueOf(cur);
						if (map.containsKey(curs)) {
							int total = map.get(curs);
							total += 1;
							map.put(curs, total);
						} else {
							map.put(curs, 1);
						}
					}
				}

				// Collection<String> chars = map.keySet();
				// for (String string : chars) {
				// System.out.println(string + " " + map.get(string));
				// }

				Collection<Integer> ints = map.values();
				ArrayList<Integer> list = new ArrayList<Integer>(ints);
				Collections.sort(list);
				Collections.reverse(list);

				int multiplier = 26;
				int max = 0;

				for (Integer integer : list) {
					max += multiplier-- * integer;
				}

				// System.out.println("Case #" + i + ": " + max);
				out.write("Case #" + i + ": " + max);
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
