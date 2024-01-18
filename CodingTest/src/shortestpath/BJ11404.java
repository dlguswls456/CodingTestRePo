package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11404 {

	static int[][] graph;
	static int[][] shortestPath;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		graph = new int[N + 1][N + 1];
		shortestPath = new int[N + 1][N + 1];
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			if (graph[a][b] != 0) {
				w = Math.min(graph[a][b], w);
			}
			graph[a][b] = w;
			shortestPath[a][b] = w;
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == j) {
					continue;
				}
				if (shortestPath[i][j] == 0) {
					shortestPath[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		for (int i = 1; i < N + 1; i++) { // 기준 노드
			for (int j = 1; j < N + 1; j++) { // j,k는 순열
				if (j == i) {
					continue;
				}
				for (int k = 1; k < N + 1; k++) {
					if (k == i || k == j) {
						continue;
					}
					// jk쌍 이루기
					if (shortestPath[j][i] == Integer.MAX_VALUE || shortestPath[i][k] == Integer.MAX_VALUE) {
						shortestPath[j][k] = Math.min(shortestPath[j][k], Integer.MAX_VALUE);
					} else {
						shortestPath[j][k] = Math.min(shortestPath[j][k], shortestPath[j][i] + shortestPath[i][k]);
					}
				}
			}
		}

		for (int i = 1; i < N + 1; i++) { // 기준 노드
			for (int j = 1; j < N + 1; j++) {
				if (shortestPath[i][j] == Integer.MAX_VALUE) {
					System.out.print("0 ");
				} else {
					System.out.print(shortestPath[i][j] + " ");
				}
			}
			System.out.println();
		}

	}

}
