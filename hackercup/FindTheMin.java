import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class FindTheMin {
	public static void main(String[] args) {
		Scanner scanner = null;
		Writer out = null;

		try {
			scanner = new Scanner(new File("findmin-test.txt"));
			out = new OutputStreamWriter(new FileOutputStream(
					"findmin-result-test.txt"));
			int numOfCase = scanner.nextInt();

			for (int i = 1; i <= numOfCase; i++) {
				scanner.nextLine();

				int n = scanner.nextInt();
				int k = scanner.nextInt();

				scanner.nextLine();

				int a = scanner.nextInt();
				int b = scanner.nextInt();
				int c = scanner.nextInt();
				int r = scanner.nextInt();

//				System.out.println("n:" + n + " k:" + k + "\na:" + a + " b:"
//						+ b + " c:" + c + " r:" + r);

				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(a);

				int mK;
				for (int j = 1; j < k; j++) {
					mK = (b * list.get(j-1) + c) % r;
					list.add(mK);
				}

//				for (Integer integer : list) {
//					System.out.print(integer + " ");
//				}
//				System.out.println();

				ArrayList<Integer> newList = new ArrayList<Integer>();
				ArrayList<Integer> allList = new ArrayList<Integer>();
				newList.addAll(list);
				allList.addAll(list);

				int checkpoint = k;
				int mVal = 0;
				while (checkpoint < n) {
					if (!newList.contains(mVal)) {
						newList.add(mVal);
						newList.remove(0);
						allList.add(mVal);
						mVal = 0;
						checkpoint++;
					} else {
						mVal++;
					}
				}

//				for (Integer integer : allList) {
//					System.out.print(integer + " ");
//				}
//				System.out.println();

//				System.out.println("Case #" + i + ": " + allList.get(n - 1));
				out.write("Case #" + i + ": " + allList.get(n - 1));
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
