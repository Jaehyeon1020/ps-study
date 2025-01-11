import java.util.*;

class Solution {
    int getRepeated(int target, int count) {
        return Integer.parseInt(Integer.toString(target).repeat(count));
    }
    
    public int solution(int N, int number) {
        // 1개 사용
        // 5
        
        // 2개 사용
        // 55
        // 5 + 5 = 10, 5 - 5 = 0, 5 * 5 = 25, 5 / 5 = 1
        
        // 3개 사용
        // 555
        // 55 + 5, 55 - 5, 55 * 5, 55 / 5
        // (5 + 5) + 5, (5 + 5) - 5, (5 + 5) * 5, (5 + 5) / 5
        // 5 - 5 + 5, 5 - 5 - 5, 5 - 5 * 5, 5 - 5 / 5
        // 5 * 5 + 5, 5 * 5 - 5, 5 * 5 * 5, 5 * 5 / 5
        // 5 / 5 + 5, 5 / 5 - 5, 5 / 5 * 5, 5 / 5 / 5 
        
        // 4개 사용
        // 5555
        // 555 + 5, ...
        // 55 + 55 -> 확인
        
        // 5개 사용
        // 555 + 55, ...
        
        // 6개 사용
        // 5555 + 55, 555 + 555, ...
        
        if (N == number) return 1;
        
        var dp = new HashMap<Integer, HashSet<Integer>>();
        for (int i = 1; i < 9; i++) {
            dp.put(i, new HashSet<Integer>());
        }
        
        var init = dp.get(1);
        init.add(N);
        for (int i = 2; i < 9; i++) {
            var cur = dp.get(i);
            cur.add(getRepeated(N, i));
            
            for (var j = 1; j < i; j++) {
                var prev1 = dp.get(j);
                var prev2 = dp.get(i - j);
                
                for (var prev1Num: prev1) {
                    for (var prev2Num: prev2) {
                        cur.add(prev1Num + prev2Num);
                        cur.add(prev1Num - prev2Num);
                        cur.add(prev2Num - prev1Num);
                        cur.add(prev1Num * prev2Num);
                        if (prev2Num != 0) cur.add(prev1Num / prev2Num);
                        if (prev1Num != 0) cur.add(prev2Num / prev1Num);
                    }
                }
            }
            
            if (cur.contains(number)) return i;
        }
        return -1;
    }
}