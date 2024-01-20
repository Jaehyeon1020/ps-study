def solution(survey, choices):
    answer = ''
    
    score = {'R': 0, 'T': 0, 'C': 0, 'F': 0, 'J': 0, 'M': 0, 'A': 0, 'N': 0}
    
    for i in range(len(survey)):
        first = survey[i][0]
        second = survey[i][1]
        choice = choices[i]
        
        if 1 <= choice <= 3:
            score[first] += (4 - choice)
        elif 5 <= choice <= 7:
            score[second] += (choice - 4)
    
    pair = [['R', 'T'], ['C', 'F'], ['J', 'M'], ['A', 'N']]
    for p in pair:
        fir = p[0]
        sec = p[1]
        
        if score[fir] > score[sec]:
            answer += fir
        elif score[fir] < score[sec]:
            answer += sec
        else:
            answer += sorted(p)[0]
    
    return answer