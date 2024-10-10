import heapq

def solution(n, s, a, b, fares):
    costs = {} # costs[출발]: (비용, 도착지점)[] => 그래프 표현을 위함
    memo = [[-1] * (n + 1) for _ in range(n + 1)] # memo[출발][도착]: 출발 => 도착 최소 비용
    
    for start, end, cost in fares:
        if not start in costs:
            costs[start] = []
        if not end in costs:
            costs[end] = []
        
        costs[start].append((cost, end))
        costs[end].append((cost, start))
    
    def dstra(start, end):
        hq = []
        visited = {} # 지점번호 : 최소비용 쌍
        heapq.heappush(hq, (0, start))
        
        while hq:
            cost, node = heapq.heappop(hq)
            if node not in visited:
                visited[node] = cost
                if memo[start][node] == -1:
                    memo[start][node] = cost
                    memo[node][start] = cost
                for next_cost, next_node in costs[node]:
                    if next_node not in visited:
                        heapq.heappush(hq, (next_cost + cost, next_node))
        
        # start-end 이어져 있으면 비용, 안 이어져 있으면 -1 반환
        return visited[end] if end in visited else -1
    
    def get_min_cost(start, end):
        if memo[start][end] != -1:
            return memo[start][end]
        
        min_cost = dstra(start, end)
        memo[start][end] = min_cost
        return min_cost
                
    # 1. 출발 -> A -> B == (출발 -> A) + (A -> B)
    # 2. 출발 -> B -> A == (출발 -> B) + (B -> A)
    # 3. (출발 -> A ~ B 사이 어딘가(K)) + (K -> A) + (K -> B)
    # 4. (출발 -> A) + (출발 -> B)
    # 넷 중 가장 비용이 작은 값 선택하면 됨
    
    # 1, 2, 4번 미리 삽입
    taxi = [
        get_min_cost(s, a) + get_min_cost(a, b),
        get_min_cost(s, b) + get_min_cost(b, a),
        get_min_cost(s, a) + get_min_cost(s, b)
    ]
    
    # 3번 계산
    for k in costs:
        if k == s or k == a or k == b:
            continue
        
        tmp = []
        
        # 공유하는 구간 (S -> K)
        tmp.append(get_min_cost(s, k))

        # K -> A
        tmp.append(get_min_cost(k, a))
        
        # K -> B
        tmp.append(get_min_cost(k, b))
        
        # 갈 수 없는 구간이 있다면 건너뛰기
        if -1 in tmp:
            continue
            
        taxi.append(sum(tmp))
    
    return sorted(taxi)[0]