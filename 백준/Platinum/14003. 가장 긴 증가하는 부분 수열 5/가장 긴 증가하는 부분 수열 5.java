// 14003 가장 긴 증가하는 부분 수열 5

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] input;
	static int[] lis;
	static ArrayList<Integer> trace;
	static int lastIndex;
	static int lisSize = 0;
	
	static String getLis() {
		lastIndex = 0;
		
		// lis 길이 구하기
		for (int num: input) {
			if (lastIndex == 0) {
				lis[lastIndex] = num;
				trace.add(lastIndex);
				
				lastIndex += 1;
			} else if (num > lis[lastIndex - 1]) {
				lis[lastIndex] = num;
				trace.add(lastIndex);
				
				lastIndex += 1;
			} else {
				int lb = lowerBound(num);
				lis[lb] = num;
				trace.add(lb);
			}
		}
		
		// lis 구하기
		int target = lastIndex - 1;
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for (int i = trace.size() - 1; i >= 0; i--) {
			if (trace.get(i) == target) {
				stack.push(input[i]);
				target -= 1;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop().toString()).append(" ");
		}
		
		lisSize = lastIndex;
		
		return sb.toString();
	}
	
	static int lowerBound(int target) {
		int left = 0;
		int right = lastIndex - 1;
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (lis[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N];
		lis = new int[N];
		trace = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		String lisStr = getLis();
		
		bw.write(Integer.toString(lastIndex) + "\n");
		bw.write(lisStr);
		bw.flush();
		
		br.close();
		bw.close();
	}

}
