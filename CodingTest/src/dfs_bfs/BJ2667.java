package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ2667 {

	static int[][] maps;
	static boolean[][] isVisited;
	static int N, tempHouses;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Integer> houses = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		maps = new int[N][N];
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!isVisited[i][j] && maps[i][j] == 1) {
					tempHouses = 0;
					dfs(i, j);
					houses.add(tempHouses);
				}
			}
		}

		System.out.println(houses.size());
		houses.sort(null);
		for (int house : houses) {
			System.out.println(house);
		}
	}

	public static void dfs(int x, int y) {
		tempHouses++;
		isVisited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY) && !isVisited[newX][newY] && maps[newX][newY] == 1) {
				dfs(newX, newY);
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
