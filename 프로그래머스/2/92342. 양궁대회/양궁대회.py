from itertools import combinations_with_replacement

def get_diff(apeach_shot, ryan_shot):
    apeach = 0
    ryan = 0
    
    for i in range(11):
        apeach_cnt = apeach_shot[i]
        ryan_cnt = ryan_shot[i]
        
        if apeach_cnt == ryan_cnt == 0:
            continue
        
        if apeach_cnt >= ryan_cnt:
            apeach += 10 - i
        else:
            ryan += 10 - i
    
    return ryan - apeach

def can_update(max_shot, cur_shot):
    if not max_shot:
        return True
    
    for i in range(10, -1, -1):
        if cur_shot[i] > max_shot[i]:
            return True
        elif cur_shot[i] < max_shot[i]:
            return False
    return True

def solution(n, info):
    max_shot = []
    max_diff = -1
    
    combinations = list(combinations_with_replacement(range(0, 11), n))
    
    for combi in combinations:
        shot = [0] * 11
        for score in combi:
            shot[10 - score] += 1
        
        diff = get_diff(info, shot) # 라이언 점수 - 어피치 점수
        if diff > 0 and diff == max_diff:
            if can_update(max_shot, shot): max_shot = shot 
        elif diff > 0 and diff > max_diff:
            max_shot = shot
            max_diff = diff
    
    return max_shot if max_shot else [-1]