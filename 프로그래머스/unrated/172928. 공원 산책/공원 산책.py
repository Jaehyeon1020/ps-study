def solution(park, routes):
    x = 0
    y = 0
    horizon = len(park[0])
    vertical = len(park)
    flag = False
    
    for x1 in range(vertical):
        if flag is True:
            break
        for y1 in range(horizon):
            if park[x1][y1] == "S":
                x = x1
                y = y1
                flag = True
                break
    
    for r in routes:
        direction, blank = r.split()
        blank = int(blank)
        fail = False
        
        if direction == "E":
            if y + blank >= horizon:
                continue
            for i in range(1, blank + 1):
                if park[x][y + i] == "X":
                    fail = True
            if not fail:
                y += blank
        elif direction == "S":
            if x + blank >= vertical:
                continue
            for i in range(1, blank + 1):
                if park[x + i][y] == "X":
                    fail = True
            if not fail:
                x += blank
        elif direction == "W":
            if y - blank < 0:
                continue
            for i in range(1, blank + 1):
                if park[x][y - i] == "X":
                    fail = True
            if not fail:
                y -= blank
        elif direction == "N":
            if x - blank < 0:
                continue
            for i in range(1, blank + 1):
                if park[x - i][y] == "X":
                    fail = True
            if not fail:
                x -= blank
            
    return [x, y]
            
        
    
                