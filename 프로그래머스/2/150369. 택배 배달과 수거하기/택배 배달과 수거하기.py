# 10:10 - 

distance = 0
cur_pos = -1

def move(dest):
    global distance, cur_pos
    moved = dest - cur_pos if dest > cur_pos else cur_pos - dest
    distance += moved
    cur_pos = dest
    
def solution(cap, n, deliveries, pickups):
    global distance, cur_pos
    
    deliver_left = []
    pickup_left = []
    
    for i in range(n):
        if deliveries[i] != 0:
            deliver_left.append([i, deliveries[i]])
        if pickups[i] != 0:
            pickup_left.append([i, pickups[i]])
    
    while deliver_left or pickup_left:
        truck = 0
        
        # 가장 먼 집까지 배달
        if deliver_left:
            deliver_plans = []
            while truck != cap:
                if not deliver_left:
                    break

                target, boxes = deliver_left[-1]
                if truck + boxes <= cap:
                    truck += boxes
                    deliver_plans.append([target, boxes])
                    deliver_left.pop()
                else:
                    tmp = cap - truck # 배달 개수
                    truck += tmp
                    deliver_plans.append([target, tmp])
                    deliver_left[-1] = [target, boxes - tmp]
            while deliver_plans:
                target, boxes = deliver_plans.pop()
                move(target)
                truck -= boxes
                    
        # 가장 먼 집부터 수거
        if pickup_left:
            pickup_plans = []
            while truck != cap:
                if not pickup_left:
                    break
                    
                target, boxes = pickup_left[-1]
                if truck + boxes <= cap:
                    truck += boxes
                    pickup_plans.append([target, boxes])
                    pickup_left.pop()
                else:
                    tmp = cap - truck
                    truck += tmp
                    pickup_left[-1] = [target, boxes - tmp]
                    pickup_plans.append([target, tmp])
            for target, boxes in pickup_plans:
                move(target)
        move(-1) # 출발지로 다시 이동
        
    return distance
        