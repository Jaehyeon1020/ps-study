def twoSum(nums, target):
    memo = {}
    for n in nums:
        memo[n] = None
    for n in nums:
        need = target - n
        if need in memo:
            return True
    return False

nums = [4,1,9,7,5,3,14]
target = 15

print(twoSum(nums,target))