package twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15961 {

	static int N, d, k, c, result;
	static int[] sushi;
	static int[] cntEating;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		result = 0;
		sushi = new int[N];
		cntEating = new int[d + 1];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		slidingWindow();
		System.out.println(result);
	}

	public static void slidingWindow() {
		cntEating[c]++;
		result = 1;
		for (int i = 0; i < k; i++) {
			if (cntEating[sushi[i]] == 0) {
				result++;
			}
			cntEating[sushi[i]]++;
		}

		int cnt = result;
		for (int i = 0; i < N; i++) {
			// 앞 빼주기
			cntEating[sushi[i]]--;
			if (cntEating[sushi[i]] == 0) {
				cnt--;
			}

			// 뒤 넣어주기
			if (cntEating[sushi[(i + k) % N]] == 0) {
				cnt++;
			}
			cntEating[sushi[(i + k) % N]]++;

			result = Math.max(result, cnt);
		}

	}

}
