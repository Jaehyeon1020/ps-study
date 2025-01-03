import java.util.*;

class Solution {
    String[] splitFilename(String filename) {
        String head = "" + filename.charAt(0);
        String number = "";
        String tail = "";
        
        boolean headOver = false;
        
        for (int i = 1; i < filename.length(); i++) {
            char cur = filename.charAt(i);
            if (!headOver && !Character.isDigit(cur)) {
                head += cur;
            } else if (Character.isDigit(cur)) {
                if (!headOver) headOver = true;
                number += cur;
            } else {
                tail += filename.substring(i, filename.length());
                break;
            }
        }
        
        return new String[]{head, number, tail};
    }
    
    int compare(String comp, String filename, HashMap<String, String[]> fileMap) {        
        // head 비교
        var compHead = fileMap.get(comp)[0];
        var filenameHead = fileMap.get(filename)[0];
        var compareResult = compHead.toLowerCase().compareTo(filenameHead.toLowerCase());
        if (compareResult != 0) {
            return compareResult;
        }
        
        // number 비교
        var compNumber = Integer.parseInt(fileMap.get(comp)[1]);
        var filenameNumber = Integer.parseInt(fileMap.get(filename)[1]);
        if (compNumber < filenameNumber) {
            return -1;
        } else if (compNumber > filenameNumber) {
            return 1;
        }
        
        // 모두 같은 경우 0 반환
        return 0;
    }
    
    public String[] solution(String[] files) {
        var fileMap = new HashMap<String, String[]>();
        
        for (var filename: files) {
            fileMap.put(filename, splitFilename(filename));
        }
        
        Arrays.sort(files, (f1, f2) -> compare(f1, f2, fileMap));
                
        return files;
    }
}