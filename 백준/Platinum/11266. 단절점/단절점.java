// 11266 단절점

import java.io.*;
import java.util.*;

public class Main {
	static int V, E;
	static HashMap<Integer, ArrayList<Integer>> graph;
	static HashSet<Integer> points;
	static int searchOrder;
	static int[] order;
	
	static int checkPoints(int cur, boolean isRoot) {
		order[cur] = searchOrder;
		searchOrder += 1;
		
		int childTreeCount = 0;
		int low = order[cur];
		
		for (int next: graph.get(cur)) {
			if (order[next] == 0) {
				childTreeCount += 1;
				
				int subLow = checkPoints(next, false);
				if (!isRoot && order[cur] <= subLow) {
					points.add(cur);
				}
				
				low = Math.min(low, subLow);
			} else {
				low = Math.min(low, order[next]);
			}
		}
		
		if (isRoot && childTreeCount >= 2) {
			points.add(cur);
		}
		
		return low;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		order = new int[V + 1];
		
		graph = new HashMap<>();
		for (int i = 1; i <= V; i++) {
			graph.put(i, new ArrayList<>());
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		points = new HashSet<>();
		for (int i = 1; i <= V; i++) {
			if (order[i] == 0) {
				searchOrder = 1;
				checkPoints(i, true);
			}
		}
		
		ArrayList<Integer> pList = new ArrayList<>();
		for (int p: points) {
			pList.add(p);
		}
		Collections.sort(pList);
		
		bw.write(Integer.toString(pList.size()) + "\n");
		for (int p: pList) {
			StringBuilder sb = new StringBuilder();
			sb.append(Integer.toString(p)).append(" ");
			bw.write(sb.toString());
		}
		
		bw.close();
		br.close();
	}

}
