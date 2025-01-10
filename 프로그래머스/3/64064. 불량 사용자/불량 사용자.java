import java.util.*;

class Solution {
    Set<String> idSet = new HashSet<String>();
    
    boolean canBan(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) return false;
        
        for (int i = 0; i < userId.length(); i++) {
            if (userId.charAt(i) != bannedId.charAt(i) && bannedId.charAt(i) != '*')
                return false;
        }
        return true;
    }
    
    void dfs(String[] userIds, ArrayDeque<String> bannedIds, boolean[] idUsed, ArrayList<String> checkedIds, int bannedIdSize) {
        if (bannedIds.size() == 0) {
            if (checkedIds.size() == bannedIdSize) {
                Collections.sort(checkedIds);
                idSet.add(checkedIds.toString());
            }
            return;
        }
        
        var bannedId = bannedIds.removeFirst();
        for (int i = 0; i < userIds.length; i++) {
            var userId = userIds[i];
            if (!idUsed[i] && canBan(userId, bannedId)) {
                checkedIds.add(userId);
                idUsed[i] = true;
                
                dfs(userIds, bannedIds, idUsed, new ArrayList<String>(checkedIds), bannedIdSize);
                
                idUsed[i] = false;
                checkedIds.remove(checkedIds.size() - 1);
            }
        }
        bannedIds.addFirst(bannedId);
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        var idUsed = new boolean[user_id.length];
        for (int i = 0; i < idUsed.length; i++) idUsed[i] = false; 
        
        var bannedIdDeque = new ArrayDeque<String>();
        for (var bid: banned_id) bannedIdDeque.addLast(bid);
        
        dfs(user_id, bannedIdDeque, idUsed, new ArrayList<String>(), banned_id.length);
        
        return idSet.size();
    }
}