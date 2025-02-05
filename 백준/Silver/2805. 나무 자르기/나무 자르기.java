// 2805 나무 자르기

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long M;
	static long[] trees;
	static long treeMax = 0;
	
	static long getMaxHeight() {
		// 0 - treeMax 사이 탐색 (upper bound)
		
		long minHeight = 0;
		long maxHeight = treeMax;
		
		while (minHeight <= maxHeight) {
			long cutterHeight = (minHeight + maxHeight) / 2;
//			System.out.println(cutterHeight);
			
			long cutted = 0;
			for (long tree: trees) {
				if (tree > cutterHeight) cutted += tree - cutterHeight;
			}
			
			if (cutted == M) {
				return cutterHeight;
			}
			else if (cutted > M) { // 잘린 양이 많음 => 높이 올리기
				minHeight = cutterHeight + 1;
			} else { // 잘린 양이 적음 => 높이 내리기
				maxHeight = cutterHeight - 1;
			}
		}
		
		return maxHeight;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = (long)Integer.parseInt(st.nextToken());
		
		trees = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			long treeHeight = (long) Integer.parseInt(st.nextToken());
			trees[i] = treeHeight;
			treeMax = Math.max(treeMax, treeHeight);
		}
		
		System.out.println(getMaxHeight());
	}
}
