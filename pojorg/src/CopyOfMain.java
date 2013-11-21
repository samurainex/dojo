import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CopyOfMain {

	private static int cur;

	private static char acA;
	private static char acB;

	private static char[] mem = new char[256];

	public static void main(String[] args) {

		InputStreamReader br = new InputStreamReader(System.in);

		try {
			while (true) {
				cur = 0;
				Arrays.fill(mem, '0');

				br.read(mem, 0, 256);
				br.read();

				if (mem[0] == '8') {
					return;
				}

				while (cur < 256) {
					runInstruction();
				}

				System.out.println(mem);
			}
		} catch (Exception e) {
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}

	}

	private static void runInstruction() {
		switch (mem[cur]) {
		case '0':

			acA = mem[getAddr(mem[cur + 1], mem[cur + 2])];

			cur += 3;
			break;

		case '1':

			int addr = getAddr(mem[cur + 1], mem[cur + 2]);
			mem[addr] = acA;

			cur += 3;
			break;

		case '2':

			char acC = acA;
			acA = acB;
			acB = acC;

			cur++;
			break;

		case '3':

			int total = getDec(acA) + getDec(acB);
			if (total >= 16) {
				acB = getHex(total / 16);
				acA = getHex(total % 16);
			} else {
				acB = '0';
				acA = getHex(total);
			}

			cur++;
			break;

		case '4':

			if (acA == '9')
				acA = 'A';
			else if (acA == 'F')
				acA = '0';
			else
				acA++;

			cur++;
			break;

		case '5':

			if (acA == '0')
				acA = 'F';
			else if (acA == 'A')
				acA = '9';
			else
				acA--;

			cur++;
			break;

		case '6':

			if (acA != '0') {
				cur += 3;
				break;
			} else {
				cur = getAddr(mem[cur + 1], mem[cur + 2]);

				runInstruction();
				break;
			}

		case '7':

			cur = getAddr(mem[cur + 1], mem[cur + 2]);

			runInstruction();
			break;

		case '8':

			cur = 256;

			break;

		default:
			break;
		}
	}

	private static short getAddr(char a, char b) {

		short addr = 0;

		byte decA = getDec(a);

		addr += (decA == 0 ? 0 : 16 * decA);
		addr += getDec(b);

		return addr;
	}

	private static byte getDec(char chara) {

		switch (chara) {
		case '0':
			return 0;

		case '1':
			return 1;

		case '2':
			return 2;

		case '3':
			return 3;

		case '4':
			return 4;

		case '5':
			return 5;

		case '6':
			return 6;

		case '7':
			return 7;

		case '8':
			return 8;

		case '9':
			return 9;

		case 'A':
			return 10;

		case 'B':
			return 11;

		case 'C':
			return 12;

		case 'D':
			return 13;

		case 'E':
			return 14;

		case 'F':
			return 15;

		default:
			return 0;
		}
	}

	private static char getHex(int dec) {

		switch (dec) {
		case 0:
			return '0';

		case 1:
			return '1';

		case 2:
			return '2';

		case 3:
			return '3';

		case 4:
			return '4';

		case 5:
			return '5';

		case 6:
			return '6';

		case 7:
			return '7';

		case 8:
			return '8';

		case 9:
			return '9';

		case 10:
			return 'A';

		case 11:
			return 'B';

		case 12:
			return 'C';

		case 13:
			return 'D';

		case 14:
			return 'E';

		case 15:
			return 'F';

		default:
			return 0;
		}

	}
}
