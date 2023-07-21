def solution(cards1, cards2, goal):
    idx_1 = 0
    idx_2 = 0
    
    for word in goal:
        if idx_1 < len(cards1) and cards1[idx_1] == word:
            idx_1 += 1
        elif idx_2 < len(cards2) and cards2[idx_2] == word:
            idx_2 += 1
        else:
            return "No"
    
    return "Yes"