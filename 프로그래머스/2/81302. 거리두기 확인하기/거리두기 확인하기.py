from collections import deque

def solution(places):
    answer = []
    
    def bfs(x, y):
        q = deque()
        q.append([x, y, '', 0])
        dx = [-1, 1, 0, 0]
        dy = [0, 0, -1, 1]
        
        visited = [[False] * 5 for _ in range(5)]

        while q:
            cur_x, cur_y, prev_status, distance = q.popleft()
            status = place[cur_x][cur_y]
                        
            # 거리두기 위반
            if (prev_status == 'O' or prev_status == 'P') and status == 'P' and not visited[cur_x][cur_y]:
                return False
            
            visited[cur_x][cur_y] = True
            
            for i in range(len(dx)):
                target_x, target_y = cur_x + dx[i], cur_y + dy[i]
                if 0 <= target_x and target_x <= 4 and 0 <= target_y and target_y <= 4 and distance < 2:
                    q.append([target_x, target_y, status, distance + 1])

        return True
            
    for place in places:
        result = 1
        for x in range(5):
            for y in range(5):
                if place[x][y] == 'P':
                    if not bfs(x, y):
                        result = 0
        answer.append(result)
                    
    return answer
            
                    