package twopointer_slidingwindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1940 {

	static int N, M, cnt;
	static int[] material;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		material = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			material[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(material);

		int start = 0;
		int end = N - 1;
		while (start < end) {
			if (material[start] + material[end] == M) {
				cnt++;
				end--;
			} else if (material[start] + material[end] > M) {
				end--;
			} else {
				start++;
			}
		}
		
		System.out.println(cnt);
	}

}
