import sys
import heapq

input = lambda: sys.stdin.readline().rstrip()

pq = []

def pop():
    if not pq:
        return 0
    
    abs_val, real_val = heapq.heappop(pq)
    same_values = []

    while pq and pq[0][0] == abs_val:
        same_values.append(heapq.heappop(pq))
    
    if not same_values:
        return real_val
    
    same_values.append((abs_val, real_val))
    same_values.sort(key = lambda v: v[1])

    for i in range(1, len(same_values)):
        heapq.heappush(pq, same_values[i])
    
    return same_values[0][1]
        

def push(val):
    heapq.heappush(pq, (abs(val), val))


N = int(input())
for _ in range(N):
    val = int(input())

    if val == 0:
        print(pop())
    else:
        push(val)