def solution(lottos, win_nums):
    match_count = 0
    zero_count = 0
        
    for l in lottos:
        if l != 0 and l in win_nums:
            match_count += 1
        elif l == 0:
            zero_count += 1
            
    # 0이 모두 일치하도록 바뀌는 경우
    best_match_count = match_count + zero_count
    best = 0
    if best_match_count == 0 or best_match_count == 1:
        best = 6
    else:
        best = 6 - (best_match_count -1)
    
    # 0이 모두 불일치하도록 바뀌는 경우
    worst = 0
    if match_count == 0 or match_count == 1:
        worst = 6
    else:
        worst = 6 - (match_count - 1)
        
    return [best, worst]