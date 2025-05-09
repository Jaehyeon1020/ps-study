import sys
import heapq

input = lambda: sys.stdin.readline().rstrip()

N, M = map(int, input().split())
graph = {}
for i in range(1, N + 1):
    graph[i] = []

for _ in range(M):
    n1, n2, cost = map(int, input().split())
    graph[n1].append((n2, cost))
    graph[n2].append((n1, cost))

def dijkstra():
    dist = [float('inf')] * (N + 1)

    visited = set()
    pq = []

    heapq.heappush(pq, (0, 1)) # cost, node
    
    while pq:
        cost_acc, cur_node = heapq.heappop(pq)

        if cur_node in visited:
            continue

        dist[cur_node] = cost_acc
        visited.add(cur_node)

        for next_node, cost in graph[cur_node]:
            if next_node not in visited:
                heapq.heappush(pq, (cost + cost_acc, next_node))
    
    return dist[N]

print(dijkstra())