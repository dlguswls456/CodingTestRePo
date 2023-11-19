package problems;
import java.io.*;
import java.util.*;

public class SWEA1961 {

	static int[][] array;
	static int N;
	static BufferedWriter bw;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			bw.write("#" + t + "\n");
			N = Integer.parseInt(br.readLine());
			array = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] turn90Array = turn90();
			int[][] turn180Array = turn90();
			int[][] turn270Array = turn90();

			printArrays(turn90Array, turn180Array, turn270Array);

		}
		bw.close();
	}

	public static void printArrays(int[][] A, int[][] B, int[][] C) throws IOException {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bw.write(String.valueOf(A[i][j]));
			}
			bw.write(" ");
			for (int j = 0; j < N; j++) {
				bw.write(String.valueOf(B[i][j]));
			}
			bw.write(" ");
			for (int j = 0; j < N; j++) {
				bw.write(String.valueOf(C[i][j]));
			}
			bw.newLine();
		}
	}

	public static int[][] turn90() {
		int[][] currentArray = array.clone();
		int[][] newArray = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newArray[i][j] = currentArray[N - j - 1][i];
			}
		}
		array = newArray;
		return newArray;
	}
}