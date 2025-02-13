// 2458 키 순서

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] graph;
	
	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j) continue;
					if (graph[i][k] == Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE) continue;
					
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
	}
	
	static int getNumOfFixedStudent() {
		int student = 0;
		for (int i = 1; i <= N; i++) {
			boolean canReach = true;
			for (int j = 1; j <= N; j++) {
				if (i == j) continue;
				if (graph[i][j] == Integer.MAX_VALUE && graph[j][i] == Integer.MAX_VALUE) {
					canReach = false;
				}
			}
			
			if (canReach) student += 1;
		}
		
		return student;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = 1;
		}
		
		floyd();
		
		bw.write(Integer.toString(getNumOfFixedStudent()));
		bw.flush();
		
		bw.close();
		br.close();
	}

}
