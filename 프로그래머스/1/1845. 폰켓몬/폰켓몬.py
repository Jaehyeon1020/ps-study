def solution(nums):
    pokemons = {}
    can_get = len(nums) / 2
    
    for po in nums:
        if po not in pokemons:
            pokemons[po] = 1
            continue
        pokemons[po] += 1
    
    if len(pokemons.keys()) >= can_get:
        return can_get
    else:
        return len(pokemons.keys())
        