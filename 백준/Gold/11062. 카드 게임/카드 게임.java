// 11062 카드 게임

import java.io.*;
import java.util.*;

public class Main {
	static int T;
	static int N;
	static int[] cards;
	static int[][] dp; // dp[i][j]: i - j번째 카드가 남아있을 때 근우가 얻게 될 최대 점수
	
	static void makeDpTable() {
		if (N % 2 == 1) {
			// 카드가 1개 남은 경우 => 근우 차례. cards[i] 취하기
			for (int i = 1; i <= N; i++) {
				dp[i][i] = cards[i];
			}
			
			// 카드 2 ~ N개 남은 경우 근우가 얻게 될 점수 계산
			for (int left = 2; left <= N; left++) {
				for (int i = 1; i <= N - left + 1; i++) {
					int j = i + left - 1;
					
					if (left % 2 == 1)
						dp[i][j] = Math.max(cards[i] + dp[i + 1][j], cards[j] + dp[i][j - 1]);
					else
						dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		} else { // 카드가 짝수 개 있는 경우
			// 카드가 1개 남은 경우 => 명우 차례. 근우가 얻을 점수는 없음 
			// 카드 2 ~ N개 남은 경우 근우가 얻게 될 점수 계산
			for (int left = 2; left <= N; left++) {
				for (int i = 1; i <= N - left + 1; i++) {
					int j = i + left - 1;
					
					if (left % 2 == 0)
						dp[i][j] = Math.max(cards[i] + dp[i + 1][j], cards[j] + dp[i][j - 1]);
					else
						dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			cards = new int[N + 1];
			dp = new int[N + 1][N + 1];
			
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				cards[j] = Integer.parseInt(st.nextToken());
			}
			
			makeDpTable();
			
			bw.write(Integer.toString(dp[1][N]) + "\n");
		}
		bw.flush();
		
		bw.close();
		br.close();
	}

}
