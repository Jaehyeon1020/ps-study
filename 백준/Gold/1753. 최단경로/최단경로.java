// 1753 최단경로

import java.util.*;
import java.io.*;

public class Main {
	static int V, E;
	static int[] distance;
	static int K;
	static HashMap<Integer, ArrayList<int[]>> graph;
	static PriorityQueue<int[]> pq; // {노드번호, 가중치}
	static boolean[] visited;
	
	static void djikstra(int start) {
		visited = new boolean[V + 1];
		
		distance = new int[V + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		for (int[] next: graph.get(start)) {
			pq.add(next);
		}
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curNode = cur[0];
			int curDist = cur[1];
			
			if (visited[curNode]) continue;
			visited[curNode] = true;
			distance[curNode] = Math.min(distance[curNode], curDist);
			
			for (int[] next: graph.get(curNode)) {
				int nextNode = next[0];
				int nextDist = curDist + next[1];
				
				if (visited[nextNode]) continue;
				pq.add(new int[] {nextNode, nextDist});
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		graph = new HashMap<>();
		for (int i = 1; i <= V; i++) {
			graph.put(i, new ArrayList<>());
		}
				
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new int[] {to, weight});
		}
		
		djikstra(K);
		
		for (int i = 1; i <= V; i++) {
			int dist = distance[i];
			if (dist == Integer.MAX_VALUE) {
				bw.write("INF\n");
			} else {
				bw.write(Integer.toString(distance[i]) + "\n");
			}
		}
		bw.flush();
		
		br.close();
		bw.close();
	}

}
