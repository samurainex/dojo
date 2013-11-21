import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	private static int c;

	private static char a;
	private static char b;

	private static char[] mem = new char[256];

	public static void main(String[] args) {

		InputStreamReader br = new InputStreamReader(System.in);

		try {
			while (true) {
				c = 0;
				Arrays.fill(mem, '0');

				br.read(mem, 0, 256);
				br.read();

				if (mem[0] == '8') {
					return;
				}

				while (c < 256) {
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
		switch (mem[c]) {
		case '0':
			a = mem[getAddr(mem[c + 1], mem[c + 2])];

			c += 3;
			break;

		case '1':
			mem[getAddr(mem[c + 1], mem[c + 2])] = a;

			c += 3;
			break;

		case '2':
			char d = a;
			a = b;
			b = d;

			c++;
			break;

		case '3':
			int total = getDec(a) + getDec(b);
			if (total >= 16) {
				b = getHex(total / 16);
				a = getHex(total % 16);
			} else {
				b = '0';
				a = getHex(total);
			}

			c++;
			break;

		case '4':
			if (a == '9')
				a = 'A';
			else if (a == 'F')
				a = '0';
			else
				a++;

			c++;
			break;

		case '5':
			if (a == '0')
				a = 'F';
			else if (a == 'A')
				a = '9';
			else
				a--;

			c++;
			break;

		case '6':
			if (a != '0') {
				c += 3;
				break;
			} else {
				c = getAddr(mem[c + 1], mem[c + 2]);

				runInstruction();
				break;
			}

		case '7':
			c = getAddr(mem[c + 1], mem[c + 2]);

			runInstruction();
			break;

		case '8':
			c = 256;
			break;

		default:
			break;
		}
	}

	private static int getAddr(char a, char b) {

		int addr = 0;
		int decA = getDec(a);

		addr += (decA == 0 ? 0 : 16 * decA);
		addr += getDec(b);

		return addr;
	}

	private static int getDec(char chara) {

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
			return '0';
		}
	}
}