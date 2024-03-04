package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 백준 3197 백조의 호수
 * 해당 문제는 1500 x 1500이기 때문에 일반적인 방법으로 풀면 시간초과가 난다
 * 
 * 하루가 지날 때마다 백조들이 만나는지 bfs를 통째로 돌면서 확인하는게 아니라,
 * 
 * 하루가 지날 때, 백조가 움직일 수 있는 영역을 수정해주면서 만나는 여부를 확인하고
 * 만날 수 없다면, 이미 조사한 영역이 아닌 다음날 움직일 수 있는 영역만 조사해줘야 한다.
 * 
 * 물이 녹을 수 있는 영역을 찾는 큐와,
 * 하나의 백조의 움직임을 기록할 큐가 필요하다
 * 
 * if 백조1의 영역에 백조2가 없다면,
 * 		func 얼음 하루 더 녹이기
 * 		result++
 * 
 * fun 얼음 녹이기:
 * 	현재 큐에 담겨있는 크기만큼만 bfs를 돈다 -> 하루치만 검사
 * 	if 얼음 발견
 * 		녹여주고, 내일 다른 얼음을 녹일 수 있는 물이 되므로 큐에 넣어준다
 * 
 * fun 백조영역 확인
 * 	nextQueue 선언: 다음날 백조가 갈 수 있는 얼음을 담을 임시 큐
 * 	현재 큐로 full bfs를 돈다 -> 최초1회만 전체영역, 이후에는 하루치만 검사하게 됨
 * 		백조2를 만나게 되면 종료
 * 		얼음을 만나게 되면 nextQueue에 담는다
 * 		물을 만나면 swanQueue에 담는다
 * 
 */
public class BJ3197 {

	static int[][] pond;
	static boolean[][] isVisited;
	static int R, C, result;
	static Queue<Integer> meltQueue = new LinkedList<Integer>();
	static Queue<Integer> swanQueue = new LinkedList<Integer>();
	static int[][] swan = new int[2][2];
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		pond = new int[R][C];
		isVisited = new boolean[R][C];

		int swanIdx = 0;

		//입력을 받으면서 녹을 수 있는 영역 좌표들을 meltQueue에 넣어준다
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == '.') { // 물
					pond[i][j] = 0;

					meltQueue.add(i);
					meltQueue.add(j);
				} else if (line.charAt(j) == 'X') { // 얼음
					pond[i][j] = -1;
				} else if (line.charAt(j) == 'L') { // 백조
					swan[swanIdx][0] = i;
					swan[swanIdx][1] = j;
					swanIdx++;

					pond[i][j] = 0;

					meltQueue.add(i);
					meltQueue.add(j);
				}
			}
		}
		//백조A의 영역을 돌기 위해 swanQueue에 넣어준다
		swanQueue.add(swan[0][0]);
		swanQueue.add(swan[0][1]);
		isVisited[swan[0][0]][swan[0][1]] = true;

		while (!meet()) {
			melt();
			result++;
		}

		System.out.println(result);

	}

	public static boolean meet() {
		Queue<Integer> nextQueue = new LinkedList<Integer>();

		while (!swanQueue.isEmpty()) {
			int curX = swanQueue.poll();
			int curY = swanQueue.poll();

			for (int i = 0; i < 4; i++) {
				int newX = curX + dx[i];
				int newY = curY + dy[i];

				if (newX == swan[1][0] && newY == swan[1][1]) {
					return true;
				}

				if (isValid(newX, newY) && !isVisited[newX][newY]) {
					isVisited[newX][newY] = true;
					if (pond[newX][newY] == 0) {// 백조가 갈 수 있는 영역
						pond[newX][newY] = 0;
						swanQueue.add(newX);
						swanQueue.add(newY);
					} else { // 백조가 내일 갈 수 있는 영역
						nextQueue.add(newX);
						nextQueue.add(newY);
					}
				}
			}
		}
		swanQueue = nextQueue;
		return false;
	}

	public static void melt() { // 1일치만 돈다
		int size = meltQueue.size();

		for (int i = 0; i < size; i += 2) {
			int curX = meltQueue.poll();
			int curY = meltQueue.poll();

			for (int j = 0; j < 4; j++) {
				int newX = curX + dx[j];
				int newY = curY + dy[j];

				if (isValid(newX, newY) && pond[newX][newY] == -1) {
					pond[newX][newY] = 0;
					meltQueue.add(newX);
					meltQueue.add(newY);
				}
			}
		}
	}

	public static boolean isValid(int x, int y) {
		if (x < 0 || x >= R || y < 0 || y >= C) {
			return false;
		}
		return true;
	}

}
