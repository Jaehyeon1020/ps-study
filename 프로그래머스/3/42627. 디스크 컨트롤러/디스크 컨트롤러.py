import heapq
from collections import deque

def solution(jobs):
    answer = 0
    jobs_count = len(jobs)
    time = 0
    runnables = []
    cur_running = [] # callTime, startTime, endTime
    
    jobs.sort()
    jobs = deque(jobs)
    
    while jobs or runnables or cur_running:
        while jobs and jobs[0][0] == time:
            call_time, running_time = jobs.popleft()
            heapq.heappush(runnables, [running_time, call_time])
            
        if cur_running and cur_running[2] == time:
            answer += cur_running[2] - cur_running[0]
            cur_running = []
            
        if not cur_running and runnables:
            running_time, call_time = heapq.heappop(runnables)
            cur_running.extend([call_time, time, time + running_time])
        
        time += 1 
    
    return answer // jobs_count