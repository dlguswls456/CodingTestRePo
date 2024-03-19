package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ2531 {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int result = 0;
		int[] sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N; i++) {
			HashSet<Integer> hs = new HashSet<Integer>();
			for (int j = i; j < i + k; j++) {
				if (j >= N) {
					int tempIdx = j % N;
					hs.add(sushi[tempIdx]);
				} else {
					hs.add(sushi[j]);
				}
			}
			hs.add(c);
			result = Math.max(result, hs.size());
		}

		System.out.println(result);
	}

}
