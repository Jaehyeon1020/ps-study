// 1915 가장 큰 정사각형

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] dp;

	static int getMaxSquare() {
		int m = 0;
		
		dp = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 0) continue;
				if (i == 1 || j == 1) {
					dp[i][j] = 1;
					m = Math.max(m, dp[i][j]);
					continue;
				}
				
				dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
				m = Math.max(m, dp[i][j]);
			}
		}
		
		return m;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			String numStr = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = numStr.charAt(j - 1) == '0' ? 0 : 1;
			}
		}
		
		int line = getMaxSquare();
		
		bw.write(Integer.toString(line * line));
		bw.flush();
		
		bw.close();
		br.close();
	}

}
