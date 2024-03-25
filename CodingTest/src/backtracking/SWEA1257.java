package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class SWEA1257 {

	static int K;
	static HashSet<String> strings;
	static StringBuilder sb;
	static String word;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			word = br.readLine();

			strings = new HashSet<String>();
			sb = new StringBuilder();

			for (int i = 0; i < word.length(); i++) {
				findString(i);
			}

			ArrayList<String> list = new ArrayList<String>();
			list.addAll(strings);
			list.sort(null);

			String result = "";
			if (K >= list.size()) {
				result = "none";
			} else {
				result = list.get(K - 1);
			}
			System.out.printf("#%d %s\n", t, result);
		}
	}

	public static void findString(int idx) {
		if (sb.length() != 0) {
			strings.add(sb.toString());
		}

		if (idx >= word.length()) {
			return;
		}

		sb.append(word.charAt(idx));
		findString(idx + 1);
		sb.deleteCharAt(sb.length() - 1);
	}

}