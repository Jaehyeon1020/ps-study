import java.util.*;

class Solution {
    int getAlphaDistance(char target) {
        return Math.min(target - 'A', 'Z' - target + 1);
    }
    
    // index 0에 있는 커서를 index i로 이동시키는 최소거리
    int initCursor(int i, int last) {
        return Math.min(i, last - i + 1);
    }
    
    public int solution(String name) {
        var len = name.length();
        
        var alphaMoved = 0;
        for (int i = 0; i < len; i++) {
            alphaMoved += getAlphaDistance(name.charAt(i));
        }
        
        var notA = 0;
        for (int i = 0; i < len; i++) if (name.charAt(i) != 'A') notA += 1;
        
        var cursorMoved = len - 1;
        for (int cursor = 0; cursor < len; cursor++) {
            var init = initCursor(cursor, len - 1);
            
            var notACount = 0;
            var index = cursor; // 커서를 옮긴 위치
            var control = 0; // 커서 옮긴 횟수
            
            // 오른쪽으로 돌기
            while (true) {
                if (name.charAt(index) != 'A') notACount += 1;
                if (notACount == notA) break;
                
                if (index + 1 == len) {
                    index = 0;
                } else index += 1;
                
                control += 1;
            }
            cursorMoved = Math.min(cursorMoved, control + init);
            
            // 왼쪽으로 돌기
            notACount = 0;
            index = cursor;
            control = 0;
            while (true) {
                if (name.charAt(index) != 'A') notACount += 1;
                if (notACount == notA) break;
                
                if (index - 1 == -1) {
                    index = len - 1;
                } else index -= 1;
                
                control += 1;
            }
            cursorMoved = Math.min(cursorMoved, control + init);
        }
        
        return alphaMoved + cursorMoved;
    }
}