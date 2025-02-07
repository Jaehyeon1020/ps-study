// 14476 최대공약수 하나 빼기

import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] nums;
	static int[] leftToRight;
	static int[] rightToLeft;
	
	static int gcd(int a, int b) {
		if (a < b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	static void fillGcdArr() {
		// left to right
		leftToRight[0] = nums[0];
		for (int i = 1; i < n; i++) {
			leftToRight[i] = gcd(leftToRight[i - 1], nums[i]);
		}
		
		// right to left
		rightToLeft[n - 1] = nums[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			rightToLeft[i] = gcd(nums[i], rightToLeft[i + 1]);
		}
	}
	
	static void printGcdAndNum() {
		int maxGcd = 0;
		int num = 0;;
		
		int overAllGcd = gcd(nums[0], nums[1]);
		for (int i = 2; i < n; i++) {
			overAllGcd = gcd(overAllGcd, nums[i]);
		}
		
		// 빼고 GCD 계산할 숫자의 인덱스
		for (int i = 0; i < n; i++) {
			
			int gcd;
			int exceptedNum;
			
			if (i == 0) {
				maxGcd = rightToLeft[1];
				num = nums[i];
				continue;
			} else if (i == n - 1) {
				gcd = leftToRight[n - 2];
				exceptedNum = nums[n - 1];
				
				if (maxGcd < gcd) {
					maxGcd = gcd;
					num = exceptedNum;
				}
				continue;
			}
			
			gcd = gcd(leftToRight[i - 1], rightToLeft[i + 1]);
			exceptedNum = nums[i];
			
			if (maxGcd < gcd) {
				maxGcd = gcd;
				num = exceptedNum;
			}
		}
		
		if (overAllGcd == maxGcd) System.out.print(-1);
		else System.out.print(Integer.toString(maxGcd) + " " + Integer.toString(num));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		leftToRight = new int[n];
		rightToLeft = new int[n];
		
		fillGcdArr();
		printGcdAndNum();
	}

}
