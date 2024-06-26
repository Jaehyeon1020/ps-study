def getLongest(nums):
    num_set = set(nums)

    count = 0
    longest = 0
    for n in num_set:
        if n - 1 not in num_set:
            target = n + 1
            count = 1
            while target in num_set:
                target += 1
                count += 1
            longest = max(count, longest)
    return longest

print(getLongest([100,4,200,1,3,2]))
print(getLongest([0,3,7,2,5,8,4,6,0,1]))