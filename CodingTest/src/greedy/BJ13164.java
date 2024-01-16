package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ13164 {

	static int N, K;
	static int[] students;
	static ArrayList<Integer> diffHeight = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		students = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}

		// 각 경계의 차이 값을 저장
		for (int i = 0; i < N - 1; i++) {
			diffHeight.add(students[i + 1] - students[i]);
		}

		diffHeight.sort(Collections.reverseOrder());
		int sum = 0;
		for (int i = 0; i < N - 1; i++) {
			if (K > 1) {
				K--;
				continue;
			}

			sum += diffHeight.get(i);
		}

		System.out.println(sum);
	}

}