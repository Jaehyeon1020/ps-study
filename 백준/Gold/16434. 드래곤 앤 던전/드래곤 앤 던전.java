import java.util.*;
import java.io.*;

public class Main {
    static int N, initAtk;
    static Room[] rooms;

    static class Room {
        int type;
        int atk;
        int health;

        public Room(int t, int a, int h) {
            this.type = t;
            this.atk = a;
            this.health = h;
        }
    }

    static boolean canFinishWithHealth(long h) {
        long curHp = h;
        long curAtk = initAtk;

        for (Room stage: rooms) {
            if (stage.type == 1) {
                // 몬스터와 싸우기
                long monsterAtk = stage.atk;
                long monsterHp = stage.health;

                // 용사가 몬스터를 죽일 때까지 때린 횟수
                // 몬스터는 용사를 (용사가 때린 횟수 - 1)번 때림
                long attacks = monsterHp % curAtk == 0 ? monsterHp / curAtk : monsterHp / curAtk + 1;
                curHp -= (attacks - 1) * monsterAtk;

                if (curHp <= 0) return false;
            } else {
                // 공격력, 생명력 회복
                curAtk += stage.atk;
                curHp += stage.health;
                if (curHp > h) curHp = h;
            }
        }
        return true;
    }

    static long getMinMaxHp() {
        long left = 1L;
        long right = 123456L * 1000000 * 1000000 + 1;

        while (left < right) {
            long mid = (left + right) / 2;

            if (canFinishWithHealth(mid)) {
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        initAtk = Integer.parseInt(st.nextToken());
        rooms = new Room[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            rooms[i] = new Room(t, a, h);
        }

        long minMaxHp = getMinMaxHp();
        bw.write(Long.toString(minMaxHp));
        bw.flush();

        bw.close();
        br.close();
    }
}
