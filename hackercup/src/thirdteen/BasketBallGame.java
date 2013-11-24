package thirdteen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BasketBallGame {
	public static void main(String[] args) {
		Scanner scanner = null;
		Writer out = null;

		try {
			scanner = new Scanner(new File("basketball.txt"));
			out = new OutputStreamWriter(new FileOutputStream(
					"basketball-example.txt"));
			int tCase = scanner.nextInt();
			scanner.nextLine();

			for (int i = 1; i <= tCase; i++) {
				int nRooster = scanner.nextInt();
				int mRotation = scanner.nextInt();
				int pTeamPlayer = scanner.nextInt();

				Player teamA[] = new Player[nRooster % 2 == 0 ? nRooster / 2 : nRooster / 2 + 1];
				Player teamB[] = new Player[nRooster / 2];

				Player players[] = new Player[2 * pTeamPlayer];

				Player rooster[] = new Player[nRooster];

				scanner.nextLine();

				for (int j = 0; j < nRooster; j++) {
					String line = scanner.nextLine();
					String[] data = line.split(" ");
					Player p = new Player(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
					rooster[j] = p;
				}

				Arrays.sort(rooster, new Comparator<Player>() {

					@Override
					public int compare(Player p1, Player p2) {
						if (p1.fgPercentage < p2.fgPercentage) {
							return 1;
						}
						else if (p1.fgPercentage == p2.fgPercentage
								&& p1.height < p2.height) {
							return 1;
						}
						return -1;
					}
				});

				int a = 0;
				int b = 0;
				for (int k = 1; k < rooster.length + 1; k++) {
					if (k % 2 == 0) {
						teamB[b++] = rooster[k - 1];
						if (k < pTeamPlayer * 2 + 1) {
							players[k - 1] = teamB[b - 1];
						}
						teamB[b - 1].draftNumber = k;
					} else {
						teamA[a++] = rooster[k - 1];
						if (k < pTeamPlayer * 2 + 1) {
							players[k - 1] = teamA[a - 1];
						}
						teamA[a - 1].draftNumber = k;
					}
				}

				for (int m = 0; m < mRotation; m++) {

					for (Player player : players) {
						player.minute++;
					}

					Arrays.sort(teamA, new Comparator<Player>() {

						@Override
						public int compare(Player p1, Player p2) {
							if (p1.minute < p2.minute) {
								return -1;
							}
							else if (p1.minute == p2.minute
									&& p1.draftNumber < p2.draftNumber) {
								return -1;
							}
							return 1;
						}
					});

					Arrays.sort(teamB, new Comparator<Player>() {

						@Override
						public int compare(Player p1, Player p2) {
							if (p1.minute < p2.minute) {
								return -1;
							}
							else if (p1.minute == p2.minute
									&& p1.draftNumber < p2.draftNumber) {
								return -1;
							}
							return 1;
						}
					});

					Arrays.sort(players, new Comparator<Player>() {

						@Override
						public int compare(Player p1, Player p2) {
							if (p1.minute < p2.minute) {
								return 1;
							}
							else if (p1.minute == p2.minute
									&& p1.draftNumber < p2.draftNumber) {
								return 1;
							}
							return -1;
						}
					});

					int switchA = 0;
					int switchB = 0;

					boolean setA = false;
					boolean setB = false;

					for (int j = 0; j < players.length; j++) {
						if (players[j].draftNumber % 2 == 0 && setB != true) {
							switchB = j;
							setB = true;
						} else if (players[j].draftNumber % 2 != 0 && setA != true) {
							switchA = j;
							setA = true;
						}
						if (setA && setB)
							break;
					}

					for (int j2 = 0; j2 < teamA.length; j2++) {
						boolean found = false;
						for (int j = 0; j < players.length; j++) {
							if (teamA[j2].draftNumber == players[j].draftNumber) {
								found = true;
								break;
							}
						}
						if(found) {
							found = false;
						} else {
							players[switchA] = teamA[j2];
							break;
						}
					}

					for (int j2 = 0; j2 < teamB.length; j2++) {
						boolean found = false;
						for (int j = 0; j < players.length; j++) {
							if (teamB[j2].draftNumber == players[j].draftNumber) {
								found = true;
								break;
							}
						}
						if(found) {
							found = false;
						} else {
							players[switchB] = teamB[j2];
							break;
						}
					}
				}

				String lastPlayer = "";
				Arrays.sort(players, new Comparator<Player>() {

					@Override
					public int compare(Player p1, Player p2) {
						return p1.name.compareTo(p2.name);
					}
				});

				for (Player player : players) {
					lastPlayer = lastPlayer + " " + player.name;
				}

				out.write("Case #" + i + ": " + lastPlayer.trim());
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

class Player {

	String name;
	int fgPercentage;
	int height;
	int draftNumber;
	int minute;

	public Player(String name, int fgPercentage, int height) {
		super();
		this.name = name;
		this.fgPercentage = fgPercentage;
		this.height = height;
	}
}
