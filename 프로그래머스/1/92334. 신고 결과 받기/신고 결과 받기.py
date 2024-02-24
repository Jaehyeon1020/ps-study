def solution(id_list, report, k):
    call_count = {} # 유저별 신고당한 횟수
    user_called = {} # 누가 누구를 신고했는지
    mail = {} # 유저별 받을 메일
    
    for user in id_list:
        call_count[user] = 0
        user_called[user] = []
        mail[user] = 0
        
    for r in report:
        caller, callee = r.split()
        if callee not in user_called[caller]:
            user_called[caller].append(callee)
            call_count[callee] += 1
    
    for called_user in id_list:
        if call_count[called_user] < k:
            continue
        for user in id_list:
            if called_user in user_called[user]:
                mail[user] += 1
        
    return [mail[user] for user in id_list]