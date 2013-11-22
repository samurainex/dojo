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
					"basketball-result.txt"));
			int tCase = scanner.nextInt();
//			System.out.println(tCase);
			scanner.nextLine();

			for (int i = 1; i <= tCase; i++) {
				int nRooster = scanner.nextInt();
				int mRotation = scanner.nextInt();
				int pTeamPlayer = scanner.nextInt();
				
//				System.out.println(nRooster);
//				System.out.println(mRotation);
//				System.out.println(pTeamPlayer);
				
				
				Player teamA[] = new Player[nRooster % 2 == 0 ? nRooster / 2 : nRooster / 2 + 1];
				Player teamB[] = new Player[nRooster / 2];
				
				Player players[] = new Player[2 * pTeamPlayer];

				Player rooster[] = new Player[nRooster];

				scanner.nextLine();
				
				for (int j = 0; j < nRooster; j++) {
					String line = scanner.nextLine();
//					System.out.println(line);
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
				
				/// LOGGING ///
				System.out.println("sorted rooster");
				for (int j = 0; j < rooster.length; j++) {
					System.out.println(rooster[j].name + " " + rooster[j].fgPercentage + " " + rooster[j].height);
				}
				System.out.println();
				/// LOGGING ///
				
				System.out.println("team");
				int a = 0;
				int b = 0;
				for (int k = 1; k < rooster.length + 1; k++) {
					if (k % 2 == 0) {
						teamB[b++] = rooster[k - 1];
						System.out.println("B " + teamB[b - 1].name + " " + teamB[b - 1].fgPercentage + " " + teamB[b - 1].height);
						if (k < pTeamPlayer * 2 + 1) {
							players[k - 1] = teamB[b - 1];	
						}
//						rooster[k - 1].draftNumber = k;
//						rooster[k - 1].team = "b";
						teamB[b - 1].draftNumber = k;
					} else {
						teamA[a++] = rooster[k - 1];
						System.out.println("A " + teamA[a - 1].name + " " + teamA[a - 1].fgPercentage + " " + teamA[a - 1].height);
						if (k < pTeamPlayer * 2 + 1) {
							players[k - 1] = teamA[a - 1];
						}
//						rooster[k - 1].draftNumber = k;
//						rooster[k - 1].team = "a";
						teamA[a - 1].draftNumber = k;
					}
				}
				System.out.println();
				
				/// LOGGING ///
				System.out.println("starters");
				for (int s = 0; s < players.length; s++) {
					System.out.println(players[s].name + " " + players[s].fgPercentage + " " + players[s].height + " " + players[s].draftNumber);
				}
				System.out.println();
				/// LOGGING ///
				
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

					/// LOGGING ///
					System.out.println("team a after " + (m + 1) + " minute(s)");
					for (int s = 0; s < teamA.length; s++) {
						System.out.println(teamA[s].name + " " + teamA[s].fgPercentage + " " + teamA[s].height + " " + teamA[s].draftNumber+ " " + teamA[s].minute);
						
					}
					System.out.println();
					System.out.println("team b after " + (m + 1) + " minute(s)");
					for (int s = 0; s < teamB.length; s++) {
						System.out.println(teamB[s].name + " " + teamB[s].fgPercentage + " " + teamB[s].height + " " + teamB[s].draftNumber+ " " + teamB[s].minute);
						
					}
					System.out.println();
					/// LOGGING ///
					
//					int countA = 0;
//					int countB = 0;
//					
//					int playerNo = 0;
//					for (int j = 0; j < rooster.length; j++) {
//						if (rooster[j].team.equals("a")) {
//							countA++;
//						} else {
//							countB++;
//						}
//						System.out.println("countA " + countA);
//						System.out.println("countB " + countB);
//						if (countA > pTeamPlayer && rooster[j].team.equals("a")) {
//							System.out.println("A exceed " + rooster[j].name);
//							continue;
//						} else if (countB > pTeamPlayer && rooster[j].team.equals("b")) {
//							System.out.println("B exceed " + rooster[j].name);
//							continue;
//						}
//						players[playerNo++] = rooster[j];
//					}
					int playerCount = 0;
					int aCount = 0;
					int bCount = 0;
					for (int j = 0; j < players.length; j++) {
						if ((j + 1) % 2 != 0) {
							players[playerCount++] = teamA[aCount++];
						} else {
							players[playerCount++] = teamB[bCount++];
						}
					}
					
					/// LOGGING ///
					System.out.println("players after " + (m + 1) + " minute(s)");
					for (int s = 0; s < players.length; s++) {
						System.out.println(players[s].name + " " + players[s].fgPercentage + " " + players[s].height + " " + players[s].draftNumber+ " " + players[s].minute);
					}
					System.out.println();
					/// LOGGING ///
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

				System.out.println("Case #" + i + ": " + lastPlayer);
				out.write("Case #" + i + ": " + lastPlayer);
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
