// 1717 집합의 표현

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] parent;
	
	static void init() {
		parent = new int[N + 1];
		
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		parent[bRoot] = aRoot;
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		init();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (op == 0) {
				// 합집합
				union(a, b);
			} else {
				// 출력
				if (find(a) == find(b)) bw.write("YES\n");
				else bw.write("NO\n");
			}
		}
		bw.flush();
		
		bw.close();
		br.close();
	}

}
