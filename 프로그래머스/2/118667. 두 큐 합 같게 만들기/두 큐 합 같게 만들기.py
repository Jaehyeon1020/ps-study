from collections import deque

def solution(queue1, queue2):
    answer = 0
    
    q_len = len(queue1)
    q1 = deque(queue1)
    q2 = deque(queue2)
    q1_sum = sum(queue1)
    q2_sum = sum(queue2)
    
    if (q1_sum + q2_sum) % 2 == 1:
        return -1
    
    cnt = 0
    task = 0
    while cnt < q_len * 4:
        if q1_sum > q2_sum:
            q1_num = q1.popleft()
            q2.append(q1_num)
            q1_sum -= q1_num
            q2_sum += q1_num
            task += 1
        elif q2_sum > q1_sum:
            q2_num = q2.popleft()
            q1.append(q2_num)
            q2_sum -= q2_num
            q1_sum += q2_num
            task += 1
        else:
            return task
        cnt += 1
    
    return -1