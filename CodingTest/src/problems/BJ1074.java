package problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1074 {
	// 분할 정복
	static int N, R, C, powN, result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		powN = (int) Math.pow(2, N);
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		moveZ(0, 0, powN);

	}

	public static void moveZ(int r, int c, int size) {
		if (size == 1) {
			System.out.print(result);
			return;
		}

		int newSize = size / 2;
		if (R < r + newSize && C < c + newSize) {
			moveZ(r, c, newSize);
		}
		if (R < r + newSize && C >= c + newSize) {
			result += (size * size) / 4;
			moveZ(r, c + newSize, newSize);
		}
		if (R >= r + newSize && C < c + newSize) {
			result += ((size * size) / 4) * 2;
			moveZ(r + newSize, c, newSize);
		}
		if (R >= r + newSize && C >= c + newSize) {
			result += ((size * size) / 4) * 3;
			moveZ(r + newSize, c + newSize, newSize);
		}
	}
}
