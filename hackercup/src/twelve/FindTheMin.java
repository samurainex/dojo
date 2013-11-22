package twelve;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FindTheMin {
	public static void main(String[] args) {
		Scanner scanner = null;
		Writer out = null;

		try {
			scanner = new Scanner(new File("findmin.txt"));
			out = new OutputStreamWriter(new FileOutputStream(
					"findmin-result.txt"));
			int numOfCase = scanner.nextInt();

			for (int i = 1; i <= numOfCase; i++) {
				scanner.nextLine();

				long n = scanner.nextInt();
				long k = scanner.nextInt();

				scanner.nextLine();

				long a = scanner.nextInt();
				long b = scanner.nextInt();
				long c = scanner.nextInt();
				long r = scanner.nextInt();

//				System.out.println("n:" + n + " k:" + k + "\na:" + a + " b:"
//						+ b + " c:" + c + " r:" + r);

				ArrayList<Long> list = new ArrayList<Long>();
				list.add(a);

				long mK;
				for (int j = 1; j < k; j++) {
					mK = (b * list.get(j-1) + c) % r;
					list.add(mK);
				}

//				for (Integer integer : list) {
//					System.out.print(integer + " ");
//				}
//				System.out.println();

				ArrayList<Long> allList = new ArrayList<Long>();
				allList.addAll(list);

				long checkpoint = k;
				long mVal = 0;
				while (checkpoint < n) {
					if (!list.contains(mVal)) {
						list.add(mVal);
						list.remove(0);
						allList.add(mVal);
						mVal = 0;
						checkpoint++;
						if ((n % checkpoint == 0)
								&& (Collections.max(list) == (k - 1))
								&& (Collections.min(list) == 0))
							break;
					} else {
						mVal++;
					}
				}

//				for (Integer integer : allList) {
//					System.out.print(integer + " ");
//				}
//				System.out.println();
				System.out.println("Case #" + i + ": " + list.get((int)k - 1));

				out.write("Case #" + i + ": " + list.get((int)k - 1));
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
