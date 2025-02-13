// 1932 정수 삼각형

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[][] memo; // memo[i][j]: i층 j번째 값이 선택됐을 때 최대값
	static ArrayList<Integer>[] lists;
	
	static void calculate() {
		memo[1][0] = lists[1].get(0);
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= i - 1; j++) {
				if (j == 0) {
					memo[i][j] = lists[i].get(j) + memo[i - 1][0];
				}
				else if (j == i - 1) {
					memo[i][j] = lists[i].get(j) + memo[i - 1][i - 2];
				} else {
					memo[i][j] = lists[i].get(j) + Math.max(memo[i -1][j - 1], memo[i - 1][j]);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		memo = new int[N + 1][N];
		lists = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			lists[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i; j++) {
				lists[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		calculate();
		
		int answer = -1;
		for (int value: memo[N]) {
			answer = Math.max(answer, value);
		}
		
		bw.write(Integer.toString(answer));
		bw.flush();
		
		bw.close();
		br.close();
	}

}
