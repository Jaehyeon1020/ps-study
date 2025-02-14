// 7579 앱

import java.io.*;
import java.util.*;

public class Main {
	static int N; // 활성 앱 개수
	static int M; // 확보해야 할 메모리
	static int[] memory;
	static int[] cost;
	static int[][] dp; // dp[i][j]: i번째 앱까지 종료할 수 있을 때 j의 비용으로 확보 가능한 최대 메모리
	
	static void makeDpTable() {
		for (int j = 0; j <= 100 * N; j++) {
			dp[0][j] = cost[0] <= j ? memory[0] : 0;
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j <= 100 * N; j++) {
				if (cost[i] > j) { // 앱을 종료하는 비용이 가용보다 큼 => 종료 불가, 이전 값 그대로 사용
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i -1][j], memory[i] + dp[i - 1][j - cost[i]]);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N][100 * N + 1];
		
		memory = new int[N];
		cost = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		makeDpTable();
		
		int minCost = -1;
		for (int c = 0; c <= 100 * N; c++) {
			if (dp[N - 1][c] >= M) {
				minCost = c;
				break;
			}
		}
		
		
		bw.write(Integer.toString(minCost));
		bw.flush();
		
		bw.close();
		br.close();
	}

}
