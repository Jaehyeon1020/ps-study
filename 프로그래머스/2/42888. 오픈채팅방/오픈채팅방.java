import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        var results = new ArrayList<String>();
        var users = new HashMap<String, String>(); // uid, username pair
        var records = Arrays.stream(record).map(r -> r.split(" ")).toArray(String[][]::new);
        
        for (var info: records) {
            handleRecord(users, info);
        }
        
        for (var info: records) {
            var command = info[0];
            var uid = info[1];
            var username = users.get(uid);
            
            switch (command) {
                case "Enter":
                    results.add(username + "님이 들어왔습니다.");
                    break;
                case "Leave":
                    results.add(username + "님이 나갔습니다.");
                    break;
            }
        }
        
        return results.toArray(new String[0]);
    }
    
    void handleRecord(HashMap<String, String> users, String[] info) {
        var command = info[0];
        var uid = info[1];
        
        if (command.equals("Enter") || command.equals("Change")) {
            var username = info[2];
            users.put(uid, username);
        }
    }
}