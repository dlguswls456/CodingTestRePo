package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10974 {

	static int N;
	static boolean[] isVisited;
	static int[] tempNumbers;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		isVisited = new boolean[N + 1];
		tempNumbers = new int[N];

		numbers(0);

	}

	public static void numbers(int cnt) {
		// N개의 숫자 모였을 때
		if (cnt == N) {
			for (int i : tempNumbers) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (isVisited[i] == false) { // 안쓰인 숫자라면
				isVisited[i] = true; // 방문처리
				tempNumbers[cnt] = i; // 배열에 값 저장
				numbers(cnt + 1); // 다음 숫자 찾으러
				isVisited[i] = false; // 끝나면 되돌리자
				tempNumbers[cnt] = -1;
			}
		}
	}

}
