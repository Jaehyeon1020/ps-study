// 11404 플로이드

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] graph;
	
	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (graph[i][k] == Integer.MAX_VALUE || graph[k][j] == Integer.MAX_VALUE) continue;
					
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 도시 개수
		M = Integer.parseInt(br.readLine()); // 간선 개수
		
		graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
		}
		for (int i = 1; i <= N; i++) {
			graph[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			graph[from][to] = Math.min(graph[from][to], distance);
		}
		
		floyd();
		
		for (int i = 1; i <= N; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 1; j <= N; j++) {
				sb.append(graph[i][j] == Integer.MAX_VALUE ? 0 : graph[i][j]).append(" ");
			}
			sb.append("\n");
			
			bw.write(sb.toString());
		}
		bw.flush();
		
		bw.close();
		br.close();
	}

}
