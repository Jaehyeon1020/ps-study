import sys

input = lambda: sys.stdin.readline().rstrip()

N, M = map(int, input().split())
nums = [int(input()) for _ in range(N)]
nums.sort()

minval = float('inf')

def diff(left, right):
    return nums[right] - nums[left]

def set_minval(left, right):
    global minval
    minval = min(nums[right] - nums[left], minval)

left = right = 0
while left != N - 1 or right != N - 1:
    # move pointer
    if left == right or diff(left, right) < M:
        if right == N - 1:
            break
        right += 1
    elif diff(left, right) >= M:
        left += 1
    
    # set minval
    if left != right and diff(left, right) >= M:
        set_minval(left, right)

print(minval)
