def solution(board, moves):
    answer = 0
    moves = list(map(lambda x: x - 1, moves))
    
    stack = []
    
    for m in moves:
        for i in range(len(board)):
            if board[i][m] != 0:
                stack.append(board[i][m])
                board[i][m] = 0
                break
        
        if len(stack) >= 2 and stack[-1] == stack[-2]:
            stack.pop()
            stack.pop()
            answer += 2
    
    return answer