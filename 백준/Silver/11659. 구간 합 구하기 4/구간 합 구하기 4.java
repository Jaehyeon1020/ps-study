// 11659 구간 합 구하기 4

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[] sums;
	
	static int getRangeSum(int start, int end) {
		return sums[end] - sums[start - 1];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sums = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			if (i == 0) sums[i] = Integer.parseInt(st.nextToken());
			else sums[i] = sums[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			bw.write(Integer.toString(getRangeSum(a, b)) + "\n");
		}
		bw.flush();
		
		bw.close();
		br.close();
	}

}
