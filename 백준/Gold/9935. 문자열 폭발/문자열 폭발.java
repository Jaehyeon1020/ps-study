import java.util.*;
import java.io.*;

public class Main {
    static String target;
    static String bomb;

    static String explosion() {
        ArrayDeque<Character> stack1 = new ArrayDeque<>();
        ArrayDeque<Character> stack2 = new ArrayDeque<>();
        ArrayDeque<Character> buffer = new ArrayDeque<>();

        char lastChar = bomb.charAt(bomb.length() - 1);
        char[] bombCharArray = bomb.toCharArray();

        for (char c: target.toCharArray()) {
            stack1.push(c);

            if (stack1.size() >= bomb.length() && stack1.peek() == lastChar) {
                for (int i = 0; i < bomb.length(); i++) {
                    stack2.push(stack1.pop());
                }

                for (char bombChar: bombCharArray) {
                    char cur = stack2.pop();

                    buffer.addLast(cur);
                    if (bombChar != cur) {
                        while (!buffer.isEmpty()) stack1.push(buffer.removeFirst());
                        while (!stack2.isEmpty()) stack1.push(stack2.pop());
                        break;
                    }
                }
                if (!buffer.isEmpty()) buffer.clear();
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        while (!stack2.isEmpty()) {
            sb.append(stack2.pop());
        }

        return sb.length() > 0 ? sb.toString() : "FRULA";
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        target = br.readLine();
        bomb = br.readLine();

        String answer = explosion();

        bw.write(answer);
        bw.flush();

        bw.close();
        br.close();
    }

}
