package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1247 {
	public static class XY {
		int x;
		int y;

		XY(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static XY[] locations;
	public static boolean[] isVisited;
	public static int minDistance;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());

		for (int TC = 1; TC <= C; TC++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			locations = new XY[N + 2];
			locations[0] = new XY(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			locations[N + 1] = new XY(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 1; i <= N; i++) {
				locations[i] = new XY(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			minDistance = Integer.MAX_VALUE;
			isVisited = new boolean[N + 2];
			dfs(N, 0, 0, 0);

			System.out.println("#" + TC + " " + minDistance);
		}

	}

	private static void dfs(int N, int idx, int totalDistance, int cnt) {
		if (totalDistance >= minDistance)
			return;
		
		if (cnt == N) {
			totalDistance += calc(idx, N + 1);
			if (totalDistance < minDistance) {
				minDistance = totalDistance;
			}
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (isVisited[i] == false) {
				isVisited[i] = true;
				dfs(N, i, totalDistance + calc(idx, i), cnt + 1);
				isVisited[i] = false;
			}
		}

	}

	public static int calc(int previousIdx, int nextIdx) {
		return Math.abs(locations[previousIdx].x - locations[nextIdx].x)
				+ Math.abs(locations[previousIdx].y - locations[nextIdx].y);
	}

}
