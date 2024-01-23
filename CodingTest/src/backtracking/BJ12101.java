package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ12101 {

	static ArrayList<ArrayList<Integer>> cases = new ArrayList<ArrayList<Integer>>();
	static ArrayList<Integer> tempCase = new ArrayList<Integer>();
	static int N, K;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		sumUntilN(0);

		// 결과가 K개 미만일 때 출력 후 종료
		if (cases.size() < K) {
			System.out.println(-1);
			return;
		}

		String result = "";
		for (int num : cases.get(K - 1)) {
			result += num + "+";
		}
		System.out.println(result.substring(0, result.length() - 1));

	}

	public static void sumUntilN(int curSum) {
		// 합이 N이면 만들어진 리스트를 전체 케이스 리스트에 저장
		if (curSum == N) {
//			cases.add((ArrayList<Integer>) tempCase.clone());
			ArrayList<Integer> cpyCase = new ArrayList<Integer>();
			cpyCase.addAll(tempCase);
			cases.add(cpyCase);

			return;
		}
		// N보다 커지면 그냥 종료
		if (curSum > N) {
			return;
		}

		for (int i = 1; i <= 3; i++) {
			tempCase.add(i);
			sumUntilN(curSum + i); // 현재까지 합을 전달
			tempCase.remove(tempCase.size() - 1);
		}
	}

}
