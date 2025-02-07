// 6588 골드바흐의 추측

import java.io.*;
import java.util.*;

public class Main {
	static boolean[] isPrime;
	
	static void calcPrimeNums() {
		isPrime = new boolean[1000001];
		
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for (int i = 2; i * i < isPrime.length; i++) {
			if (isPrime[i]) {
				for (int j = i*i; j < isPrime.length; j += i) {
					isPrime[j] = false;
				}
			}
		}
	}
	
	static int[] getCombi(int num) {
		for (int i = num; i >= num / 2; i--) {
			if (i >= num) {
				continue;
			}
			else if (!isPrime[i]) {
				continue;
			}
			
			int b = i;
			int a = num - i;
						
//			System.out.println(a);
//			System.out.println(b);
						
			if (isPrime[a]) {
				return new int[] {a, b};
			}
		}
		
		return new int[] {-1, -1};
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		calcPrimeNums();
				
		while (true) {
			int n = Integer.parseInt(br.readLine());
			
			if (n == 0) break;
			
			int[] combi = getCombi(n);
			int a = combi[0];
			int b = combi[1];
			
			if (a == -1) bw.write("Goldbach's conjecture is wrong.\n");
			else {
				StringBuilder sb = new StringBuilder();
				sb.append(Integer.toString(n));
				sb.append(" = ");
				sb.append(Integer.toString(a));
				sb.append(" + ");
				sb.append(Integer.toString(b));
				sb.append("\n");
				
				bw.write(sb.toString());
//				bw.write(Integer.toString(n) + " = " + Integer.toString(a) + " + " + Integer.toString(b) + "\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
