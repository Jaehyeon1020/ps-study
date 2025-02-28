import java.util.*;
import java.io.*;

public class Main {
    static char getWinner(String map) {
        HashSet<Character> winners = new HashSet<>();

        int[] vertical = new int[]{0, 1, 2};
        int[] horizon = new int[]{0, 3, 6};

        // 세로 방향 확인
        for (int start: vertical) {
            if (map.charAt(start) == map.charAt(start + 3) &&
                    map.charAt(start + 3) == map.charAt(start + 6)
            ) {
                if (map.charAt(start) == 'X') winners.add('X');
                else if (map.charAt(start) == 'O') winners.add('O');
            }
        }

        // 가로 방향 확인
        for (int start: horizon) {
            if (map.charAt(start) == map.charAt(start + 1)
                    && map.charAt(start + 1) == map.charAt(start + 2)) {
                if (map.charAt(start) == 'X') winners.add('X');
                else if (map.charAt(start) == 'O') winners.add('O');
            }
        }

        // 대각선 방향 확인
        if (map.charAt(0) == map.charAt(4)
                && map.charAt(4) == map.charAt(8)) {
            if (map.charAt(0) == 'X') winners.add('X');
            else if (map.charAt(0) == 'O') winners.add('O');
        }
        if (map.charAt(2) == map.charAt(4)
                && map.charAt(4) == map.charAt(6)) {
            if (map.charAt(2) == 'X') winners.add('X');
            else if (map.charAt(2) == 'O') winners.add('O');
        }

        if (winners.size() > 1) {
            return 'E'; // 둘 다 승자에 있음 => 말이 안되는 상황
        } else if (winners.size() == 1) {
            return winners.contains('O') ? 'O' : 'X';
        } else {
            return '.';
        }
    }

    static boolean validate(String map) {
        int countO = 0;
        int countX = 0;
        for (char c: map.toCharArray()) {
            if (c == 'O') countO += 1;
            else if (c == 'X') countX += 1;
        }

        // 승자 계산
        char winner = getWinner(map);
        if (winner == 'E') return false;

        // 게임판 꽉 참:
        // X의 개수가 O의 개수보다 하나 더 많아야 함
        // 무승부이거나 X가 승리해야 함
        if (countO + countX == 9) {
            return countX == countO + 1 && (winner == 'X' || winner == '.');
        }

        // 게임판 꽉 차지 않은 경우:
        // 승자가 결정나지 않았다면 종료 불가
        // O가 승자인 경우 X와 O의 개수 같음
        // X가 승자인 경우 X가 O보다 하나 더 많이 존재
        if (winner == '.') return false;
        else if (winner == 'O') {
            return countX == countO;
        } else {
            return countX == countO + 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String input = br.readLine();

            if (input.equals("end")) break;

            boolean isValid = validate(input);

            if (isValid) bw.write("valid\n");
            else bw.write("invalid\n");
        }
        bw.flush();

        bw.close();
        br.close();
    }
}
