package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ14502 {
	static class Location {
		int x, y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static ArrayList<Integer> tempCase = new ArrayList<Integer>();
	static HashMap<Integer, Location> chickens;
	static ArrayList<Location> houses;
	static boolean[] isVisited;
	static int M, N;
	static int minDistance = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] city = new int[N][N];
		houses = new ArrayList<Location>();
		chickens = new HashMap<Integer, Location>();
		int cntChicken = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				city[i][j] = num;

				if (num == 1) {
					houses.add(new Location(i, j));
				} else if (num == 2) {
					chickens.put(cntChicken, new Location(i, j));
					cntChicken++;
				}
			}
		}
		isVisited = new boolean[cntChicken];
		chooseMChickens(0, 0);
		System.out.println(minDistance);

	}

	public static void chooseMChickens(int cnt, int idx) {
		if (cnt == M) {
			int distance = calcTotalDistance(houses, tempCase);
			if (minDistance > distance) {
				minDistance = distance;
			}
			return;
		}

		for (int i = idx; i < chickens.size(); i++) {
			if (isVisited[i] == false) {
				isVisited[i] = true;
				tempCase.add(i);
				chooseMChickens(cnt + 1, i + 1);
				isVisited[i] = false;
				tempCase.remove(tempCase.size() - 1);
			}
		}

	}

	public static int calcChickenDistance(Location house, ArrayList<Integer> tempCase) {
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < tempCase.size(); i++) {
			int distance = Math.abs(house.x - chickens.get(tempCase.get(i)).x)
					+ Math.abs(house.y - chickens.get(tempCase.get(i)).y);
			if (minDistance > distance) {
				minDistance = distance;
			}
		}
		return minDistance;
	}

	public static int calcTotalDistance(ArrayList<Location> house, ArrayList<Integer> tempCase) {
		int sum = 0;
		for (int i = 0; i < house.size(); i++) {
			sum += calcChickenDistance(house.get(i), tempCase);
			if(sum>minDistance) {
				return minDistance;
			}
		}
		return sum;
	}

}
