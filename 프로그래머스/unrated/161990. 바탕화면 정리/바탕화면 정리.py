def solution(wallpaper):
    # 모든 파일 중 가장 위 좌표 + 가장 왼쪽 좌표 = 시작
    # 가장 아래좌표 + 가장 오른쪽 좌표 ++1 = 끝
    lux = 0
    luy = 0
    rdx = 0
    rdy = 0
    
    vertical = len(wallpaper)
    horizon = len(wallpaper[0])
    
    mostLeft = horizon + 1
    mostUp = vertical + 1
    mostRight = -1
    mostDown = -1
    
    for x in range(vertical):
        for y in range(horizon):
            if wallpaper[x][y] == "#":
                if y <= mostLeft:
                    mostLeft = y
                if x >= mostDown:
                    mostDown = x
                if x <= mostUp:
                    mostUp = x
                if y >= mostRight:
                    mostRight = y
    
    mostRight += 1
    mostDown += 1
    
    return [mostUp, mostLeft, mostDown, mostRight]