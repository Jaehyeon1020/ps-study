from collections import deque

def solution(bridge_length, weight, truck_weights):
    truck_weights = deque(truck_weights)
    on_bridge = deque([])
    
    cur_weight = 0
    time = 0
    
    while truck_weights or on_bridge:
        time += 1
        
        # 트럭 이동
        for truck in on_bridge:
            truck['move'] += 1
        
        # 나갈 수 있는 트럭 확인
        if on_bridge and on_bridge[0]['move'] > bridge_length:
            leave_truck = on_bridge.popleft()
            cur_weight -= leave_truck['weight']
        
        # 새 트럭 올리기
        if len(on_bridge) < bridge_length and truck_weights and truck_weights[0] + cur_weight <= weight:
            new_truck_weight = truck_weights.popleft()
            cur_weight += new_truck_weight
            on_bridge.append({
                "weight": new_truck_weight,
                "move": 1
            })
    
    return time