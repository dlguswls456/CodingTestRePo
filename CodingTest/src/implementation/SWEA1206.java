package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA1206 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] buildings = new int[N + 4];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 2; i < N + 2; i++) {
				buildings[i] = Integer.parseInt(st.nextToken());
			}

			int cntHouse = 0;
			for (int i = 2; i < N + 2; i++) {
				int diff = buildings[i] - findMaxHeight(buildings, i);
				if (diff > 0) {
					cntHouse += diff;
				}
			}

			bw.write("#" + t + " " + cntHouse);
			bw.newLine();
		}
		bw.close();
	}

	public static int findMaxHeight(int[] buildings, int idx) {
		return Math.max(Math.max(Math.max(buildings[idx - 1], buildings[idx - 2]), buildings[idx + 1]),
				buildings[idx + 2]);
	}
}
