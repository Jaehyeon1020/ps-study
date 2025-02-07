// 2725 보이는 점의 개수

import java.io.*;
import java.util.*;

public class Main {
	static int[] counts; // counts[i]: size가 i일 때 보이는 점의 개수
	
	static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	static void calcVisiblePointCount() {
		counts[1] = 3;
		
		for (int size = 2; size <= 1000; size++) {
			int count = counts[size - 1];
			
			// (size, 1~size)
			for (int i = 1; i <= size; i++) {
				if (gcd(size, i) == 1) count += 2;
			}
			
			counts[size] = count;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int c = Integer.parseInt(br.readLine());
		counts = new int[1001];
		
		calcVisiblePointCount();
				
		for (int i = 0; i < c; i++) {
			int tc = Integer.parseInt(br.readLine());
			bw.write(Integer.toString(counts[tc]) + "\n");
		}
		
//		System.out.println(Arrays.toString(counts));
		
		bw.close();
		br.close();
	}

}
