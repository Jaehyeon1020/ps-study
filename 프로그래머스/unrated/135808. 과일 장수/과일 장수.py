def solution(k, m, score):
    answer = 0
    
    remain = len(score)
    inbox = 0
    box = []
    score.sort(reverse=True)
    
    for s in score:
        box.append(s)
        inbox += 1
        
        if inbox == m:
            mini = min(box)
            answer += mini * m
            inbox = 0
            box.clear()
    
    return answer