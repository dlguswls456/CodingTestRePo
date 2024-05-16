package shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1956 {

	static int V, E;
	static long minResult;
	static int[][] graph;
	static long[][] shtPath;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new int[V + 1][V + 1];
		shtPath = new long[V + 1][V + 1];
		minResult = Long.MAX_VALUE;

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			graph[from][to] = distance;
			shtPath[from][to] = distance;
		}

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j) {
					continue;
				}

				if (shtPath[i][j] == 0) {
					shtPath[i][j] = Long.MAX_VALUE;
				}
			}
		}

		for (int middle = 1; middle <= V; middle++) {
			for (int start = 1; start <= V; start++) {
				for (int end = 1; end <= V; end++) {
					if (shtPath[start][middle] == Long.MAX_VALUE || shtPath[middle][end] == Long.MAX_VALUE) {
						continue;
					}
					shtPath[start][end] = Math.min(shtPath[start][end], shtPath[start][middle] + shtPath[middle][end]);
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j) {
					continue;
				}
				if (shtPath[i][j] != Long.MAX_VALUE && shtPath[j][i] != Long.MAX_VALUE) {
					minResult = Math.min(shtPath[i][j] + shtPath[j][i], minResult);
				}
			}
		}

		if (minResult == Long.MAX_VALUE) {
			minResult = -1;
		}
		System.out.println(minResult);
	}

}
