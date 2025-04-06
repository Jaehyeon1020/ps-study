import sys
import heapq

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
nums = []
for _ in range(N):
    heapq.heappush(nums, int(input()))

def get_compares():
    if N == 1:
        return 0
    
    total = 0
    while len(nums) >= 2:
        new_cards = heapq.heappop(nums) + heapq.heappop(nums)
        heapq.heappush(nums, new_cards)
        total += new_cards
    
    return total

print(get_compares())