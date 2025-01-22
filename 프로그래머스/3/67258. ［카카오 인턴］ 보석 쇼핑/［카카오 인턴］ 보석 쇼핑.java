import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        var gemLog = new HashMap<String, Integer>(); // 보석 종류, window 내부 유지 개수
        for (var gem: gems) {
            if (!gemLog.containsKey(gem)) {
                gemLog.put(gem, 0);
            }
        }
        int gemTypeCount = gemLog.keySet().size();
                
        int start = 0;
        int end = 0;
        var existingGems = new HashSet<String>(); // window에 있는 보석
        
        existingGems.add(gems[0]);
        gemLog.put(gems[0], 1);
        
        int shortest = Integer.MAX_VALUE;
        int[] section = new int[2];
        
        while (!(start == gems.length - 1 && end == gems.length - 1)) {
            if (existingGems.size() < gemTypeCount && end < gems.length - 1) {
                end += 1;
                existingGems.add(gems[end]);
                gemLog.put(gems[end], gemLog.get(gems[end]) + 1);
            } else if (existingGems.size() == gemTypeCount && start < gems.length - 1) {
                int gemNum;
                while (true) {
                    gemNum = gemLog.get(gems[start]);
                    if (gemNum == 1) break;
                    gemLog.put(gems[start], gemNum - 1);
                    start += 1;
                }
                
                if (end - start < shortest) {
                    shortest = end - start;
                    section[0] = start + 1;
                    section[1] = end + 1;
                }
                
                existingGems.remove(gems[start]);
                gemLog.put(gems[start], gemNum - 1);
                start += 1;
            } else if (end < gems.length - 1) {
                end += 1;
                existingGems.add(gems[end]);
                gemLog.put(gems[end], gemLog.get(gems[end]) + 1);
            } else if (start < gems.length - 1) {
                int gemNum = gemLog.get(gems[start]);
                if (gemNum == 1) {
                    existingGems.remove(gems[start]);
                }
                gemLog.put(gems[start], gemNum - 1);
                start += 1;
            }
        }
        
        return section;
    }
}