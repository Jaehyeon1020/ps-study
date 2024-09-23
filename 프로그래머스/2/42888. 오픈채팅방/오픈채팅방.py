def solution(record):
    log = []
    id_usernames = {}
    
    for r in record:
        info = r.split()
        command, user_id = info[0:2]
        
        if command == 'Enter':
            username = info[2]
            id_usernames[user_id] = username
            log.append([command, user_id])
        elif command == 'Leave':
            log.append([command, user_id])
        else: # command: Change
            username = info[2]
            id_usernames[user_id] = username
    
    answer = []
    for l in log:
        command, user_id = l
        command_map = {'Enter': '들어왔습니다.', 'Leave': '나갔습니다.'}
        answer.append(f'{id_usernames[user_id]}님이 {command_map[command]}')
        
    return answer