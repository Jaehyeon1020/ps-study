import java.util.*;

class Task {
    int id; // 3
    int arrivalTime; // 1
    int taskTime; // 2
    
    public Task(int id, int at, int tt) {
        this.id = id;
        this.arrivalTime = at;
        this.taskTime = tt;
    }
}

class Solution {
    int compareTask(Task t1, Task t2) {
        // 소요시간 작을수록
        if (t1.taskTime < t2.taskTime) return -1;
        else if (t1.taskTime > t2.taskTime) return 1;
        
        // 요청시각 빠를수록
        if (t1.arrivalTime < t2.arrivalTime) return -1;
        else if (t1.arrivalTime > t2.arrivalTime) return 1;
        
        // 작업 id 작을수록
        if (t1.id < t2.id) return -1;
        else return 1;
    }
    
    public int solution(int[][] jobs) {
        var returnTimes = new ArrayList<Integer>();
        int time = 0;
        var pq = new PriorityQueue<Task>((t1, t2) -> compareTask(t1, t2));
        Arrays.sort(jobs, Comparator.comparing(job -> job[0]));
        
        var i = 0;
        var nextJob = jobs[i];
        while (!(i == jobs.length && pq.size() == 0)) {
            // 요청된 작업 큐에 추가
            while (nextJob[0] <= time && i < jobs.length) {
                pq.add(new Task(i, nextJob[0], nextJob[1]));
                i++;
                if (i < jobs.length) nextJob = jobs[i];
            }
            
            if (pq.size() == 0) {
                time += 1;
                continue;
            }
            
            var task = pq.poll();
            var endTime = time + task.taskTime;
            returnTimes.add(endTime - task.arrivalTime);
            
            time = endTime;
        }
        
        return (int) returnTimes.stream().mapToInt(Integer::intValue).average().orElse(0);
    }
}