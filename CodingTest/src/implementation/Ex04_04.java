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

		// ���⿡ ���� �����̴� ����
		int[] direction = { -1, 1, 1, -1 };

		// �ٴ�, ���� ���� �ޱ�
		int[][] isOcean = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				isOcean[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// �̹� �湮�� ���� ���
		int[][] haveBeen = new int[N][M];
		haveBeen[A][B] = 1;

		// �� ���� ��� �� �� ���⿡ ���� ���� ī��Ʈ
		int currentDirection = d;
		int failedCnt = 0;
		while (true) {
			// �� ��ġ�� �ӽ� ��ġ�� ����
			int tempA = A;
			int tempB = B;
			// ��� ���⿡ ���ؼ� ������ ���
			if (failedCnt == 4) {
				failedCnt = 0;
				currentDirection--;
				// ��ĭ �ڷ� �ӽ� ��ġ ����
				if (currentDirection == 0 || currentDirection == 2) {
					tempA = A + direction[currentDirection] * -1;
				} else {
					tempB = B + direction[currentDirection] * -1;
				}

				// ��ĭ�� �ٴٶ�� ����
				if (isOcean[tempA][tempB] == 1)
					break;

				// ��ĭ���� �̵� ���� -> ����ġ ����
				A = tempA;
				B = tempB;
			}

			// ���� �ε��� ����
			currentDirection = currentDirection % 4;
			// ư �������� �����̴� �ӽ� ��ġ ����
			if (currentDirection == 0 || currentDirection == 2) {
				tempA = A + direction[currentDirection];
				// ���� �Ѿ�� �ٴ��̹Ƿ� ����
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

			// �ӽ� ��ġ�� ���� ���� �ִٸ�
			if (haveBeen[tempA][tempB] == 1) {
				failedCnt++;
				currentDirection++;
				continue;
			}

			// �ӽ� ��ġ�� �ٴٶ��
			if (isOcean[tempA][tempB] == 1) {
				failedCnt++;
				currentDirection++;
				continue;
			}

			// ������� �����ߴٸ� ��ĭ �̵� �����ϴٴ� �� -> �� ��ġ ����
			A = tempA;
			B = tempB;
			// �湮�� ���� ���
			haveBeen[A][B] = 1;
		}

		// �湮�� ���� ī���
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
