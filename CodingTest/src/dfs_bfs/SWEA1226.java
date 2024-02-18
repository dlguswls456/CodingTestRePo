package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA1226 {

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int test = Integer.parseInt(br.readLine());
			int[][] array = new int[16][16];
			Queue<Node> queue = new LinkedList<Node>();
			for (int i = 0; i < 16; i++) {
				String line = br.readLine();
				for (int j = 0; j < 16; j++) {
					array[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
					if (array[i][j] == 2) {
						queue.add(new Node(i, j));
					}
				}
			}

			int[] dx = { -1, 1, 0, 0 };
			int[] dy = { 0, 0, -1, 1 };
			int result = 0;
			while (!queue.isEmpty()) {
				Node currentNode = queue.poll();

				for (int i = 0; i < 4; i++) {
					int newX = currentNode.x + dx[i];
					int newY = currentNode.y + dy[i];
					if (array[newX][newY] == 3) {
						result = 1;
						queue.clear();
						break;
					}
					if (array[newX][newY] == 0 && isValid(newX, newY)) {
						array[newX][newY] = -1;
						queue.add(new Node(newX, newY));
					}
				}
			}

			System.out.println("#" + t + " " + result);
		}

	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= 16 || y < 0 || y >= 16) {
			return false;
		}
		return true;

	}

}
