package thirdteen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Tennison {
	public static void main(String[] args) {
		Scanner scanner = null;
		Writer out = null;

		try {
			scanner = new Scanner(new File("tennison.txt"));
			out = new OutputStreamWriter(new FileOutputStream(
					"tennison-result.txt"));
			int tCase = scanner.nextInt();
			System.out.println(tCase);
			scanner.nextLine();

			double pWeatherMin = 0.0;
			double pWeatherMax = 1.0;

	        DecimalFormat df = new DecimalFormat("0.000000");

			for (int i = 1; i <= tCase; i++) {
				int matches = scanner.nextInt();

				double pWinSunny = scanner.nextDouble();
				double pWinRain = scanner.nextDouble();
				double pFirstSunny = scanner.nextDouble();
				double pWinSunIncreaseBy = scanner.nextDouble();
				double pWinSunIncrease = scanner.nextDouble();
				double pLoseSunDecreaseBy = scanner.nextDouble();
				double pLoseSunDecrease = scanner.nextDouble();

				System.out.println(matches + " "
						+ pWinSunny + " "
						+ pWinRain + " "
						+ pFirstSunny + " "
						+ pWinSunIncreaseBy + " "
						+ pWinSunIncrease + " "
						+ pLoseSunDecreaseBy + " "
						+ pLoseSunDecrease);

				double pFirstWin = 0.0;
				double pWin = 0.0;

				pFirstWin = ((pWinSunny * pFirstSunny) + (pWinRain * (1 - pFirstSunny))) ;
				System.out.println("pFirstWin " + pFirstWin);
				System.out.println("pFirstSunny " + pFirstSunny);

				double pCurrentWin = pFirstWin;
				double pCurrentLose = 1 - pFirstWin;
				double pCurrentSunny = 0.0;

				for (int j = 1; j < matches; j++) {
					System.out.println("match " + (j + 1));
					pCurrentSunny = pFirstSunny + (pWinSunIncreaseBy * (pCurrentWin * pWinSunIncrease))
							- (pLoseSunDecreaseBy * (pCurrentLose * pLoseSunDecrease));
					if (pCurrentSunny > pWeatherMax) {
						pCurrentSunny = pWeatherMax;
					} else if (pCurrentSunny < pWeatherMin) {
						pCurrentSunny = pWeatherMin;
					}

					System.out.println("pCurrentSunny " + pCurrentSunny);
					pWin = (pCurrentWin * ((pWinSunny * pCurrentSunny) + (pWinRain * (1 - pCurrentSunny))));

					pCurrentWin = pWin;
					pCurrentLose = 1 - pWin;
					System.out.println("pCurrentWin " + pCurrentWin);
				}

				System.out.println("Case #" + i + ": " + df.format(matches == 1 ? pFirstWin : pWin));
				out.write("Case #" + i + ": " + df.format(matches == 1 ? pFirstWin : pWin));
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
