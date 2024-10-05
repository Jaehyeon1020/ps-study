def solution(temperature, t1, t2, a, b, onboard):
    # 온도 범위 0 - 50으로 만들기
    temperature += 10
    t1 += 10
    t2 += 10
        
    # dp[t][c]: 시간 t 일 때 c도에 도달할 수 있는 최소 비용
    dp = [[100 * 1000 + 1] * 51 for _ in range(len(onboard))] 
    dp[0][temperature] = 0
    
    # 에어컨을 켰을 때 온도 움직이는 방향
    move = 1 if temperature < t1 else -1
    
    # 1. 전 시간대와 동일한 온도를 유지하기 위해 에어컨을 켜는 경우 (온도가 희망온도 내부) dp[t][c] = dp[t-1][c] + b
    # 2. 전 시간대와 동일한 온도를 유지하기 위해 에어컨을 켜지 않는 경우 (외부온도 == 실내온도): dp[t-1][c]
    # 3. 온도를 올리기 위해 에어컨을 켜는 경우 / 온도를 내리기 위해 에어컨을 켜는 경우: dp[t-1][c+1] + a or dp[t-1][c-1] + a
    # 4. 온도를 내리기 위해 에어컨을 끄는 경우 / 온도를 올리기 위해 에어컨을 끄는 경우: dp[t-1][c-1] or dp[t-1][c+1]
    # 네 경우 중 가장 소비전력 작은 경우를 dp[t][c]에 set
    for t in range(1, len(onboard)):
        for c in range(51):
            costs = [100 * 1000 + 1]
            
            # 승객이 타있는 동안 온도는 무조건 쾌적온도 내에 있어야 함
            if onboard[t] == 1 and not t1 <= c <= t2:
                continue
                
            # 온도가 쾌적온도에 있으면서, 에어컨 켜고 온도 유지
            if t1 <= c <= t2:
                costs.append(dp[t-1][c] + b)
            
            # 외부온도 == 실내온도, 온도 변화 X, 에어컨 끔
            if temperature == c:
                costs.append(dp[t-1][c])
            
            # 온도를 올리기 / 내리기 위해 에어컨을 켬
            if 0 <= c - move <= 50:
                costs.append(dp[t-1][c-move] + a)
            
            # 온도를 내리기 / 올리기 위해 에어컨을 끔
            if 0 <= c + move <= 50:
                costs.append(dp[t-1][c+move])
            
            dp[t][c] = min(costs)

    return min(dp[-1])