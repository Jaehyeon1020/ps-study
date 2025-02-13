// 1854 K번째 최단경로 찾기

import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static HashMap<Integer, ArrayList<Node>> graph;
	static PriorityQueue<Integer>[] distance;
	
	static class Node {
		int city;
		int distance;
		
		public Node(int c, int d) {
			this.city = c;
			this.distance = d;
		}
	}
	
	static void initDistancePQ() {
		distance = new PriorityQueue[n + 1];
		for (int i = 1; i <= n; i++) {
			distance[i] = new PriorityQueue<>((n1, n2) -> n2 - n1); // max heap
		}
	}
	
	static void dijkstra() {
		initDistancePQ();
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.city - o2.city);
		
		pq.add(new Node(1, 0));
		distance[1].add(0);
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for(Node next: graph.get(cur.city)) {
				PriorityQueue<Integer> distPQ = distance[next.city];
				int nextDistance = next.distance + cur.distance;
				
				if (distPQ.size() == k) {
					int kthDistance = distPQ.peek();
					
					if (nextDistance < kthDistance) {
						distPQ.poll();
						distPQ.add(nextDistance);
						pq.add(new Node(next.city, nextDistance));
					}
				} else {
					distPQ.add(nextDistance);
					pq.add(new Node(next.city, nextDistance));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 도시 개수
		m = Integer.parseInt(st.nextToken()); // 도로 개수
		k = Integer.parseInt(st.nextToken()); // k 번째 최단경로
		
		graph = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			graph.put(i, new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Node(to, distance));
		}
		
		dijkstra();
		
		for (int i = 1; i <= n; i++) {
			PriorityQueue<Integer> pq = distance[i];
			
			if (pq.size() < k) {
				bw.write("-1\n");
			} else {
				bw.write(Integer.toString(pq.peek()) + "\n");
			}
		}
		bw.flush();
		
		bw.close();
		br.close();
	}
	
}
