// 1246 사전

import java.util.*;
import java.io.*;


public class Main {
	static int N, M;
	static long K;
	static long[][] combinations;
	static long MAX = 1_000_000_000;
	
	static void calculateCombinations() {
		combinations[0][0] = 1;
		combinations[1][0] = 1;
		combinations[1][1] = 1;
		
		for (int i = 2; i < combinations.length; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					combinations[i][j] = 1;
				} else {
					if (combinations[i - 1][j - 1] >= MAX || combinations[i - 1][j] >= MAX) {
						combinations[i][j] = MAX;
					} else {
						combinations[i][j] = combinations[i - 1][j - 1] + combinations[i - 1][j];
					}
				}
			}
		}
	}
	
	static String findKthString() {
		int leftA = N;
		int leftZ = M;
		StringBuilder sb = new StringBuilder();
		
		if (combinations[N + M][M] < K) {
			return "-1";
		}
		
		// N + M 길이의 문자열 만들기
		while (sb.length() < N + M) {
			// 결정 문자 a인 경우
			if (K <= combinations[leftA + leftZ - 1][leftZ]) {
				sb.append("a");
				leftA -= 1;
			} else {
				sb.append("z");
				K -= combinations[leftA + leftZ - 1][leftZ];
				leftZ -= 1;
			}
		}
		
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		combinations = new long[N + M + 1][N + M + 1];
		
		calculateCombinations();
		String kth = findKthString();

		bw.write(kth);
		
		bw.close();
		br.close();
	}

}