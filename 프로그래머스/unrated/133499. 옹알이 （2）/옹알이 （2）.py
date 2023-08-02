def check(w, target):
    count = 0
    
    i = 0
    while i <= len(target) - len(w):
        if target[i: i+len(w)] == w:
            count += 1
            i += len(w)
        else:
            i += 1
    
    return count

def solution(babbling):
    answer = 0
    
    can_say = ["aya", "ye", "woo", "ma"]
    for bab in babbling:
        fail = False
        total_len = 0
        
        for word in can_say:
            count = check(word, bab)
            
            if count >= 2 and check(word*2, bab) > 0:
                fail = True
            else:
                total_len += count * len(word)
        
        if not fail and total_len == len(bab):
            answer += 1
    
    return answer