def solution(strings, n):
    answer = []
     
    for s in strings:
        if len(answer) == 0:
            answer.append(s)
            continue
            
        for index, target in enumerate(answer):
            if s[n] < target[n]:
                answer.insert(index, s)
                break
            elif s[n] == target[n]:
                if sorted([s, target]) == [s, target]:
                    answer.insert(index, s)
                    break
            
            if index == len(answer) - 1:
                answer.append(s)
                break
    
    return answer