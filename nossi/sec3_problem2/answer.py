def getDays(temperatures):
    stack = []
    answer = [0] * len(temperatures)

    for cur_day, cur_temp in enumerate(temperatures):
        if not stack:
            stack.append((cur_day, cur_temp))
        else:
            while stack and cur_temp > stack[-1][1]:
                prev_day, _ = stack.pop()
                answer[prev_day] = cur_day - prev_day
            stack.append((cur_day, cur_temp))
    return answer

print(getDays([73,74,75,71,69,72,76,73]))
print(getDays([30,40,50,60]))