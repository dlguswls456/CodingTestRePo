package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14620 {

	static int N, minPrice;// 화단 변의 길이, 결과 값
	static int[][] nodes;// 화단 배열
	static boolean[][] isOccupied;// 찜한 자리 처리

	public static void main(String[] args) throws IOException {
		// 입력받기 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new int[N][N];
		isOccupied = new boolean[N][N];
		minPrice = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				nodes[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 진짜 실행
		findSpots(0, 0);

		System.out.println(minPrice);
	}

	// 몇번째 꽃인지 확인, minPrice계산
	public static void findSpots(int cnt, int price) {
		if (price >= minPrice) // 이미 최저가보다 비싸지면 중단
			return;
		if (cnt == 3) {// 마지막 꽃 도달
			minPrice = Math.min(minPrice, price);
			return;
		}

		for (int i = 1; i < N - 1; i++) { // idx 0은 어차피 불가능
			for (int j = 1; j < N - 1; j++) {
				if (isPossible(i, j)) { // 심기 가능할때만
					reverse(i, j); // 해당 자리에 심기
					findSpots(cnt + 1, calcPrice(i, j, price)); // 다음 꽃 찾으러
					reverse(i, j); // 되돌려놓기
				}
			}
		}
	}

	// 가격 계산
	public static int calcPrice(int x, int y, int price) {
		int[] dx = { -1, 1, 0, 0, 0 };
		int[] dy = { 0, 0, 1, -1, 0 };
		for (int i = 0; i < 5; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY)) {
				price += nodes[newX][newY];
			}
		}
		return price;
	}

	// 심기 가능한지 확인
	public static boolean isPossible(int x, int y) {
		boolean isPossible = true;
		int[] dx = { -1, 1, 0, 0, 0 };
		int[] dy = { 0, 0, 1, -1, 0 };
		for (int i = 0; i < 5; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			if (isValid(newX, newY) && !isOccupied[newX][newY]) {
				isPossible = true;
			} else {
				isPossible = false;
				break;
			}
		}

		return isPossible;
	}

	// 꽃 심기 & 되돌리기
	public static void reverse(int x, int y) {
		int[] dx = { -1, 1, 0, 0, 0 };
		int[] dy = { 0, 0, 1, -1, 0 };
		for (int i = 0; i < 5; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			isOccupied[newX][newY] = !isOccupied[newX][newY];
		}
	}

	// 인덱스 유효한지 확인
	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}

		return true;
	}
}
