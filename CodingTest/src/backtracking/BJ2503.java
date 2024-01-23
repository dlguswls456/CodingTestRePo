package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2503 {

	// 정답일 가능성 있는 숫자인지 확인하기 위한 용도
	static boolean[] isPossible = new boolean[988];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// isPossible 초기화
		for (int i = 123; i <= 987; i++) {
			String strNum = String.valueOf(i);
			// 0이 들어가거나
			if (strNum.contains("0")) {
				continue;
			}
			// 중복되는 숫자가 있는 경우는 가능성이 없는 숫자
			if (strNum.charAt(0) == strNum.charAt(1) || strNum.charAt(0) == strNum.charAt(2)
					|| strNum.charAt(1) == strNum.charAt(2)) {
				continue;
			}
			// 해당 케이스들을 제외한 숫자들을 true로 초기화
			isPossible[i] = true;
		}

		// 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String guess = st.nextToken();
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());

			// 숫자 하나씩 받을 때마다 정답일 가능성 검사
			for (int j = 123; j <= 987; j++) {
				if (isPossible[j]) {// 가능성이 있는 경우 실행
					chkAnswer(guess, j, strike, ball);
				}
			}
		}

		// 가능한 경우 카운트
		int result = 0;
		for (int j = 123; j <= 987; j++) {
			if (isPossible[j]) {
				result++;
			}
		}
		System.out.println(result);

	}

	public static void chkAnswer(String guess, int number, int strike, int ball) {
		int tempStrike = 0;
		int tempBall = 0;
		int cpyNum = number;

		// 한자리씩 검사
		for (int i = 0; i < 3; i++) {
			int tempNum = number % 10;
			int idx = guess.indexOf(Integer.toString(tempNum).charAt(0));
			if (idx != -1) { // 예측한 숫자에 포함된 숫자일 때
				if (idx == i) { // 스트라이크
					tempStrike++;
				} else { // 볼
					tempBall++;
				}
			}
			number /= 10;
		}

		// 영호가 알려준 답이랑 다른경우 가능성 없는 숫자라고 변경
		if (strike != tempStrike || ball != tempBall) {
			isPossible[cpyNum] = false;
		}
	}
}
