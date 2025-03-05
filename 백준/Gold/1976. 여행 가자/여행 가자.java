import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static HashSet<Integer>[] graph;
    static HashSet<Integer> plannedCities;
    static int firstCity;
    static boolean[] visited;

    static void bfs(int startCity) {
        visited = new boolean[N + 1];

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addLast(startCity);
        visited[startCity] = true;

        while (!q.isEmpty()) {
            int curCity = q.removeFirst();

            for (int nextCity: graph[curCity]) {
                if (visited[nextCity]) continue;

                q.addLast(nextCity);
                visited[nextCity] = true;
            }
        }
    }

    static boolean check() {
        for (int city: plannedCities) {
            if (!visited[city]) return false;
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new HashSet[N + 1];
        plannedCities = new HashSet<>();

        for (int i = 1; i <= N; i++) graph[i] = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int city = Integer.parseInt(st.nextToken());
            if (i == 0) firstCity = city;
            plannedCities.add(city);
        }

        bfs(firstCity);

        boolean canPlanTrip = check();

        String answer = canPlanTrip ? "YES" : "NO";
        bw.write(answer);
        bw.flush();

        bw.close();
        br.close();
    }
}
