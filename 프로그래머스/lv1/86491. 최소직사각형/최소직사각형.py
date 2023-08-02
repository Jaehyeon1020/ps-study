def solution(sizes):
    answer = 0
    
    horizon = []
    vertical = []
    
    for size in sizes:
        if size[0] >= size[1]:
            horizon.append(size[0])
            vertical.append(size[1])
        else:
            horizon.append(size[1])
            vertical.append(size[0])
    answer = max(horizon) * max(vertical)
    return answer