def solution(name, yearning, photo):
    yearning_dict = {}
    
    idx = 0
    for n in name:
        yearning_dict[n] = yearning[idx]
        idx += 1
        
    result = []
    for p in photo:
        value = 0
        for name in p:
            if name in yearning_dict:
                value += yearning_dict[name]
        
        result.append(value)
    
    return result