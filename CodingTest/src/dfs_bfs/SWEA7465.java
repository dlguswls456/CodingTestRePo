package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA7465 {

	static int[][] ppl;
	static boolean[] isVisited;
	static int N, M;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ppl = new int[N + 1][N + 1];
			isVisited = new boolean[N + 1];
			result = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				ppl[a][b] = 1;
				ppl[b][a] = 1;
			}

			for (int i = 1; i <= N; i++) {
				if (!isVisited[i]) {
					Queue<Integer> queue = new LinkedList<Integer>();
					queue.add(i);
					isVisited[i] = true;
					findConnection(queue);
					result++;
				}
			}

			System.out.printf("#%d %d\n", t, result);
		}

	}

	public static void findConnection(Queue<Integer> queue) {
		while (!queue.isEmpty()) {
			int curPerson = queue.poll();

			for (int i = 1; i <= N; i++) {
				if (!isVisited[i] && ppl[curPerson][i] == 1) {
					queue.add(i);
					isVisited[i] = true;
				}
			}

		}
	}

}
