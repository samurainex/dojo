

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ToTheMax {
	public static void main(String[] args) {
		Scanner scan = null;

		try {
//			scan = new Scanner(System.in);
			scan = new Scanner(new File("to-the-max.txt"));

			ArrayList<Integer> result = new ArrayList<Integer>();

			int size = scan.nextInt();
			int[][] square = new int[size][size];
			int temp = 0;
			int totalAll = 0;
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					square[i][j] = temp = scan.nextInt();
					totalAll += temp;
					result.add(temp);
				}
			}


			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {

					for (int k = 0; k <= size - 1; k++) {
						for (int m = 0; m <= size - 1; m++) {

							if (k == 0 && m == 0)
								continue;



						}
					}

				}
			}

			result.add(totalAll);

			System.out.println(Collections.max(result));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scan != null) {
				scan.close();
			}
		}
	}
}
