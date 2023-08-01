def solution(X, Y):
    answer = ''
    
    x_list = [0 for x in range(10)]
    y_list = [0 for x in range(10)]
    xy_list = [0 for x in range(10)]
    
    for x in X:
        x_list[int(x)] += 1
    for y in Y:
        y_list[int(y)] += 1
    
    for i in range(10):
        smaller = min(x_list[i], y_list[i])
        xy_list[i] = smaller
    
    for i in range(9, -1, -1):
        repeat = xy_list[i]
        answer += str(i) * repeat
    
    if not answer:
        return "-1"
    elif answer[0] == "0":
        return "0"
    else:
        return answer