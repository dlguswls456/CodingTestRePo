package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1220 {

	static int[][] SN;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= 10; t++) {
			int size = Integer.parseInt(br.readLine());
			SN = new int[100][100];
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					SN[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = 0;
			for (int i = 0; i < 100; i++) {
				int flag = 0;
				for (int j = 0; j < 100; j++) {
					if (SN[j][i] == 2) { // 파랑
						if (flag == 1) {
							result++;
						}
						flag = 2;
					} else if (SN[j][i] == 1) {// 빨강
						flag = 1;
					}
				}
			}
			System.out.println("#" + t + " " + result);
		}
	}

}