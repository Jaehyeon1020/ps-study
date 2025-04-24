import sys

input = lambda: sys.stdin.readline().rstrip()

N, M, L = map(int, input().split())
stops = list(map(int, input().split()))
stops.sort()

diffs = []
max_diff = 0

if N > 0:
    diffs.append(stops[0])
    max_diff = max(max_diff, stops[0])

    for i in range(len(stops) - 1):
        diff = stops[i + 1] - stops[i]
        max_diff = max(max_diff, diff)
        diffs.append(diff)

    diffs.append(L - stops[-1])
    max_diff = max(max_diff, L + 1 - stops[-1])
else:
    diffs.append(L)
    max_diff = L

# 모든 휴게소 간의 거리를 distance 이하로 만들 수 있는가?
def can_make(distance):
    # print(diffs)
    left_stops = M

    for diff in diffs:
        if diff <= distance:
            continue

        used_stops = 0
        while diff / (used_stops + 1) > distance:
            used_stops += 1
        
        if used_stops > diff - 1:
            return False
        
        left_stops -= used_stops

    if left_stops < 0:
        # print(left_stops)
        return False
    return True

left = 1
right = max_diff
while left < right:
    mid = (left + right) // 2
    # print(mid)

    if can_make(mid):
        right = mid
    else:
        left = mid + 1

print(right)