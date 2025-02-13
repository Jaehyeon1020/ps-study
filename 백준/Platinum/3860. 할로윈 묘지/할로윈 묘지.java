// 3860 할로윈 묘지

import java.util.*;
import java.io.*;

public class Main {
	static int W, H;
	static int G;
	static int E;
	static boolean[][] cannotGo;
	static boolean[][] ghostHole;
	static ArrayList<Edge> edges;
	static int[][] distance;
	static int ripCount;
	
	static class Pos {
		int x;
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge {
		Pos from;
		Pos to;
		int dist;
		
		public Edge(Pos f, Pos t, int d) {
			this.from = f;
			this.to = t;
			this.dist = d;
		}
	}
	
	static boolean bellman() {
		distance = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		distance[0][0] = 0;
		
		int nodeNum = H * W - ripCount;
		
		for (int i = 0; i < nodeNum - 1; i++) {
			for (Edge e: edges) {
				if (distance[e.from.x][e.from.y] == Integer.MAX_VALUE) continue;
				
				if (distance[e.to.x][e.to.y] > distance[e.from.x][e.from.y] + e.dist) {
					distance[e.to.x][e.to.y] = distance[e.from.x][e.from.y] + e.dist;
				}
			}
		}
				
		// cycle
		for (Edge e: edges) {
//			if (e.dist < 0 && e.to.x == H - 1 && e.to.y == W - 1) continue; // 음의 사이클이 있더라도 도착 지점으로 가면 제외
			if (distance[e.from.x][e.from.y] == Integer.MAX_VALUE) continue;
			
			if (distance[e.to.x][e.to.y] > distance[e.from.x][e.from.y] + e.dist) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if (W == 0 && H == 0) break;
			
			// 묘비 추가
			ripCount = 0;
			cannotGo = new boolean[H][W];
			G = Integer.parseInt(br.readLine());
			for (int i = 0; i < G; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				cannotGo[y][x] = true;
				ripCount += 1;
			}
			
			// 귀신구멍 간선 추가
			ghostHole = new boolean[H][W];
			edges = new ArrayList<>();
			E = Integer.parseInt(br.readLine());
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				Edge e = new Edge(new Pos(y1, x1), new Pos(y2, x2), t);
				edges.add(e);
				ghostHole[y1][x1] = true;
			}
			
			// 나머지 간선 추가
			int[] dx = new int[] {1, -1, 0, 0};
			int[] dy = new int[] {0, 0, -1, 1};
			for (int x = 0; x < cannotGo.length; x++) {
				for (int y = 0; y < cannotGo[0].length; y++) {
					if (ghostHole[x][y]) continue;
					if (x == H -1 && y == W - 1) continue;
					
					for (int i = 0; i < 4; i++) {
						int newX = x + dx[i];
						int newY = y + dy[i];
						
						if (newX >= 0 && newX < cannotGo.length
								&& newY >= 0 && newY < cannotGo[0].length) {
							if (cannotGo[newX][newY]) continue;
							
							Edge e = new Edge(new Pos(x, y), new Pos(newX, newY), 1);
							edges.add(e);
						}
					}
				}
			}
			
			boolean isFailed = bellman();
			
			if (isFailed) bw.write("Never\n");
			else if (distance[H - 1][W - 1] == Integer.MAX_VALUE) bw.write("Impossible\n");
			else bw.write(Integer.toString(distance[H - 1][W - 1]) + "\n");
		}
		bw.flush();
		
		bw.close();
		br.close();
	}

}
