from collections import deque

def move(cur_pos, target_pos):
    cur_x, cur_y = cur_pos
    target_x, target_y = target_pos
    
    if cur_x != target_x:
        if cur_x > target_x:
            return [cur_x - 1, cur_y]
        return [cur_x + 1, cur_y]
    if cur_y != target_y:
        if cur_y > target_y:
            return [cur_x, cur_y - 1]
        return [cur_x, cur_y + 1]

def solution(points, routes):
    answer = 0
    log = {}
    
    # 로봇 하나가 움직이는 경로 순차적으로 기록
    for route in routes:
        time = 0
        route = deque(route)
        target = route.popleft()
        cur_pos = points[target - 1] # 현재 위치
        target_pos = points[target - 1] # 타겟 위치
        while True:                
            if time in log:
                log[time].append(cur_pos)
            else:
                 log[time] = [cur_pos]
            
            if cur_pos == target_pos:
                if route:
                    target_pos = points[route.popleft() - 1]
                else:
                    break
            
            cur_pos = move(cur_pos, target_pos)
            time += 1
    
    # 로그 분석
    for time in log:
        danger = []
        for pos in log[time]:
            if log[time].count(pos) >= 2 and pos not in danger:
                danger.append(pos)
                
        answer += len(danger)

    return answer