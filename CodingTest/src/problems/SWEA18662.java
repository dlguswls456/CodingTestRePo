package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA18662 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				numbers.add(Integer.parseInt(st.nextToken()));
			}

			int x = numbers.get(0);
			int y = numbers.get(1);
			int z = numbers.get(2);

			int yx = y - x;
			int zy = z - y;
			double zx = (double) (z + x);

			if (yx == zy) {
				System.out.println("#" + i + " 0.0");
				continue;
			}

			double way1 = y - (zx / 2);
			double way2 = y + yx - z;
			double way3 = y - zy - x;

			double min = Math.min(Math.min(way1 * way1, way2 * way2), way3 * way3);
			String stringMin = String.format("%.1f",Math.sqrt(min));
			System.out.println("#" + i + " " + stringMin);

		}

	}

}
