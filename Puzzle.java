public final class Puzzle {
	public final char[][] p;
	public final int blankx;
	public final int blanky;
	public final int[] moves;
	public final int h1;
	public final int h2;

	public Puzzle(String a) {
		p = new char[4][4];
		int x = 0;
		int y = 0;
		for (int i = 0; i <= 15; i++) {
			p[x][y] = a.charAt(i);
			x++;
			if (x > 3) {
				x = 0;
				y++;
			}
		}
		blankx = getBlankX();
		blanky = getBlankY();
		moves = getMoves();
		h1 = h1();
		h2 = h2();
	}

	public int getBlankX() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (p[x][y] == '_') {
					return x;
				}
			}
		}
		return 0;
	}

	public int getBlankY() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (p[x][y] == '_') {
					return y;
				}
			}
		}
		return 0;
	}

	public int h1() {
		String correct = "123456789ABCDFE_";
		String ourString = toString();
		int i = 0;
		for (int j = 0; j < correct.length(); j++) {
			if (correct.charAt(j) == ourString.charAt(j)) {
				i++;
			}
		}
		return (16 - i);
	}

	public int h2() {
		int h2 = 0;
		String str = toString();
		for(int i = 0; i<str.length(); i++) {
			int x1 = getCorrectX(str.charAt(i));
			int y1 = getCorrectY(str.charAt(i));
			int x2 = getCurrentX(str.charAt(i));
			int y2 = getCurrentY(str.charAt(i));
			int dist = Math.abs(x1-x2) + Math.abs(y1-y2);
			h2 = h2 + dist;
		}
		return h2;
	}

	public int getCurrentX(char c) {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (p[x][y] == c) {
					return x;
				}
			}
		}
		return 0;
	}
	
	public int getCurrentY(char c) {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (p[x][y] == c) {
					return y;
				}
			}
		}
		return 0;
	}

	public int getCorrectX(char c) {
		int a = -1;
		if (c == '1' || c == '5' || c == '9' || c == 'D') {
			a = 0;
		} else if (c == '2' || c == '6' || c == 'A' || c == 'E') {
			a = 1;
		} else if (c == '3' || c == '7' || c == 'B' || c == 'F') {
			a = 2;
		} else if (c == '4' || c == '8' || c == 'C' || c == '_') {
			a = 3;
		}
		return a;
	}

	public int getCorrectY(char c) {
		int a = -1;
		
		if (c == '1' || c == '2' || c == '3' || c == '4') {
			a = 0;
		} else if (c == '5' || c == '6' || c == '7' || c == '8') {
			a = 1;
		} else if (c == '9' || c == 'A' || c == 'B' || c == 'C') {
			a = 2;
		} else if (c == 'D' || c == 'E' || c == 'F' || c == '_') {
			a = 3;
		}
		return a;
	}

	public boolean canDown() {
		return !(blanky == 3);
	}

	public boolean canUp() {
		return !(blanky == 0);
	}

	public boolean canLeft() {
		return !(blankx == 0);
	}

	public boolean canRight() {
		return !(blankx == 3);
	}

	public int[] getMoves() {
		getBlankX();
		getBlankY();
		int[] ESWR = { 0, 0, 0, 0 };
		if (canUp()) {
			ESWR[3] = 1;
		}
		if (canDown()) {
			ESWR[1] = 1;
		}
		if (canLeft()) {
			ESWR[2] = 1;
		}
		if (canRight()) {
			ESWR[0] = 1;
		}
		return ESWR;
	}

	public Puzzle moveRight() {
		String newPuzzle = toString();
		int blank = newPuzzle.indexOf("_");
		String first = newPuzzle.substring(0, blank);
		String end = newPuzzle.substring(blank + 1, newPuzzle.length());
		String out = first + end.charAt(0) + "_" + end.substring(1, end.length());
		Puzzle a = new Puzzle(out);
		return a;
	}

	public Puzzle moveLeft() {
		String newPuzzle = toString();
		int blank = newPuzzle.indexOf("_");
		String first = newPuzzle.substring(0, blank - 1);
		String end = newPuzzle.substring(blank - 1, newPuzzle.length());
		String out = first + "_" + end.charAt(0) + end.substring(2, end.length());
		Puzzle a = new Puzzle(out);
		return a;

	}

	public Puzzle moveUp() {
		String str = toString();
		String lineOne = str.substring(0, 4);
		String lineTwo = str.substring(4, 8);
		String lineThree = str.substring(8, 12);
		String lineFour = str.substring(12, 16);
		if (lineTwo.indexOf('_') != -1) {
			String together = swapUp(lineTwo, lineOne);
			String out = together + lineThree + lineFour;
			Puzzle p = new Puzzle(out);
			return p;
			// System.out.println(together);
		} else if (lineThree.indexOf('_') != -1) {
			String together = swapUp(lineThree, lineTwo);
			String out = lineOne + together + lineFour;
			Puzzle p = new Puzzle(out);
			return p;
		}
		// else if (lineFour.indexOf('_') != -1) {
		String together = swapUp(lineFour, lineThree);
		String out = lineOne + lineTwo + together;
		Puzzle p = new Puzzle(out);
		return p;
	}

	public Puzzle moveDown() {
		String str = toString();
		String lineOne = str.substring(0, 4);
		String lineTwo = str.substring(4, 8);
		String lineThree = str.substring(8, 12);
		String lineFour = str.substring(12, 16);
		if (lineOne.indexOf('_') != -1) {
			String out = swapVertical(lineOne, lineTwo);
			out = out + lineThree + lineFour;
			Puzzle p = new Puzzle(out);
			return p;
		} else if (lineTwo.indexOf('_') != -1) {
			String out = swapVertical(lineTwo, lineThree);
			String a = lineOne + out + lineFour;
			Puzzle p = new Puzzle(a);
			return p;
		}
		// else if (lineThree.indexOf('_') != -1) {
		String out = swapVertical(lineThree, lineFour);
		String a = lineOne + lineTwo + out;
		Puzzle p = new Puzzle(a);
		return p;
	}

	public static String swapVertical(String one, String two) {
		int index = one.indexOf("_");
		String newOne = one.substring(0, index) + two.substring(index, index + 1)
				+ one.substring(index + 1, one.length());
		String newTwo = two.substring(0, index) + "_" + two.substring(index + 1, one.length());

		// System.out.println(newOne);
		// System.out.println(newTwo);
		return newOne + newTwo;
	}

	public static String swapUp(String one, String two) {
		int index = one.indexOf("_");
		String newOne = one.substring(0, index) + two.substring(index, index + 1)
				+ one.substring(index + 1, one.length());
		String newTwo = two.substring(0, index) + "_" + two.substring(index + 1, one.length());

		// System.out.println(newOne);
		// System.out.println(newTwo + newOne);
		return newTwo + newOne;
	}

	public String toString() {
		String ourState = "";
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				ourState = ourState + "" + p[x][y];
			}
		}
		return ourState;
	}

	public boolean isDone() {
		String s = toString();
		if (s.equals("123456789ABCDEF_") || s.equals("123456789ABCDFE_")) {
			return true;
		}
		return false;
	}

	public void display() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				System.out.print(p[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println("----");

	}

	public static void main(String[] args) {
		Puzzle p = new Puzzle("123456789ABCD_EF");
		p.display();
	
		System.out.println(p.h1);
	}
}