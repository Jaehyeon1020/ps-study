def solution(board, h, w):
    answer = 0
    
    color = board[h][w]
    
    col_move = [1, -1, 0, 0]
    row_move = [0, 0, 1, -1]
    
    for i in range(4):
        # try:
        #     if board[h + col_move[i]][w + row_move[i]] == color:
        #         answer += 1
        # except:
        #     continue
        col = h + col_move[i]
        row = w + row_move[i]
        
        if col <= len(board) - 1 and col >= 0 and row <= len(board) - 1 and row >= 0:
            if board[col][row] == color:
                answer += 1
    
    return answer