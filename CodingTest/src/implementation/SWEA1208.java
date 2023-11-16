package implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1208 {
	static int[] boxes;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine());
			boxes = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(boxes);

			while (N > 0) {
				boxes[99]--;
				boxes[0]++;
				Arrays.sort(boxes);
				N--;
			}

			bw.write("#" + t + " " + (boxes[99] - boxes[0]));
			bw.newLine();
		}
		bw.close();
	}
}
