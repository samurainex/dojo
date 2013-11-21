import java.io.IOException;
import java.io.InputStreamReader;

public class MicroSim {

	private static final short MEMSIZE = 256;

	private static final byte LD = '0';
	private static final byte ST = '1';
	private static final byte SWP = '2';
	private static final byte ADD = '3';
	private static final byte INC = '4';
	private static final byte DEC = '5';
	private static final byte BZ = '6';
	private static final byte BR = '7';
	private static final byte STP = '8';

	private static int cur;

	private static char acA = '0';
	private static char acB = '0';

	private static char[] mem;

	public static void main(String[] args) throws IOException {

		InputStreamReader br = new InputStreamReader(System.in);

		while (true) {
			cur = 0;
			mem = new char[MEMSIZE];

			br.read(mem, 0, MEMSIZE);
//			System.out.print("\nin: ");
//			System.out.println(mem);

			// read new line
			br.read();

			if (mem[0] == STP) {
//				System.out.println("/END");
				return;
			}

			while (cur < MEMSIZE) {
				runInstruction();
			}
//			System.out.print("out: ");
			System.out.println(mem);
		}
	}

	private static void runInstruction() {
		switch (mem[cur]) {
		case LD:
//			System.out.println(cur + " LD " + mem[cur + 1] + mem[cur + 2]);

			acA = mem[getAddr(mem[cur + 1], mem[cur + 2])];

//			System.out.println("A: " + acA);
//			System.out.println("B: " + acB);

			cur += 3;
			break;

		case ST:
//			System.out.println(cur + " ST " + mem[cur + 1] + mem[cur + 2]);

			int addr = getAddr(mem[cur + 1], mem[cur + 2]);
			mem[addr] = acA;

//			System.out.println("mem[" + addr
//					+ "]: " + mem[addr]);

			cur += 3;
			break;

		case SWP:
//			System.out.println(cur + " SWP");

			char acC = acA;
			acA = acB;
			acB = acC;

//			System.out.println("A: " + acA);
//			System.out.println("B: " + acB);

			cur++;
			break;

		case ADD:
//			System.out.println(cur + " ADD");

			int total = getDec(acA) + getDec(acB);
			if (total >= 16) {
				acB = getHex(total / 16);
				acA = getHex(total % 16);
			} else {
				acB = '0';
				acA = getHex(total);
			}

//			System.out.println("A: " + acA);
//			System.out.println("B: " + acB);

			cur++;
			break;

		case INC:
//			System.out.println(cur + " INC");

			if (acA == '9')
				acA = 'A';
			else if (acA == 'F')
				acA = '0';
			else
				acA++;

//			System.out.println("A: " + acA);
//			System.out.println("B: " + acB);

			cur++;
			break;

		case DEC:
//			System.out.println(cur + " DEC");

			if (acA == '0')
				acA = 'F';
			else if (acA == 'A')
				acA = '9';
			else
				acA--;

//			System.out.println("A: " + acA);
//			System.out.println("B: " + acB);

			cur++;
			break;

		case BZ:
//			System.out.println(cur + " BZ " + mem[cur + 1] + mem[cur + 2]);

			if (acA != '0') {
				cur += 3;
				break;
			} else {
				cur = getAddr(mem[cur + 1], mem[cur + 2]);
//				System.out.println("running command at address: " + cur);

				runInstruction();
				break;
			}

		case BR:
//			System.out.println(cur + " BR " + mem[cur + 1] + mem[cur + 2]);

			cur = getAddr(mem[cur + 1], mem[cur + 2]);
//			System.out.println("running command at address: " + cur);

			runInstruction();
			break;

		case STP:
//			System.out.println(cur + " STP");

			cur = MEMSIZE;

			break;

		default:
//			System.out.println("unknown command: " + mem[cur]);
			break;
		}
	}

	private static short getAddr(char a, char b) {

		short addr = 0;

		byte decA = getDec(a);

		addr += (decA == 0 ? 0 : 16 * decA);
		addr += getDec(b);

//		System.out.println("addr: " + addr);

		return addr;
	}

	private static byte getDec(char chara) {

		switch (chara) {
		// TODO for 0-9 change to Integer.valueOf(char c) ? Don't know if it
		// will be faster and smaller, worth a try
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
//			System.out.println("other hex values: " + chara);
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
//			System.out.println("other dec values: " + dec);
			return 0;
		}

	}
}
