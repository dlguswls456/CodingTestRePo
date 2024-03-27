package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA4014 {

	static int N, X, result;
	static int[][] map;

	static class Count {
		int height;
		int count;

		Count(int height, int count) {
			this.height = height;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Count [height=" + height + ", count=" + count + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			result = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				int curNum = map[i][0];
				int curCnt = 1;
				ArrayList<Count> tempList = new ArrayList<Count>();
				for (int j = 1; j < N; j++) {
					if (map[i][j] == curNum) {
						curCnt++;
					} else {
						tempList.add(new Count(curNum, curCnt));

						curNum = map[i][j];
						curCnt = 1;
					}
				}
				tempList.add(new Count(curNum, curCnt));

				boolean flag = true;
				for (int j = 1; j < tempList.size(); j++) {
					Count pre = tempList.get(j - 1);
					Count cur = tempList.get(j);

					if (Math.abs(pre.height - cur.height) != 1) {
						flag = false;
						break;
					}

					if (pre.height - cur.height == 1) { // 내리막길
						if (cur.count < X) {
							flag = false;
							break;
						} else {
							cur.count -= X;
						}
					}

					if (pre.height - cur.height == -1) { // 오르막길
						if (pre.count < X) {
							flag = false;
							break;
						} else {
							pre.count -= X;
						}
					}

				}

				if (flag) {
//                    System.out.println("row : " + i);
					result++;
				}

			}

			for (int i = 0; i < N; i++) {
				int curNum = map[0][i];
				int curCnt = 1;
				ArrayList<Count> tempList = new ArrayList<Count>();
				for (int j = 1; j < N; j++) {
					if (map[j][i] == curNum) {
						curCnt++;
					} else {
						tempList.add(new Count(curNum, curCnt));

						curNum = map[j][i];
						curCnt = 1;
					}
				}
				tempList.add(new Count(curNum, curCnt));

				boolean flag = true;
				for (int j = 1; j < tempList.size(); j++) {
					Count pre = tempList.get(j - 1);
					Count cur = tempList.get(j);

					if (Math.abs(pre.height - cur.height) != 1) {
						flag = false;
						break;
					}

					if (pre.height - cur.height == 1) { // 내리막길
						if (cur.count < X) {
							flag = false;
							break;
						} else { // cur 변경
							cur.count -= X;
						}
					}

					if (pre.height - cur.height == -1) { // 오르막길
						if (pre.count < X) {
							flag = false;
							break;
						} else { // pre 변경
							pre.count -= X;
						}
					}

				}

				if (flag) {
//                    System.out.println("col : " + i);
					result++;
				}

			}

			System.out.printf("#%d %d\n", t, result);
		}

	}

}
