import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = Integer.MAX_VALUE;
		
		int l = 0;
		int r = 0;
		int sum = nums[0];
		while (l != N - 1 || r != N - 1) {
//			System.out.print(l);
//			System.out.print(" ");
//			System.out.println(r);
//			System.out.print("sum: ");
//			System.out.println(sum);
//			System.out.println(answer);
			if (sum >= S) {
				if (l == r) {
					answer = 1;
					break;
				}
				if (r - l + 1 < answer) answer = r - l + 1;
				
				sum -= nums[l];
				l += 1;
			} else {
				if (r < N - 1) {
					r += 1;
					sum += nums[r];
				} else {
					break;
				}
			}
		}
		
		if (answer == Integer.MAX_VALUE) answer = 0;
		if (nums[l] >= S) answer = 1;
		
		System.out.print(answer);
	}
}
