import bisect
from collections import deque

def solution(k, score):
    answer = []
    k_list = deque([])
    
    for s in score:
        if len(k_list) < k:
            bisect.insort(k_list, s)
            answer.append(k_list[0])
            continue
            
        bisect.insort(k_list, s)
        k_list.popleft()
        answer.append(k_list[0])
    
    return answer