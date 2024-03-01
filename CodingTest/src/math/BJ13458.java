package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ13458 {

	static int[] students;
	static int N, B, C;
	static long minResult;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		students = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		minResult += N;
		for (int i = 0; i < N; i++) {
			students[i] -= B;
			if (students[i] < 0) {
				students[i] = 0;
			}
		}

		for (int i = 0; i < N; i++) {
			if (students[i] != 0) {
				if (students[i] % C == 0) {
					minResult += students[i] / C;
				} else {
					minResult += students[i] / C + 1;
				}
			}
		}

		System.out.println(minResult);
	}

}
