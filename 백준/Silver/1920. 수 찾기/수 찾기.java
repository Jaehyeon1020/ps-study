import java.util.*;
import java.io.*;

public class Main {
	
	// arr가 target을 포함하는지 체크
	public static int isContaining(int target, int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			
			if (arr[mid] == target) return 1;
			else if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		int n, m;
		int[] nNums, mNums;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		nNums = new int[n];
		for (int i = 0; i < n; i++) {
			nNums[i] = Integer.parseInt(st.nextToken());
		}
		
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		mNums = new int[m];
		for (int i = 0; i < m; i++) {
			mNums[i] = Integer.parseInt(st.nextToken());
		}
		
		br.close();
		
		Arrays.sort(nNums);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < m; i++) {
			int checkingNum = mNums[i];
			bw.write(Integer.toString(isContaining(checkingNum, nNums)) + "\n");
			bw.flush();
		}
	}

}
