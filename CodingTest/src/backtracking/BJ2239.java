package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ2239 {

	static int[][] array = new int[9][9];
	static boolean[][] ver = new boolean[9][10];
	static boolean[][] hor = new boolean[9][10];
	static boolean[][] box = new boolean[9][10];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int cnt0 = 0;
	static ArrayList<Node> zero = new ArrayList<Node>();

	public static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			String line = br.readLine();
			for (int j = 0; j < 9; j++) {
				array[i][j] = line.charAt(j) - '0';
				if (array[i][j] != 0) {
					ver[i][array[i][j]] = true;
					hor[j][array[i][j]] = true;
					box[(i / 3) * 3 + (j / 3)][array[i][j]] = true;
				} else {
					cnt0++;
					zero.add(new Node(i, j));
				}

			}
		}

		find(0);

	}

	public static void find(int cnt) {
		if (cnt == cnt0) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(array[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}

		Node curNode = zero.get(cnt);
		for (int i = 1; i <= 9; i++) {
			if (!ver[curNode.x][i] && !hor[curNode.y][i] && !box[(curNode.x / 3) * 3 + (curNode.y / 3)][i]) {
				ver[curNode.x][i] = true;
				hor[curNode.y][i] = true;
				box[(curNode.x / 3) * 3 + (curNode.y / 3)][i] = true;
				array[curNode.x][curNode.y] = i;
				find(cnt + 1);
				ver[curNode.x][i] = false;
				hor[curNode.y][i] = false;
				box[(curNode.x / 3) * 3 + (curNode.y / 3)][i] = false;
			}
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= 9 || y < 0 || y >= 9) {
			return false;
		}
		return true;
	}

}
