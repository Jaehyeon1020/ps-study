def solution(info, edges):
    answer = []
        
    visited = [False for _ in range(len(info))]
    def dfs(sheep, wolf):
        if sheep > wolf:
            answer.append(sheep)
        else:
            return
        
        for _from, to in edges:
            if visited[_from] and not visited[to]:
                visited[to] = True
                # 양
                if info[to] == 0:
                    dfs(sheep + 1, wolf)
                else:
                    dfs(sheep, wolf + 1)
                visited[to] = False

    visited[0] = True
    dfs(1, 0)
    
    return max(answer)