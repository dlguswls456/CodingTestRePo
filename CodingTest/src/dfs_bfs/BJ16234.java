package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 인구이동
public class BJ16234 {

	static int[][] countries;
	static int[][] union;
	static int result, N, L, R, cnt;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		countries = new int[N][N];
		union = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				countries[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 연산 반복
		while (true) {
			// 가능한 연합을 조사
			boolean flag = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (union[i][j] == 0) {
						cnt = 0; // 한 연합에 포함된 국가 개수
						int sum = findUnion(i, j, 0);// 연합 찾은 후 인구 수의 합 반환

						if (cnt == 1) {// 만약 연합생성이 불가능하다면
							union[i][j] = 0;
						} else {// 한번이라도 연합 생성을 했다면
							flag = true; // 연산을 한다는 의미
							calc(i, j, sum / cnt);// 평균값으로 초기화
						}
					}
				}
			}

			// 한번도 연산을 안했다면 종료시키자
			if (!flag) {
				break;
			}

			result++;// 연산했으니까 result+1
			union = new int[N][N];// 연합 초기화
		}

		System.out.println(result);

	}

	public static void calc(int x, int y, int avg) {
		countries[x][y] = avg;// 평균값으로 초기화
		union[x][y] = -1;// 0으로 하면 함수 밖에서 무한루프 돈다
		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY) && union[newX][newY] == 1) {
				calc(newX, newY, avg);
			}
		}

	}

	public static int findUnion(int x, int y, int sum) {
		union[x][y] = 1;// 연합 생성
		cnt++;// 해당 연합 국가 개수
		int totalSum = countries[x][y];// 연합 국가 인구 수 sum할 변수
		for (int i = 0; i < 4; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY) && union[newX][newY] == 0) {
				int diff = Math.abs(countries[x][y] - countries[newX][newY]);
				if (L <= diff && diff <= R) {
					totalSum += findUnion(newX, newY, sum + countries[x][y]);
				}
			}
		}

		return totalSum;
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}

		return true;
	}
}
