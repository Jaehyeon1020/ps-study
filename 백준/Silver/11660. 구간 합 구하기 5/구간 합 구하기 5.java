// 구간 합 구하기 5

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] sums; // sums[i][j]: i번째줄의 j번째까지의 누적합
	
	static void makeSum() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (j == 1) sums[i][j] = map[i][j];
				else sums[i][j] = sums[i][j - 1] + map[i][j];
			}
		}
	}
	
	static int getSum(int x1, int y1, int x2, int y2) {
		int s = 0;
		
		for (int i = x1; i <= x2; i++) {
			s += sums[i][y2] - sums[i][y1 - 1];
		}
		
		return s;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		sums = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeSum();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			bw.write(Integer.toString(getSum(x1, y1, x2, y2)) + "\n");
		}
		bw.flush();
		
		bw.close();
		br.close();
	}

}
