// 3830 교수님은 기다리지 않는다

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] parent; // [샘플번호, root와의 무게 차(root > this)] 배열
	
	static void init(int n) {
		parent = new int[n + 1][2];
		
		for (int i = 0; i < parent.length; i++) {
			parent[i] = new int[] {i, 0};
		}
	}
	
	static void union(int a, int b, int w) {
		int[] aRoot = find(a);
		int[] bRoot = find(b);
		
		int aRootNum = aRoot[0];
		int toARootWeight = aRoot[1];
		int bRootNum = bRoot[0];
		int toBRootWeight = bRoot[1] + w;
		
		// 더 무거운 쪽을 parent로 만들기
		if (toARootWeight >= toBRootWeight) {
			parent[bRootNum] = new int[] {aRootNum, toARootWeight - toBRootWeight};
		} else {
			parent[aRootNum] = new int[] {bRootNum, toBRootWeight - toARootWeight};
		}
	}
	
	static int[] find(int a) {
		int[] pInfo = parent[a];
		int parentNum = pInfo[0];
		int toParentWeight = pInfo[1];
		
		if (parentNum == a) {
			return pInfo;
		}
		
		int[] rootInfo = find(parentNum);
		return parent[a] = new int[] {rootInfo[0], toParentWeight + rootInfo[1]};
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 샘플 종류 개수
			M = Integer.parseInt(st.nextToken());
			
			if (N == 0 && M == 0) break;
			
			init(N);
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				String op = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (op.equals("!")) {
					// union
					int w = Integer.parseInt(st.nextToken());
					union(a, b, w);
				} else {
					// query
					int[] aFound = find(a);
					int[] bFound = find(b);
					
					int aRoot = aFound[0];
					int aWeight = aFound[1];
					int bRoot = bFound[0];
					int bWeight = bFound[1];
					
					if (aRoot != bRoot) bw.write("UNKNOWN\n");
					else bw.write(Integer.toString(aWeight - bWeight) + "\n");
				}
			}
		}
		bw.flush();
		
		bw.close();
		br.close();
	}

}
