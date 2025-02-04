import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int[][] map; // -1: empty, 0: 고슴도치, 1: 물, 2: 돌, 3: 비버의 굴
	static int minTime = Integer.MAX_VALUE;
	static ArrayList<int[]> waterPos = new ArrayList<>();
	
	static void watering() {
		ArrayList<int[]> tmp = new ArrayList<>();
		
		int[] dx = new int[] {0, 0, 1, -1};
		int[] dy = new int[] {1, -1, 0, 0};
		
		for (int[] pos: waterPos) {
			int x = pos[0];
			int y = pos[1];
			for (int i = 0; i < 4; i++) {
				int newX = x + dx[i];
				int newY = y + dy[i];
				
				if (newX >= 0 && newX < map.length && newY >= 0 && newY < map[0].length) {
					if (map[newX][newY] != 1 && map[newX][newY] != 2 && map[newX][newY] != 3) {
						map[newX][newY] = 1;
						tmp.add(new int[] {newX, newY});
					}
				}
			}
		}
		
		waterPos = tmp;
	}
	
	static void bfs(int x, int y) {
		ArrayDeque<int[]> q = new ArrayDeque<>(); // x, y, time
		q.addLast(new int[] {x, y, 0});
		boolean[][] visited = new boolean[map.length][map[0].length];
		
		int[] dx = new int[] {0, 0, 1, -1};
		int[] dy = new int[] {1, -1, 0, 0};
		
		int time = -1;
		while (q.size() > 0) {
//			System.out.println(q.toString());
			
			int[] cur = q.removeFirst();
			int curX = cur[0];
			int curY = cur[1];
			int curTime = cur[2];
			
			if (curTime != time) {
				time = curTime;
				watering();
			}
			
			if (map[curX][curY] == 3) minTime = Math.min(minTime, curTime);
			
			for (int i = 0; i < 4; i++) {
				if (curX + dx[i] >= 0 && curX + dx[i] < map.length && curY + dy[i] >= 0 && curY + dy[i] < map[0].length) {
					if (map[curX + dx[i]][curY + dy[i]] != 1 && map[curX + dx[i]][curY + dy[i]] != 2 && !visited[curX + dx[i]][curY + dy[i]]) {
						visited[curX + dx[i]][curY + dy[i]] = true;
						q.addLast(new int[] {curX + dx[i], curY + dy[i], curTime + 1});
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		int x=0, y=0;
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				char c = line.charAt(j);
				if (c == '.') map[i][j] = -1;
				else if (c == 'S') {
					map[i][j] = 0;
					x = i;
					y = j;
				}
				else if (c == '*') {
					map[i][j] = 1;
					waterPos.add(new int[] {i, j});
				}
				else if (c == 'X') map[i][j] = 2;
				else if (c == 'D') map[i][j] = 3;
			}
		}
		br.close();
		
		bfs(x, y);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(minTime == Integer.MAX_VALUE ? "KAKTUS" : Integer.toString(minTime));
		bw.close();
	}
}
