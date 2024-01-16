def solution(N, stages):
    answer = []
    
    participants = {}
    fail = {}
    for i in range(1, N + 1):
        participants[i] = 0
        fail[i] = 0
        
    # 1 ~ N + 1 stage
    for stage in range(1, N + 2):
        c = stages.count(stage)
        
        if stage == N + 1:
            for i in range(1, N + 1):
                participants[i] += c
        else:
            fail[stage] += c
            for i in range(1, stage + 1):
                participants[i] += c
    
    fail_rates = {}
    for i in range(1, N + 1):
        if participants[i] != 0:
            fail_rates[i] = fail[i] / participants[i]
        else:
            fail_rates[i] = 0
    
    sorted_rates = sorted(fail_rates.items(), key=lambda x: (-x[1], x[0]))
    
    for s in sorted_rates:
        answer.append(s[0])
    
    return answer