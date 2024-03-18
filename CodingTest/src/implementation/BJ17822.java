package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17822 {

	static int[][] array;
	static int N, M, T;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		array = new int[N + 1][M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			// 회전 시키기
			for (int i = x; i <= N; i += x) {
				int[] tempArr = new int[M];
				for (int j = 0; j < M; j++) {
					int idx = 0;
					if (d == 0) {
						idx = j + k;
						if (M <= idx) {
							idx -= M;
						}
					} else {
						idx = j - k;
						if (idx < 0) {
							idx += M;
						}
					}
					tempArr[idx] = array[i][j];
				}
				array[i] = tempArr.clone();
			}

//			System.out.println("======회전========");
//			for (int i = 1; i <= N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(array[i][j] + " ");
//				}
//				System.out.println();
//			}

			// 계산하기
			boolean flagOuter = false;
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					// 지울 수 있는 가능성이 경우
					boolean flag = false;
					if (array[i][j] != 0) {
						flag = dfs(i, j, array[i][j]);
						if (flag) {
							array[i][j] = 0;
							flagOuter = true;
						}
					}

				}
			}

//			System.out.println("======인접========");
//			for (int i = 1; i <= N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(array[i][j] + " ");
//				}
//				System.out.println();
//			}

			// 평균 구하기
			if (!flagOuter) {
				double avg = 0.0;
				int cnt = 0;
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (array[i][j] != 0) {
							avg += array[i][j];
							cnt++;
						}
					}
				}
				avg /= cnt;
//				System.out.println("avg = " + avg);

				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (array[i][j] != 0) {
							if ((double) array[i][j] < avg) {
								array[i][j]++;
							} else if ((double) array[i][j] > avg) {
								array[i][j]--;
							}
						}
					}
				}
			}

//			System.out.println("======평균========");
//			for (int i = 1; i <= N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(array[i][j] + " ");
//				}
//				System.out.println();
//			}

		}

		int result = 0;
//		System.out.println("=======최종=======");
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
//				System.out.print(array[i][j] + " ");
				result += array[i][j];
			}
//			System.out.println();
		}
		System.out.println(result);

	}

	public static boolean dfs(int x, int y, int num) {
		boolean result = false;
		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (newY == M) {
				newY = 0;
			} else if (newY == -1) {
				newY = M - 1;
			}
			if (isValid(newX, newY) && array[newX][newY] == num) {
				result = true;
				array[newX][newY] = 0;
				dfs(newX, newY, num);
			}
		}

		return result;
	}

	public static boolean isValid(int x, int y) {
		if (x < 1 || x > N || y < 0 || y >= M) {
			return false;
		}
		return true;
	}

}
