def solution(participant, completion):    
    d = {}
    
    for p in participant:
        if p in d:
            d[p] += 1
        else:
            d[p] = 0
    
    for c in completion:
        if c not in d:
            return c
        d[c] -= 1
    
    return max(d, key=d.get)