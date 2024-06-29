from collections import deque

def get_shortest_path(grid):
    n = len(grid)
    start = grid[0][0]
    end = grid[n - 1][n - 1]
    path = -1

    if start == 1 or end == 1:
        return -1
    
    q = deque()
    q.append((0, 0, 1))
    visited = [[False for _ in range(n)] for _ in range(n)]
    move = [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, -1), (-1, 1), (1, -1), (1, 1)]

    while q:
        cur_x, cur_y, d = q.popleft()

        if cur_x == n - 1 and cur_y == n - 1: # end
            path = d
            break

        for dx, dy in move:
            next_x = cur_x + dx
            next_y = cur_y + dy
            if (
                0 <= next_x and next_x < n and 0 <= next_y and next_y < n and
                visited[next_x][next_y] == False and grid[next_x][next_y] == 0
            ):
                q.append((next_x, next_y, d + 1))
                visited[next_x][next_y] = True
        
        
    return path

grid1 = [
    [0, 0, 0],
    [1, 1, 0],
    [1, 1, 0]
]

grid2 = [
    [1, 0, 0],
    [1, 1, 0],
    [1, 1, 0]
]

print(get_shortest_path(grid2))