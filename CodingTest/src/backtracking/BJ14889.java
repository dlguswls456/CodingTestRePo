package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ14889 {

	static int N;
	static int[][] power;
	static boolean[] isVisitedPerson;
	static ArrayList<Integer> tempTeam;
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		power = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		tempTeam = new ArrayList<Integer>();
		isVisitedPerson = new boolean[N + 1];
		backTracking(1);
		System.out.print(minDiff);
	}

	public static void backTracking(int idx) {
		if (tempTeam.size() == N / 2) {
			int tempDiff = calcPowerDiff(tempTeam);
			if (tempDiff < minDiff) {
				minDiff = tempDiff;
			}
			return;
		}

		for (int i = idx; i <= N; i++) {
			if (isVisitedPerson[i] == false) {
				isVisitedPerson[i] = true;
				tempTeam.add(i);
				backTracking(i + 1);
				isVisitedPerson[i] = false;
				tempTeam.remove(tempTeam.size() - 1);
			}
		}
	}

	public static int calcPowerDiff(ArrayList<Integer> team) {
		int scoreA = 0;
		int scoreB = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (team.contains(i) && team.contains(j)) {
					scoreA += power[i][j];
					scoreA += power[j][i];
				} else if (!team.contains(i) && !team.contains(j)) {
					scoreB += power[i][j];
					scoreB += power[j][i];
				}
			}
		}
		return Math.abs(scoreA - scoreB);
	}
}
