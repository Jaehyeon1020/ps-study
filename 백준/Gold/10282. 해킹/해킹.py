import sys
import heapq

input = lambda: sys.stdin.readline().rstrip()
print = sys.stdout.write

tc = int(input())

def dijkstra(start, graph, dist, n):
    visited = [False] * (n + 1)

    pq = []
    heapq.heappush(pq, (0, start))

    while pq:
        cur_dist, computer = heapq.heappop(pq)
        
        if visited[computer]: continue
        else: visited[computer] = True

        dist[computer] = min(cur_dist, dist[computer]) if dist[computer] != -1 else cur_dist

        if computer not in graph:
            continue

        for next_computer, time in graph[computer]:
            if visited[next_computer]:
                continue
            heapq.heappush(pq, (cur_dist + time, next_computer))


for _ in range(tc):
    n, d, c = map(int, input().split())
    graph = {}

    for __ in range(d):
        dest, src, time = map(int, input().split())
        
        if src not in graph:
            graph[src] = []
        graph[src].append((dest, time))
    
    dist = [-1] * (n + 1)
    dijkstra(c, graph, dist, n)

    count = 0
    for d in dist[1:]:
        if d != -1:
            count += 1

    max_time = max(dist)
    print(str(count) + " " + str(max_time) + "\n")