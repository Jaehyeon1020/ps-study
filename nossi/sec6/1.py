from collections import deque

def get_number_of_islands(grid):
    col = len(grid)
    row = len(grid[0])
    visited = [[False for _ in range(row)] for _ in range(col)]
    island = 0

    def bfs_check(x, y):
        q = deque()
        q.append((x,y))
        visited[cur_x][cur_y] = True
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]

        while q:
            cur_x, cur_y = q.popleft()

            for i in range(4):
                next_x = cur_x + dx[i]
                next_y = cur_y + dy[i]
                if (
                    next_x >= 0 and next_x < col and next_y >= 0 and next_y < row
                    and not visited[next_x][next_y] and grid[next_x][next_y] == '1'
                ):
                    q.append((next_x, next_y))
                    visited[next_x][next_y] = True

    for i in range(col):
        for j in range(row):
            if grid[i][j] == '1' and not visited[i][j]:
                bfs_check(i, j) 
                island += 1

    return island


grid = [
    ['1', '1', '0', '0', '0'],
    ['1', '1', '0', '0', '0'],
    ['0', '0', '1', '0', '0'],
    ['0', '0', '0', '1', '1']
]

print(get_number_of_islands(grid))