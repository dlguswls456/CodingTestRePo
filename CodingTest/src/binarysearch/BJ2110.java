package binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2110 {
	static int N, C;
	static ArrayList<Integer> house;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		house = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			house.add(Integer.parseInt(br.readLine()));
		}
		house.sort(null); // 집 순서대로 정렬

		int result = 0;
		// 가장 작은 거리: 1, 가장 큰 거리: 마지막 집-가장 앞 집
		int minDistance = 1;
		int maxDistance = house.get(N - 1) - house.get(0);
		int mid = maxDistance;// 처음엔 제일 뒷집부터 봐야 한다
		while (minDistance <= maxDistance) {
			if (cntHouse(mid) < C) { // CCTV개수가 모자라면 거리를 줄여야하므로 mid 줄여주기
				maxDistance = mid - 1;
			} else { // CCTV개수가 필요 이상이면 거리를 늘려야하므로 mid 늘려주기
				minDistance = mid + 1;
				result = Math.max(mid, result);
			}

			mid = (minDistance + maxDistance) / 2;
		}
		System.out.println(result);
	}

	// 가장 앞집부터 거리체크 및 가능한 CCTV 세기
	public static int cntHouse(int term) {
		int cnt = 1;
		int preHouse = house.get(0);
		for (int i = 1; i < N; i++) {
			if (preHouse + term <= house.get(i)) {
				cnt++;
				preHouse = house.get(i);// CCTV 설치 가능한 집 만나면 기준 지점을 해당 집으로 갱신
			}
		}

		return cnt;
	}
}