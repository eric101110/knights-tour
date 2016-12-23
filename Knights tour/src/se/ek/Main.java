package se.ek;

/**
 * @author Eric Karlsson
 */

public final class Main {

	private static final int BOARD_LENGTH = 5;
	private static final int BOARD_TOTAL_LENGTH = BOARD_LENGTH * BOARD_LENGTH;
	private static int[][] board;
	private static final Move[] moves = new Move[] { new Move(2, 1), new Move(1, 2), new Move(-1, 2), new Move(-2, 1),
			new Move(-2, -1), new Move(-1, -2), new Move(1, -2), new Move(2, -1) };

	public static void initializeBoard() {

		board = new int[BOARD_LENGTH][BOARD_LENGTH];

		for (int x = 0; x < BOARD_LENGTH; x++) {
			for (int y = 0; y < BOARD_LENGTH; y++) {
				board[x][y] = 0;
			}
		}
	}

	public static boolean tryMove(int moveX, int moveY) {

		return ((moveX >= 0 && moveY >= 0 && moveX <= BOARD_LENGTH - 1 && moveY <= BOARD_LENGTH - 1
				&& board[moveX][moveY] == 0));

	}

	public static boolean solve(int currentPositionX, int currentPositionY, int moveCount) {

		board[currentPositionX][currentPositionY] = moveCount;

		if (moveCount == BOARD_TOTAL_LENGTH) {
			printSolution();
			return true;
		} else {
			for (int i = 0; i < moves.length; i++) {
				int moveX = currentPositionX + moves[i].getX();
				int moveY = currentPositionY + moves[i].getY();

				if (tryMove(moveX, moveY)) {
					moveCount++;
					if (solve(moveX, moveY, moveCount)) {
						return true;
					} else {
						board[moveX][moveY] = 0;
						moveCount--;
					}
				}
			}
		}
		return false;
	}

	public static void printSolution() {

		for (int y = 0; y < BOARD_LENGTH; y++) {
			for (int x = 0; x < BOARD_LENGTH; x++) {

				if (x % BOARD_LENGTH == 0) {
					System.out.println();
				}
				System.out.print(String.format("%-5s", board[x][y]));
			}
		}
	}

	public static void main(String[] args) {
		initializeBoard();
		solve(0, 0, 1);
	}

}

class Move {
	private int x, y;

	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
