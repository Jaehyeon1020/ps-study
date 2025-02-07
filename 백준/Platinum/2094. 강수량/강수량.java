// 2094 강수량

import java.io.*;
import java.util.*;

public class Main {
	static int[][] facts;
	static int[][] expects;
	static int[] years; // facts에 주어진 연도만 모아서 저장
	
	static int[] tree;
	static int leafPointer;
	static HashMap<Integer, Integer> indexMap; // tree에서 연도에 해당하는 index가 몇인지 저장
	
	static void initTree() {
		// 연도의 개수
		int n = facts.length;
		
		int leafCount = 1;
		while (leafCount <= n) {
			leafCount <<= 1;
		}
		
		int treeSize = leafCount * 2;
		
		tree = new int[treeSize];
		leafPointer = leafCount;
		
		indexMap = new HashMap<>();
		for (int i = 0; i < facts.length; i++) {
			int year = facts[i][0];
			int rain = facts[i][1];
			int treeIdx = leafPointer + i;
			tree[treeIdx] = rain;
			indexMap.put(year, treeIdx);
		}
		
		int cur = leafPointer - 1;
		while (cur != 0) {
			tree[cur] = Math.max(tree[cur * 2], tree[cur * 2 + 1]);
			cur -= 1;
		}
	}
	
	static int getMaxRain(int startYear, int endYear) {
		int left = indexMap.get(startYear);
		int right = indexMap.get(endYear);
		
		if (left == right) return tree[left];
		
//		System.out.println(Arrays.toString(tree));
		
		ArrayList<Integer> comparingVals = new ArrayList<>();
		
		while (left <= right) {
			if (left % 2 == 1) {
				comparingVals.add(tree[left]);
				left += 1;
			}
			if (right % 2 == 0) {
				comparingVals.add(tree[right]);
				right -= 1;
			}
			
			left /= 2;
			right /= 2;
		}
		
		if (comparingVals.isEmpty()) return 0;
		return Collections.max(comparingVals);
	}
	
	static int findNextYear(int year) {
		int left = 0;
		int right = years.length - 1;
		
		if (year < years[0]) return years[0];
		else if (year > years[right]) return years[right];
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (years[mid] <= year) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		return years[right];
	}
	
	static int findPrevYear(int year) {
		int left = 0;
		int right = years.length - 1;
		
		if (year > years[right]) return years[right];
		else if (year < years[0]) return years[0];
		
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (years[mid] >= year) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right != 0 ? years[right - 1] : years[right];
	}
	
	// cur년에 past년 이후 가장 많은 비가 내렸는지 체크
	static String validateExpectation(int cur, int past) {
		boolean lackOfInfo = false;
		
		// cur or past의 강수량 모름
		boolean knowCur = indexMap.containsKey(cur);
		boolean knowPast = indexMap.containsKey(past);
		if (!knowCur || !knowPast) lackOfInfo = true;
		
		// cur+1 ~ past-1 중 강수량 모르는 연도 있음
		int nextOfPast = findNextYear(past);
		int prevOfCur = findPrevYear(cur);
		
		// past 정보 다음이 cur이고, cur 정보 다음이 past일 때
		if (nextOfPast == cur && prevOfCur == past) {
//			System.out.println("cur=past");
			
			int curRain = tree[indexMap.get(cur)];
			int pastRain = tree[indexMap.get(past)];
			
			if (curRain > pastRain) {
//				System.out.println("false1");
				return "false";
			}
			else if (cur - past == 1) return "true";
			else return "maybe";
		}
				
		if ((indexMap.get(prevOfCur) - indexMap.get(nextOfPast) < prevOfCur - nextOfPast) ||
				nextOfPast != past + 1 || prevOfCur != cur - 1) {
			lackOfInfo = true;
		}
		
		// MAX(nextOfPast ~ prevOfCur) >= cur강수량 이면 false
		// cur 강수량 모르면 pass
		if (knowCur && getMaxRain(nextOfPast, prevOfCur) >= tree[indexMap.get(cur)]) {
//			System.out.println(nextOfPast);
//			System.out.println(prevOfCur);
//			System.out.println(getMaxRain(nextOfPast, prevOfCur));
//			System.out.println("false2");
			return "false";
		}
		
		// past 강수량 < cur 강수량 이면 false
		// past 강수량이나 cur 강수량 모르면 pass
		if (knowPast && knowCur && tree[indexMap.get(past)] < tree[indexMap.get(cur)]) {
//			System.out.println("false3");
			return "false";
		}
		
		// past는 알지만 cur은 모르는 경우
		if (knowPast && !knowCur) {	
			if (past == years[years.length - 1]) lackOfInfo = true;
			else {
				if (getMaxRain(nextOfPast, prevOfCur) >= tree[indexMap.get(past)]) return "false";
				else lackOfInfo = true;
			}
		}
		
		return lackOfInfo ? "maybe" : "true";
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		facts = new int[n][2];
		years = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int year = Integer.parseInt(st.nextToken());
			int rain = Integer.parseInt(st.nextToken());
			facts[i][0] = year;
			facts[i][1] = rain;
			years[i] = year;
		}
		
		
		int m = Integer.parseInt(br.readLine());
		expects = new int[m][2];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int year = Integer.parseInt(st.nextToken());
			int rain = Integer.parseInt(st.nextToken());
			expects[i][0] = year;
			expects[i][1] = rain;
		}
		
		initTree();
				
		for (int[] expect: expects) {
			int past = expect[0];
			int cur = expect[1];
			
			bw.write(validateExpectation(cur, past) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
