default_val = [0, 1, 2, 3, 4, 5, 5, 4, 3, 2]

def calc_min(storey):
    if storey < 10:
        return default_val[storey]
    
    stand = 1
    while stand <= storey:
        stand *= 10
    stand //= 10
    
    highest_num = storey // stand
    highest = highest_num * stand
    
    if storey % stand == 0:
        return default_val[highest_num]
    
    case1 = default_val[highest_num] + calc_min(storey - highest)
    case2 = default_val[(highest_num + 1 if highest_num + 1 != 10 else 1)] + calc_min(abs(storey - (highest_num + 1) * stand))
        
    return min(case1, case2)

def solution(storey):
    result = calc_min(storey)
    
    return result