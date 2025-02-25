import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static ArrayDeque<Board> upside = new ArrayDeque<>();
    static ArrayDeque<Board> downside = new ArrayDeque<>();
    static HashMap<Integer, Board> boardMap = new HashMap<>();

    static int round = 1;

    static class Board {
        int id;
        int health;
        boolean robot;

        public Board(int id, int health) {
            this.id = id;
            this.health = health;
            this.robot = false;
        }
    }

    static void run() {
        while (true) {
            // 벨트 회전
            moveBelt();

            // 로봇 이동
            moveRobots();

            // 로봇 올리기
            Board boardAtFirst = upside.peekFirst();
            if (boardAtFirst.health > 0) {
                boardAtFirst.robot = true;
                boardAtFirst.health -= 1;
            }

            // 과정 종료 여부 판단
            int broken = 0;
            for (Board b: boardMap.values()) {
                if (b.health == 0) broken += 1;
            }
            if (broken >= K) break;

            // 다음 단계 이동
            round += 1;
        }
    }

    static void moveBelt() {
        Board up = upside.removeLast();
        Board down = downside.removeFirst();

        upside.addFirst(down);
        downside.addLast(up);

        if (upside.peekLast().robot) {
            upside.peekLast().robot = false; // 내리는 위치에 도달한 로봇이 있다면 내리기
        }
    }

    static void moveRobots() {
        int curBoardId = getPrevBoardId(upside.peekLast().id);

        // 뒷 칸부터 검사
        for (int i = 0; i < N - 1; i++) {
            Board curBoard = boardMap.get(curBoardId);

            if (curBoard.robot) {
                // 다음 칸의 헬스, 로봇 여부 체크
                // 다음 칸으로 이동 가능하면 로봇 이동
                Board nextBoard = boardMap.get(getNextBoardId(curBoardId));
                if (nextBoard.health > 0 && !nextBoard.robot) {
                    curBoard.robot = false;
                    nextBoard.robot = true;
                    nextBoard.health -= 1;

                    // 로봇이 이동한 위치가 내리는 위치라면 로봇 즉시 내리기
                    if (nextBoard.id == upside.peekLast().id) {
                        nextBoard.robot = false;
                    }
                }
            }

            // 앞 칸으로 이동
            curBoardId = getPrevBoardId(curBoardId);
        }
    }

    static int getNextBoardId(int curBoardId) {
        return curBoardId == 2 * N ? 1 : curBoardId + 1;
    }

    static int getPrevBoardId(int curBoardId) {
        return curBoardId == 1 ? 2 * N : curBoardId - 1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            Board b = new Board(i, Integer.parseInt(st.nextToken()));
            upside.addLast(b);
            boardMap.put(i, b);
        }
        for (int i = N + 1; i <= 2*N; i++) {
            Board b = new Board(i, Integer.parseInt(st.nextToken()));
            downside.addFirst(b);
            boardMap.put(i, b);
        }

        run();

        bw.write(Integer.toString(round));
        bw.flush();

        bw.close();
        br.close();
    }
}
