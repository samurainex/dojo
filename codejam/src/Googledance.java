

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;

public class Googledance {
	public static void main (String[] args) {
		FileReader fr = null;
		BufferedReader br = null;
		Writer out = null;
		Writer debug = null;
		try {
			fr = new FileReader(args[0]);
			br = new BufferedReader(fr);
			out = new OutputStreamWriter(new FileOutputStream("googledance-result.txt"));
			debug = new OutputStreamWriter(new FileOutputStream("googledance-debug.txt"));
			String numOfLine = br.readLine();
			int line = 0;
			if (numOfLine != null) {
				line = Integer.valueOf(numOfLine);
			} else {
				return;
			}

			String lineData;
			String[] danceArray;

			for (int i = 1; i <= line; i++) {
				String resultLine = "";
				lineData = br.readLine();
				danceArray = lineData.split(" ");
				int numOfGoogler = Integer.valueOf(danceArray[0]);
				int numOfSurprise = Integer.valueOf(danceArray[1]);
				int minSingleScore = Integer.valueOf(danceArray[2]);

				int googlerTotalScore = 0;
				int okCounter = 0;
				int surpriseCounter = 0;
				int kOk = 0, lOk = 0, mOk = 0;
				boolean okNumber = false;
				boolean surpriseNumber = false;
				for (int j = 3; j < 3 + numOfGoogler; j++) {
					okNumber = false;
					surpriseNumber = false;
					kOk = 0; lOk = 0; mOk = 0;
					googlerTotalScore = Integer.valueOf(danceArray[j]);
					int minScoreBound = 0;
					if (minSingleScore >= 2) {
						minScoreBound = 2;
					} else if (minSingleScore == 1) {
						minScoreBound = 1;
					} else if (minSingleScore == 0) {
						minScoreBound = 0;
					}
						for (int k = minSingleScore - minScoreBound; k <= 30; k++) {
							if (okNumber && !surpriseNumber) {
								break;
							}
							for (int l = minSingleScore - minScoreBound; l <= 30; l++) {
								if (okNumber && !surpriseNumber) {
									break;
								}
								for (int m = minSingleScore - minScoreBound; m <= 30; m++) {
									if ((k + l + m) == googlerTotalScore) {
										if (k >= minSingleScore || l >= minSingleScore || m >= minSingleScore) {
											if ((Math.abs(k - l) < 2) && (Math.abs(l - m) < 2) && (Math.abs(k - m) < 2)) {
												okNumber = true;
												kOk = k;
												lOk = l;
												mOk = m;
												surpriseNumber = false;
												break;
											} else if ((Math.abs(k - l) <= 2) && (Math.abs(l - m) <= 2) && (Math.abs(k - m) <= 2)) {
												okNumber = true;
												kOk = k;
												lOk = l;
												mOk = m;
												surpriseNumber = true;
											}
										}
									}
								}
							}
						}

						debug.write("break out at: " + kOk + " " + lOk + " " + mOk + " ok: " + okNumber + " surprise: " + surpriseNumber + "\n");

						if (okNumber) {
							okCounter++;
						}

						if (surpriseNumber) {
							surpriseCounter++;
						}
				}

				if (surpriseCounter > numOfSurprise) {
					okCounter = okCounter - (surpriseCounter - numOfSurprise);
				}

				resultLine = "Case #" + i + ": " + okCounter;

				debug.write("\n");
				out.write(resultLine);
				if (i != line) {
					out.write("\n");
				}
				System.out.println(resultLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
				out.close();
				debug.close();
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
