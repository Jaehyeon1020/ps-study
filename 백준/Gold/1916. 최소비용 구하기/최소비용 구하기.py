import sys
import heapq

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
M = int(input())
bus = [list(map(int, input().split())) for _ in range(M)]
start_city, end_city = map(int, input().split())

graph = {}
for src, dest, cost in bus:
    if src not in graph:
        graph[src] = []
    
    graph[src].append((dest, cost))

def dijkstra():
    pq = []
    visited = set()
    costs = [float('inf')] * (N + 1)

    heapq.heappush(pq, (0, start_city)) # 비용, 도시

    while pq:
        cost, city = heapq.heappop(pq)

        if city in visited:
            continue
        
        visited.add(city)
        costs[city] = cost

        if city not in graph:
            continue
        for next_city, next_cost in graph[city]:
            if next_city not in visited:
                heapq.heappush(pq, (next_cost + cost, next_city))
    
    return costs[end_city]

print(dijkstra())