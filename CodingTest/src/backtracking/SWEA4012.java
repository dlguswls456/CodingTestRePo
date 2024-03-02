package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA4012 {

	static int[][] ingredient;
	static boolean[] isVisited;
	static int N, minDiff;
	static ArrayList<Integer> tempTeam;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			minDiff = Integer.MAX_VALUE;
			ingredient = new int[N + 1][N + 1];
			isVisited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					ingredient[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			tempTeam = new ArrayList<Integer>();
			findTeam(0, 1);
			System.out.println("#" + t + " " + minDiff);
		}

	}

	public static void findTeam(int cnt, int idx) {
		if (cnt == N / 2) {
			minDiff = Math.min(minDiff, calcDiff());
			return;
		}

		for (int i = idx; i <= N; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				tempTeam.add(i);
				findTeam(cnt + 1, i + 1);
				isVisited[i] = false;
				tempTeam.remove(tempTeam.size() - 1);
			}
		}
	}

	public static int calcDiff() {
		int teamA = 0;
		int teamB = 0;
		for (int i = 0; i < tempTeam.size() - 1; i++) {
			for (int j = i + 1; j < tempTeam.size(); j++) {
				teamA += ingredient[tempTeam.get(i)][tempTeam.get(j)] + ingredient[tempTeam.get(j)][tempTeam.get(i)];
			}
		}

		ArrayList<Integer> teamBList = new ArrayList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (!tempTeam.contains(i)) {
				teamBList.add(i);
			}
		}
		for (int i = 0; i < teamBList.size() - 1; i++) {
			for (int j = i + 1; j < teamBList.size(); j++) {
				teamB += ingredient[teamBList.get(i)][teamBList.get(j)]
						+ ingredient[teamBList.get(j)][teamBList.get(i)];
			}
		}
		return Math.abs(teamA - teamB);
	}

}
