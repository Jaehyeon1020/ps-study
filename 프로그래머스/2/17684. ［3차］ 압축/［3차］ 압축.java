import java.util.*;

class Solution {
    public int[] solution(String msg) {
        var answerArray = new ArrayList<Integer>();
        var dictionary = new HashMap<String, Integer>();
        
        int index = 1;
        for (int i = (int)'A'; i <= (int)'Z'; i++) {
            dictionary.put(Character.toString((char)i), index++);
        }
        
        int pointer = 0;
        String token = "";
        while (pointer < msg.length()) {
            String cur = Character.toString(msg.charAt(pointer));
            
            if (dictionary.containsKey(token + cur) && pointer == msg.length() - 1) {
                answerArray.add(dictionary.get(token + cur));
                break;
            }
            
            // 뒷글자까지 이어서 사전에 있으면 계속 진행
            // 사전에 없으면 그 전까지 토큰 번호 answer에 추가, 현재 토큰은 dict에 추가
            if (dictionary.containsKey(token + cur)) {
                token += cur;
                pointer += 1;
            } else {
                answerArray.add(dictionary.get(token));
                dictionary.put(token + cur, index++);
                token = "";
            }
        }
        
        return answerArray.stream().mapToInt(Integer::intValue).toArray();
    }
}