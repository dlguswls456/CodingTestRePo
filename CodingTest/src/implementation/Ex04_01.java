package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ex04_01 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int x = 1;
		int y = 1;

		// Solution1
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		while (st.hasMoreTokens()) {
//			String move = st.nextToken();
//			switch (move) {
//			case "L":
//				y--;
//				if (y < 1) {
//					y++;
//					break;
//				}
//				break;
//			case "R":
//				y++;
//				if (y > N) {
//					y--;
//					break;
//				}
//				break;
//			case "U":
//				x--;
//				if (x < 1) {
//					x++;
//					break;
//				}
//				break;
//			case "D":
//				x++;
//				if (x > N) {
//					x--;
//					break;
//				}
//				break;
//			}
//		}

		// Solution2
		ArrayList<String> moveTypes = new ArrayList<String>();
		moveTypes.add("L");
		moveTypes.add("R");
		moveTypes.add("U");
		moveTypes.add("D");
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };
		ArrayList<String> moveList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			moveList.add(st.nextToken());
		}

		for (String move : moveList) {
			int moveType = moveTypes.indexOf(move);
			if (x + dx[moveType] < 1 || x + dx[moveType] > N || y + dy[moveType] < 1 || y + dy[moveType] > N)
				continue;
			x += dx[moveType];
			y += dy[moveType];
		}

		System.out.print(x + " " + y);
	}

}
