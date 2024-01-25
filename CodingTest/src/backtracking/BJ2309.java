package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ2309 {

	static ArrayList<ArrayList<Integer>> cases = new ArrayList<ArrayList<Integer>>();
	static ArrayList<Integer> babies = new ArrayList<Integer>();
	static ArrayList<Integer> tempBabies = new ArrayList<Integer>();
	static boolean[] isVisited = new boolean[9];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			babies.add(Integer.parseInt(br.readLine()));
		}
		babies.sort(null);// 입력 받아서 한번 정렬

		findRealBabies(0, 0, 0); // 난쟁이 찾으러

		for (int baby : cases.get(0)) { 
			System.out.println(baby);
		}

	}

	public static void findRealBabies(int cnt, int curSum, int idx) {
		if (cnt == 7) {
			if (curSum == 100) {
				cases.add((ArrayList<Integer>) tempBabies.clone()); // 진짜 7난쟁이들 저장
			}
			return;
		}

		for (int i = idx; i < 9; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				tempBabies.add(babies.get(i));
				findRealBabies(cnt + 1, curSum + babies.get(i), i);
				isVisited[i] = false;
				tempBabies.remove(tempBabies.size() - 1);
			}
		}

	}

}