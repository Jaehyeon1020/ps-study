import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static HeightChange[] changes;
    static int count = 0;

    static class HeightChange {
        int num;
        int height;

        public HeightChange(int n, int h) {
            this.num = n;
            this.height = h;
        }
    }

    static void countBuildings() {
        ArrayDeque<HeightChange> stack = new ArrayDeque<>();

        for (HeightChange cur: changes) {
            if (stack.isEmpty() || cur.height > stack.peek().height) stack.push(cur);
            else if (!stack.isEmpty() && cur.height < stack.peek().height) {
                while (!stack.isEmpty() && cur.height < stack.peek().height) {
                    if (stack.pop().height != 0) count += 1;
                }
                if (stack.isEmpty() || stack.peek().height < cur.height) stack.push(cur);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.pop().height != 0) count += 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        changes = new HeightChange[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            changes[i] = new HeightChange(n, h);
        }

        countBuildings();

        bw.write(Integer.toString(count));
        bw.flush();

        bw.close();
        br.close();
    }
}
