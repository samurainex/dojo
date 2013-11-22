import java.util.Scanner;


public class FlipShift {
	
	static int[] disks;
	static int[] newDisks;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int line = sc.nextInt();
		
		for (int i = 0; i < line; i++) {
			
			int length = sc.nextInt();
			
			int curBlack = 0;
			int maxBlack = 0;
			int startBlackPos = 0;
			int stopBlackPos = 0;
			
			// determine which pos. has the most black, to be used as "end point"
			
			disks = new int[length * 2];
			
			int j = 0;
			
			for (; j < length; j++) {
				disks[j] = sc.nextInt();
				
				if (disks[j] == 0) {
					curBlack++;
					if (curBlack > maxBlack) {
						maxBlack = curBlack;
						stopBlackPos = j;
						startBlackPos = j - curBlack + 1;
					}
				} else {
					if (curBlack != 0) {
						curBlack = 0;
					}
				}
			}
			
			j = 0;
			for (int k = length; k < length * 2; k++) {
				disks[k] = disks[j++];
			}
			
			newDisks = new int[length];
			for (int m = 0; m < length; m++) {
				newDisks[m] = disks[m + startBlackPos];
			}
			
			System.out.println();
			System.out.println("maxBlack: " + maxBlack);
			System.out.println("start: " + startBlackPos);
			System.out.println("stop: " + stopBlackPos);
			
			for (int n = 0; n < length * 2; n++) {
				System.out.print(disks[n]);
			}

			System.out.println();
			for (int p = 0; p < length; p++) {
				System.out.print(newDisks[p]);
			}
			
			System.out.println();
		}
	}
	

	public boolean check() {
		
		return false;
	}
}
