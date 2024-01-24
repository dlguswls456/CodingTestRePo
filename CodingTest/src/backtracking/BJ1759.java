package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1759 {

	static int L, C;
	static ArrayList<String> alphabet = new ArrayList<String>();
	static String[] vowels = { "a", "e", "i", "o", "u" };
	static boolean[] isVisited;
	static ArrayList<String> cases = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		isVisited = new boolean[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {// 입력받은 알파벳
			alphabet.add(st.nextToken());
		}
		alphabet.sort(null);// 정렬

		password(0, 0);
	}

	public static void password(int cnt, int idx) {
		if (cnt == L) {// L개 모였을 때
			// 자음 모음 개수 확인
			int cntVowels = 0;
			int cntNonVowels = 0;
			for (String vowel : vowels) {
				if (cases.contains(vowel)) {
					cntVowels++;
				}
			}
			cntNonVowels = cases.size() - cntVowels;

			if (cntVowels >= 1 && cntNonVowels >= 2) {// 조건 만족시 출력
				for (String str : cases) {
					System.out.print(str);
				}
				System.out.println();
			}

			return;
		}

		for (int i = 0; i < C; i++) {// 아직 덜 모였을 때
			if (i < idx) {// 사전식 출력을 위해 더 앞의 알파벳은 금지
				continue;
			}
			if (!isVisited[i]) {// 방문 안한 알파벳에 대해
				isVisited[i] = true; // 포함시키자
				cases.add(alphabet.get(i)); 
				password(cnt + 1, i); // 재귀
				isVisited[i] = false; // 다 돌려놔
				cases.remove(cases.size() - 1);
			}

		}

	}
}