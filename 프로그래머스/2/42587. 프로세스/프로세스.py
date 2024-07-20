from collections import deque

def solution(priorities, location):
    pair_priorities = deque()
    for i, p in enumerate(priorities):
        pair_priorities.append((i, p))
        
    count = 1
    while pair_priorities:
        cur_i, cur_p = pair_priorities.popleft()
        
        re_append = False
        for next_i, next_p in pair_priorities:
            if next_p > cur_p:
                re_append = True
        
        if re_append:
            pair_priorities.append((cur_i, cur_p))
        elif cur_i == location:
            return count
        else:
            count += 1