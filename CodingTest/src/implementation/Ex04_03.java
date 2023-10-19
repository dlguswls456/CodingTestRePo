package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Ex04_03 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String location = br.readLine();

		String[] col = { "a", "b", "c", "d", "e", "f", "g", "h" };
		List<String> column = Arrays.asList(col);
		String tempX = location.substring(0, 1);
		int x = column.indexOf(tempX) + 1;
		int y = Integer.parseInt(location.substring(1, 2));

		int[] dx = { -2, -2, 2, 2, 1, -1, 1, -1 };
		int[] dy = { 1, -1, 1, -1, -2, -2, 2, 2 };

		int cnt = 0;
		for (int i = 0; i < 8; i++) {
			if (x + dx[i] < 1 || x + dx[i] > 8 || y + dy[i] < 1 || y + dy[i] > 8)
				continue;
			cnt++;
		}
		System.out.print(cnt);
	}

}
