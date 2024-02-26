package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1861 {

	static int[][] rooms;
	static int N, result, resultRoom;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Integer> queue = new LinkedList<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			result = 0;
			resultRoom = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}

			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int currentRoom = rooms[i][j];
					for (int k = 0; k < 4; k++) {
						int newX = i + dx[k];
						int newY = j + dy[k];
						if (isValid(newX, newY)) {
							if (currentRoom + 1 == rooms[newX][newY]) {
								queue.add(currentRoom);
								queue.add(i);
								queue.add(j);
								queue.add(1);
							}
						}
					}
				}

			}

			bfs();
			System.out.println("#" + t + " " + resultRoom + " " + result);
		}

	}

	public static void bfs() {
		while (!queue.isEmpty()) {
			int from = queue.poll();
			int currentX = queue.poll();
			int currentY = queue.poll();
			int currentDepth = queue.poll();
			if (result == currentDepth) {
				result = currentDepth;
				resultRoom = Math.min(resultRoom, from);
			} else if (result < currentDepth) {
				result = currentDepth;
				resultRoom = from;
			}

			for (int i = 0; i < 4; i++) {
				int newX = currentX + dx[i];
				int newY = currentY + dy[i];
				if (isValid(newX, newY) && rooms[currentX][currentY] + 1 == rooms[newX][newY]) {
					queue.add(from);
					queue.add(newX);
					queue.add(newY);
					queue.add(currentDepth + 1);
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
