def solution(keymap, targets):
    result = []
    fail = False
    
    for target in targets:
        count = 0
        
        for char in target:
            locationList = []
            
            for key in keymap:
                location = key.index(char) + 1 if char in key else None
                if location is not None:
                    locationList.append(location)
                    
            if len(locationList) == 0:
                fail = True
            else:
                count += min(locationList)
        
        if fail == True:
            result.append(-1)
            fail = False
        else:
            result.append(count)
            count = 0
    
    return result