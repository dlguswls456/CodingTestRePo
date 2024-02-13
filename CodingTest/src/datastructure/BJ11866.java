package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ11866 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		String result = "<";
		int idx = 0;
		while (!list.isEmpty()) {
			idx += K - 1;
			while (idx >= list.size()) {
				idx -= list.size();
			}

			result += list.remove(idx) + ", ";
		}
		result = result.substring(0, result.length()-2);
		result += ">";
		System.out.println(result);
	}

}
