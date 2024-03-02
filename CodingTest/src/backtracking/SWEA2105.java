package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2105 {

	static int[][] dessert;
	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { -1, 1, 1, -1 };
	static int N, maxResult, firstDir, secondDir, curDir;
	static ArrayList<Integer> tempDessert;
	static int startX, startY;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			maxResult = 0;
			curDir = 0;
			firstDir = 0;
			secondDir = 0;
			dessert = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					dessert[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			tempDessert = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					startX = i;
					startY = j;
					tempDessert.add(dessert[i][j]);
					findDessert(i, j, 0, 0, 0, 1);
					tempDessert.remove(tempDessert.size() - 1);
				}
			}

			if (maxResult == 0) {
				maxResult = -1;
			}
			System.out.println("#" + t + " " + maxResult);
		}

	}

	public static void findDessert(int curX, int curY, int dir, int cntFirst, int cntSecond, int cnt) {
		if (dir == 0) { // 직진
			int newX = curX + dx[0];
			int newY = curY + dy[0];
			if (isValid(newX, newY) && !tempDessert.contains(dessert[newX][newY])) {
				tempDessert.add(dessert[newX][newY]);
				findDessert(newX, newY, 0, cntFirst + 1, cntSecond, cnt + 1);
				tempDessert.remove(tempDessert.size() - 1);
			}

			if (cntFirst > 0) { // 다른 방향으로 전환
				findDessert(curX, curY, 1, cntFirst, cntSecond, cnt);
			}
		} else if (dir == 1) {
			int newX = curX + dx[1];
			int newY = curY + dy[1];
			if (isValid(newX, newY) && !tempDessert.contains(dessert[newX][newY])) {
				tempDessert.add(dessert[newX][newY]);
				findDessert(newX, newY, 1, cntFirst, cntSecond + 1, cnt + 1);
				tempDessert.remove(tempDessert.size() - 1);
			}

			if (cntSecond > 0) { // 다른 방향으로 전환
				findDessert(curX, curY, 2, cntFirst, cntSecond, cnt);
			}
		} else if (dir == 2) {
			if (cntFirst > 0) {
				int newX = curX + dx[2];
				int newY = curY + dy[2];
				if (isValid(newX, newY) && !tempDessert.contains(dessert[newX][newY])) {
					tempDessert.add(dessert[newX][newY]);
					findDessert(newX, newY, 2, cntFirst - 1, cntSecond, cnt + 1);
					tempDessert.remove(tempDessert.size() - 1);
				}
			}
			if (cntFirst == 0) { // 다음 방향으로
				findDessert(curX, curY, 3, cntFirst, cntSecond, cnt);
			}
		} else if (dir == 3) {
			// 방향 안바꿈
			if (cntSecond > 0) {
				int newX = curX + dx[3];
				int newY = curY + dy[3];
				if (isValid(newX, newY)) {
					if (startX == newX && startY == newY) {
						maxResult = Math.max(maxResult, cnt);
					} else if (!tempDessert.contains(dessert[newX][newY])) {
						tempDessert.add(dessert[newX][newY]);
						findDessert(newX, newY, 3, cntFirst, cntSecond - 1, cnt + 1);
						tempDessert.remove(tempDessert.size() - 1);
					}
				}
			}
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}
		return true;
	}

}
