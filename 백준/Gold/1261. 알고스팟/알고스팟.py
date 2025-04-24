import sys
import heapq

input = lambda: sys.stdin.readline().rstrip()

M, N = map(int, input().split())
maze = [list(map(int, list(input()))) for _ in range(N)]

def valid(x, y):
    return 0 <= x < N and 0 <= y < M

def get_min_dist():
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    visited = [[False] * M for _ in range(N)]
    pq = []

    heapq.heappush(pq, (0, 0, 0)) # dist, x, y

    # for l in visited:
    #             print(l)

    while pq:
        dist, x, y = heapq.heappop(pq)
        
        if visited[x][y]:
            continue

        if x == N - 1 and y == M - 1:
            return dist
        
        visited[x][y] = True
        
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if valid(nx, ny) and not visited[nx][ny]:
                if maze[nx][ny] == 0:
                    heapq.heappush(pq, (dist, nx, ny))
                else:
                    heapq.heappush(pq, (dist + 1, nx, ny))
    
    return -1

print(get_min_dist())