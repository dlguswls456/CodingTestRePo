package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex04_02 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// for 0-60
		int cnt = 0;
		for (int i = 0; i <= 59; i++) {
			String strI = String.valueOf(i);
			if (strI.contains("3")) {
				cnt++;
			}
		}
		// 3이 하나라도 들어가는 분, 초 계산
		cnt = 60 * 60 - (60 - cnt) * (60 - cnt);

		// for hour
		int hourCnt = 0;
		for (int i = 3; i <= N; i++) {
			String strI = String.valueOf(i);
			if (strI.contains("3")) {
				hourCnt++;
			}
		}

		int nCnt = cnt * (N - hourCnt + 1);
		hourCnt *= 60 * 60;
		System.out.print(nCnt + hourCnt);
	}

}
