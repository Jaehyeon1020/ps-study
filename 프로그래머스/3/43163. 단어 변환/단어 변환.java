import java.util.*;

class Solution {
    int answer;
    String gTarget;
    String[] gWords;
    HashSet<String> wordSet;
    
    boolean canConvert(String src, String dest) {
        // target과 겹치는 글자 수가 같거나 1개 많아져야 전환 가능
        // 두 String 간 차이는 반드시 1이어야 함
        int srcContaining = 0;
        int destContaining = 0;
        
        // src - target 비교
        for (int i = 0; i < src.length(); i++) {
            if (gTarget.charAt(i) == src.charAt(i)) srcContaining += 1;
        }
        
        // dest - target 비교
        for (int i = 0; i < dest.length(); i++) {
            if (gTarget.charAt(i) == dest.charAt(i)) destContaining += 1;
        }
        
        // src - dest 간 비교
        int srcDestDiff = 0;
        for (int i = 0; i < src.length(); i++) {
            if (src.charAt(i) != dest.charAt(i)) srcDestDiff += 1;
        }
        
        int result = destContaining - srcContaining;
        return (result == 0 || result == 1) && srcDestDiff == 1 ? true : false;
    }
    
    void dfs(int step, HashSet<String> used, String cur) {
        // System.out.println("step" + step + " cur " + cur);
        if (step >= answer && answer != 0) return;
        if (cur.equals(gTarget)) {
            if (step < answer || answer == 0) answer = step;
            return;
        }
        
        for (var word: wordSet) {
            if (!used.contains(word) && canConvert(cur, word)) {
                used.add(word);
                dfs(step + 1, used, word);
                used.remove(word);
            }
        }
    }
    
        
    public int solution(String begin, String target, String[] words) {
        answer = 0;
        gTarget = target;
        gWords = words;
        wordSet = new HashSet<String>();
        for (var word: words) {
            wordSet.add(word);
        }
        
        if (!wordSet.contains(target)) return 0;
        
        dfs(0, new HashSet<String>(), begin);
        
        return answer;
    }
}