package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ9935 {

	static ArrayList<Character> stringList;
	static String target;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		target = br.readLine();
		String result = "";
		
		stringList = new ArrayList<Character>();
		for (int i = 0; i < line.length(); i++) {
			stringList.add(line.charAt(i));
			if (stringList.size() >= target.length()) {
				if (isContain()) {
					popTarget();
				}
			}
		}

		// 문자열을 합치는 과정에서 메모리 초과가 발생!
		// 스트링버퍼를 사용하자
		StringBuffer sb = new StringBuffer();
		for (char c : stringList) {
			sb.append(c);
		}
		
		result = sb.toString();
		if (result.equals(""))
			result = "FRULA";
		System.out.println(result);
	}

	public static boolean isContain() {
		for (int i = 0; i < target.length(); i++) {
			if (stringList.get(stringList.size() - 1 - i) != target.charAt(target.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	public static void popTarget() {
		for (int i = 0; i < target.length(); i++) {
			stringList.remove(stringList.size() - 1);
		}
	}
}
