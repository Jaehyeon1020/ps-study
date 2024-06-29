from collections import deque

def can_visit_all(rooms):
    q = deque()
    visited = [False for _ in range(len(rooms))]
    visited[0] = True

    for room in rooms[0]:
        if visited[room] is False:
            q.append(room)
            visited[room] = True

    while q:
        key = q.popleft()
        for room in rooms[key]:
            if visited[room] is False:
                q.append(room)
                visited[room] = True
    
    if False in visited:
        return False
    return True

rooms1 = [[1], [2], [3], []]
rooms2 = [[1,3], [3,0,1], [2], [0]]
rooms3 = [[0,1], [0,2], [], [0,1,2,3]]

print(can_visit_all(rooms3))

