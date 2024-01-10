def solution(bandage, health, attacks):    
    sustain = 0
    last_attack_time = attacks[-1][0]
    current_attack_index = 0
    max_health = health
    
    for t in range(1, last_attack_time + 1):
        if current_attack_index == len(attacks):
            return health
        
        # 몬스터 공격 여부 확인
        cur_attack = attacks[current_attack_index]
        if cur_attack[0] == t:
            sustain = 0
            current_attack_index += 1
            health -= cur_attack[1]
            if health <= 0: 
                return -1
            continue
        
        # 공격 당하지 않는 경우
        sustain += 1
        if sustain == bandage[0]:
            sustain = 0
            health += bandage[2]
            
        health += bandage[1]
        if health > max_health:
            health = max_health
    
    return health
        