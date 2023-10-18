package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Ex03_03 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int currentMin = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				if (num < currentMin) {
					currentMin = num;
				}
			}

			if (currentMin > max) {
				max = currentMin;
			}
		}

		System.out.print(max);
	}

}
