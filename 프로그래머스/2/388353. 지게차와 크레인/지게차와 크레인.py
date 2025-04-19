from collections import deque

boxes = []
n = 0
m = 0

def remove_all(alphabet):
    for i in range(n):
        for j in range(m):
            if boxes[i][j] == alphabet:
                boxes[i][j] = 'e'

def valid(x, y):
    return 0 <= x < n and 0 <= y < m
                
def is_edge(x, y):
    dx = [-1, 1, 0, 0]
    dy = [0, 0, 1, -1]
    
    visited = [[False] * m for _ in range(n)]
    q = deque()
    
    q.append((x, y))
    visited[x][y] = True
    
    while q:
        cx, cy = q.popleft()
        
        if cx == 0 or cx == n - 1 or cy == 0 or cy == m - 1:
            return True
        
        for i in range(4):
            nx, ny = cx + dx[i], cy + dy[i]
            if valid(nx, ny) and boxes[nx][ny] == 'e' and not visited[nx][ny]:
                q.append((nx, ny))
                visited[nx][ny] = True
    
    return False
                
def remove_edge(alphabet):
    edge_pos = []
    for i in range(n):
        for j in range(m):
            if boxes[i][j] == alphabet and is_edge(i, j):
                edge_pos.append((i, j))
    
    for x, y in edge_pos:
        boxes[x][y] = 'e'

def solution(storage, requests):
    global n, m
    
    n = len(storage)
    m = len(storage[0])
    
    for line in storage:
        new_line = []
        for box in line:
            new_line.append(box)
        boxes.append(new_line)
    
    for req in requests:
        if len(req) == 2:
            remove_all(req[0])
        else:
            remove_edge(req)
    
    count = 0
    for line in boxes:
        for box in line:
            if box != 'e':
                count += 1
    
    return count