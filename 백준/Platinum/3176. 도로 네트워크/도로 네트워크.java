// 3176 도로 네트워크

import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static HashMap<Integer, ArrayList<int[]>> graph; // 출발도시: {도착도시, 가중치}
	static int[] depth;
	static boolean[] visited;
	
	static int[][] parent; // parent[K][V]: V의 2^K번째 조상 정점 번호
	static int[][] minDistance; // minDistance[K][V]: V에서 2^K번째까지 가는 도로 중 최소 거리
	static int[][] maxDistance;
	static int LOG;
	
	static void bfs() {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		q.addLast(1);
		depth[1] = 0;
		visited[1] = true;
		
		while (!q.isEmpty()) {
			int curNode = q.removeFirst();
			int curDepth = depth[curNode];
			
			for (int[] next: graph.get(curNode)) {
				int nextNode = next[0];
				int distance = next[1];
				
				if (!visited[nextNode]) {
					q.addLast(nextNode);
					depth[nextNode] = curDepth + 1;
					visited[nextNode] = true;
					
					parent[0][nextNode] = curNode;
					minDistance[0][nextNode] = distance;
					maxDistance[0][nextNode] = distance;
				}
			}
		}
	}
	
	static void findAncestors() {
		for (int i = 1; i <= LOG; i++) {
			for (int j = 1; j <= N; j++) {
				parent[i][j] = parent[i - 1][parent[i-1][j]];
				minDistance[i][j] = Math.min(minDistance[i - 1][j], minDistance[i - 1][parent[i - 1][j]]);
				maxDistance[i][j] = Math.max(maxDistance[i - 1][j], maxDistance[i - 1][parent[i - 1][j]]);
			}
		}
	}
	
	static int[] getMinMax(int a, int b) {
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		if (depth[a] > depth[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		for (int i = LOG; i >= 0; i--) {
			if (depth[b] - depth[a] >= (1 << i)) {
				min = Math.min(min, minDistance[i][b]);
				max = Math.max(max, maxDistance[i][b]);
				b = parent[i][b];
			}
		}
		
		if (a == b) {
			return new int[] {min, max};
		}
		
		for (int i = LOG; i >= 0; i--) {
			if (parent[i][a] != parent[i][b]) {
				min = Math.min(Math.min(min, minDistance[i][a]), minDistance[i][b]);
				max = Math.max(Math.max(max, maxDistance[i][a]), maxDistance[i][b]);
				
				a = parent[i][a];
				b = parent[i][b];
			}
		}
		
		min = Math.min(Math.min(min, minDistance[0][a]), minDistance[0][b]);
		max = Math.max(Math.max(max, maxDistance[0][a]), maxDistance[0][b]);
		
		return new int[] {min, max};
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		depth = new int[N + 1];
		
		LOG = 0;
		int size = 1;
		while (size <= N) {
			size <<= 1;
			LOG += 1;
		}
		parent = new int[LOG + 1][N + 1];
		minDistance = new int[LOG + 1][N + 1];
		maxDistance = new int[LOG + 1][N + 1];
		
		graph = new HashMap<>();
		for (int i = 1; i <= N; i++) {
			graph.put(i, new ArrayList<>());
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			graph.get(A).add(new int[] {B, C});
			graph.get(B).add(new int[] {A, C});
		}
		
		bfs(); // depth, 부모 계산
		findAncestors(); // 조상 찾기
		
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			int[] result = getMinMax(from, to);
			
			bw.write(Integer.toString(result[0]) + " " + Integer.toString(result[1]) + "\n");
		}
		bw.flush();
		
		bw.close();
		br.close();
	}

}
