def solution(new_id):
    # 정규식 이용하지 않는 방법
    
    #1
    new_id = new_id.lower()
    
    #2
    temp = ''
    for c in new_id:
        if c in ['-', '_', '.'] or c.isalpha() or c.isdigit():
            temp += c
    new_id = temp

    #3
    while('..' in new_id):
        new_id = new_id.replace('..', '.')
    
    #4
    if len(new_id) != 0 and new_id[0] == '.':
        new_id = new_id[1:]
    if len(new_id) != 0 and new_id[-1] == '.':
        new_id = new_id[:-1]
    
    #5
    if len(new_id) == 0:
        new_id = 'a'
        
    #6
    if len(new_id) >= 16:
        new_id = new_id[:15]
        if new_id[-1] == '.':
            new_id = new_id[:-1]
    
    #7
    if len(new_id) <= 2:
        while(len(new_id) < 3):
            new_id = new_id + new_id[-1]
    
    return new_id