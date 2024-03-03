package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class SWEA2382 {

	static int N, M, K, result;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static ArrayList<Microbe> microbeList;

	static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			return x == other.x && y == other.y;
		}

	}

	static class Microbe {
		Node node;
		int cnt;
		int dir;

		Microbe(Node node, int cnt, int dir) {
			this.node = node;
			this.cnt = cnt;
			this.dir = dir;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			result = 0;
			microbeList = new ArrayList<Microbe>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				Node node = new Node(x, y);
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				microbeList.add(new Microbe(node, cnt, dir));
			}

			// 1시간마다 이동
			while (M > 0) {
				// 1칸씩 이동
				for (Microbe microbe : microbeList) {
					move(microbe);
				}

				// 같은 자리로 오는 미생물 처리 -> HashMap으로 묶어줌
				HashMap<Node, ArrayList<Microbe>> tempMap = new HashMap<Node, ArrayList<Microbe>>();
				for (Microbe microbe : microbeList) {
					if (tempMap.containsKey(microbe.node)) {
						tempMap.get(microbe.node).add(microbe);
					} else {
						ArrayList<Microbe> list = new ArrayList<Microbe>();
						list.add(microbe);
						tempMap.put(microbe.node, list);
					}
				}

				microbeList.clear(); // 초기화

				// 재계산 후 다시 리스트에 넣어준다
				Iterator<Map.Entry<Node, ArrayList<Microbe>>> entry = tempMap.entrySet().iterator();
				while (entry.hasNext()) {
					Map.Entry<Node, ArrayList<Microbe>> elem = entry.next();
					ArrayList<Microbe> list = elem.getValue();
					int max = 0;
					Microbe newMicrobe = new Microbe(elem.getKey(), 0, 0);
					for (Microbe m : list) {
						newMicrobe.cnt += m.cnt;
						if (max < m.cnt) {
							max = m.cnt;
							newMicrobe.dir = m.dir;
						}
					}
					microbeList.add(newMicrobe);
				}

				M--;
			}

			// 결과 합 구하기
			for (Microbe microbe : microbeList) {
				result += microbe.cnt;
			}

			System.out.println("#" + t + " " + result);
		}
	}

	public static void move(Microbe microbe) {
		microbe.node.x += dx[microbe.dir];
		microbe.node.y += dy[microbe.dir];
		// 약품에 닿았을 때
		if (isMedicine(microbe.node.x, microbe.node.y)) {
			microbe.cnt /= 2;
			// 방향 바꿔주기
			if (microbe.dir % 2 == 0) {// 2,4
				microbe.dir -= 1;
			} else {// 1,3
				microbe.dir += 1;
			}
		}
	}

	public static boolean isMedicine(int x, int y) {
		if (x >= 1 && x <= N - 2 && y >= 1 && y <= N - 2) {
			return false;
		}
		return true;
	}

}
