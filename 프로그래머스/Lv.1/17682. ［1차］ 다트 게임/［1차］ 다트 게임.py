def solution(dartResult):
    score = [0, 0, 0]
    cur = -1
    power = {'S': 1, 'D': 2, 'T': 3}
    continue_flag = False
    
    for i in range(len(dartResult)):
        if continue_flag:
            continue_flag = False
            continue
        
        try:
            # score 10
            if dartResult[i] == '1' and dartResult[i + 1] == '0':
                cur += 1
                score[cur] = 10
                i += 1
                continue_flag = True
                continue
            
            # score 0-9
            cur_score = int(dartResult[i])
            cur += 1
            score[cur] = cur_score
        except:
            if dartResult[i] in ['S', 'D', 'T']:
                score[cur] = score[cur] ** power[dartResult[i]]
            else:
                if dartResult[i] == '*':
                    score[cur] *= 2
                    if cur > 0:
                        score[cur - 1] *= 2
                elif dartResult[i] == '#':
                    score[cur] *= -1
    
    
    return sum(score)