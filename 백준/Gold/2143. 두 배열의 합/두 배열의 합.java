import java.io.*;
import java.util.*;

public class Main {
	static long T;
	
	// arr의 모든 부분배열의 합을 담은 list 반환
	static List<Long> getPartialArraySums(int[] arr) {
		List<Long> list = new ArrayList<Long>();
		
		for (int i = 0; i < arr.length; i++) {
			long sum = 0;
			for (int j = i; j < arr.length; j++) {
				sum += (long)arr[j];
				list.add(sum);
			}
		}
		
		return list;
	}
	
	static long findT(List<Long> l1, List<Long> l2) {
		int lp = 0; // l1 idx
		int rp = l2.size() - 1; // l2 idx
		
		long count = 0;
		while (lp < l1.size() && rp >= 0) {
			long left = l1.get(lp);
			long right = l2.get(rp);
			long sum = left + right;
			
			if (sum == T) {
				long l1Count = 0;
				long l2Count = 0;
				
				// l1에서 중복 요소 찾기
				while (lp < l1.size() && l1.get(lp) == left) {
					l1Count += 1;
					lp += 1;
				}
				
				// l2에서 중복 요소 찾기
				while (rp >= 0 && l2.get(rp) == right) {
					l2Count += 1;
					rp -= 1;
				}
				
				count += l1Count * l2Count;
			} else if (sum < T) {
				lp += 1;
			} else {
				rp -= 1;
			}
		}
		
		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		
		int[] nNums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nNums[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		int[] mNums = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			mNums[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		List<Long> sumList1 = getPartialArraySums(nNums);
		List<Long> sumList2 = getPartialArraySums(mNums);
		
		Collections.sort(sumList1);
		Collections.sort(sumList2);
		
//		System.out.println(sumList1.toString());
//		System.out.println(sumList2.toString());
		
		
		System.out.print(findT(sumList1, sumList2));
	}

}
