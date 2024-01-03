def solution(n, lost, reserve):
    
    join = [True for x in range(n + 1)]
    
    for l in lost:
        if not l in reserve:
            join[l] = False # 아예 체육복이 없는 상태를 의미
    
    for i in range(1, n + 1):
        if join[i] == False:
            if i - 1 >= 1 and (i - 1) in reserve and (i - 1) not in lost:
                join[i] = True
                reserve.remove(i - 1)
            elif i + 1 <= n and (i + 1) in reserve and (i + 1) not in lost:
                join[i] = True
                reserve.remove(i + 1)
    
    return join.count(True) - 1