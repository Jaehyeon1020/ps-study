// 1202 보석 도둑

import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static PriorityQueue<int[]> gemsSortedByValue; // 보석 무게, 가격
	static PriorityQueue<int[]> gemsSortedByWeight; // 보석 무게, 가격
	static int[] bags; // 가방에 담을 수 있는 무게 배열
	static long totalValue = 0;
	
	static void calculateTotalWeight() {		
		for (int canPutW: bags) {
			// 현재 가방 무게로 가능한 보석을 모두 가격 기준 큐에 담기
			while (!gemsSortedByWeight.isEmpty() && gemsSortedByWeight.peek()[0] <= canPutW) {
				gemsSortedByValue.add(gemsSortedByWeight.poll());
			}
			
			if (!gemsSortedByValue.isEmpty()) {
				int[] gem = gemsSortedByValue.poll();
				totalValue += gem[1];
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		gemsSortedByWeight = new PriorityQueue<int[]>((e1, e2) -> e1[0] - e2[0]); // 무게 기준 오름차순 정렬
		gemsSortedByValue = new PriorityQueue<int[]>((e1, e2) -> e2[1] - e1[1]); // 가격 기준 내림차순 정렬
		bags = new int[K];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			gemsSortedByWeight.add(new int[]{weight, price});
		}
		
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
				
//		while (!gems.isEmpty()) {
//			System.out.println(Arrays.toString(gems.poll()));
//		}
		
		Arrays.sort(bags);
				
		calculateTotalWeight();
		
		System.out.print(totalValue);
	}
}
