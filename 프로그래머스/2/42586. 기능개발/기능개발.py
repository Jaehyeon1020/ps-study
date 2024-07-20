from collections import deque

def solution(progresses, speeds):
    answer = []
    
    progress_q = deque(progresses)
    speeds_q = deque(speeds)
    while progress_q:
        count = 0
        while progress_q and progress_q[0] >= 100:
            if progress_q:
                progress_q.popleft()
                speeds_q.popleft()
                count += 1  
        if count > 0:
            answer.append(count)
            
        for i in range(len(progress_q)):
            progress_q[i] += speeds_q[i]   
        
        
    return answer
        
                
            