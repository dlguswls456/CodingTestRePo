package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex04_04 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		// 방향에 따라 움직이는 정보
		int[] direction = { -1, 1, 1, -1 };

		// 바다, 육지 정보 받기
		int[][] isOcean = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				isOcean[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 이미 방문한 육지 기록
		int[][] haveBeen = new int[N][M];
		haveBeen[A][B] = 1;

		// 현 방향 기록 및 각 방향에 대해 실패 카운트
		int currentDirection = d;
		int failedCnt = 0;
		while (true) {
			// 현 위치를 임시 위치로 복사
			int tempA = A;
			int tempB = B;
			// 모든 방향에 대해서 실패한 경우
			if (failedCnt == 4) {
				failedCnt = 0;
				currentDirection--;
				// 한칸 뒤로 임시 위치 수정
				if (currentDirection == 0 || currentDirection == 2) {
					tempA = A + direction[currentDirection] * -1;
				} else {
					tempB = B + direction[currentDirection] * -1;
				}

				// 뒷칸이 바다라면 종료
				if (isOcean[tempA][tempB] == 1)
					break;

				// 뒷칸으로 이동 성공 -> 현위치 수정
				A = tempA;
				B = tempB;
			}

			// 방향 인덱스 수정
			currentDirection = currentDirection % 4;
			// 튼 방향으로 움직이는 임시 위치 설정
			if (currentDirection == 0 || currentDirection == 2) {
				tempA = A + direction[currentDirection];
				// 범위 넘어가면 바다이므로 실패
				if (tempA >= N || tempA < 0) {
					failedCnt++;
					currentDirection++;
					continue;
				}
			} else {
				tempB = B + direction[currentDirection];
				if (tempB >= M || tempB < 0) {
					failedCnt++;
					currentDirection++;
					continue;
				}
			}

			// 임시 위치에 가본 적이 있다면
			if (haveBeen[tempA][tempB] == 1) {
				failedCnt++;
				currentDirection++;
				continue;
			}

			// 임시 위치가 바다라면
			if (isOcean[tempA][tempB] == 1) {
				failedCnt++;
				currentDirection++;
				continue;
			}

			// 여기까지 도달했다면 한칸 이동 가능하다는 뜻 -> 현 위치 수정
			A = tempA;
			B = tempB;
			// 방문한 육지 기록
			haveBeen[A][B] = 1;
		}

		// 방문한 육지 카운드
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (haveBeen[i][j] == 1) {
					cnt++;
				}
			}
		}

		System.out.print(cnt);
	}

}
