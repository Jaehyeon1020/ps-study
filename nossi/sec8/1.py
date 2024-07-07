import heapq

def get_network_delay_time(times, n, k):
    graph = {}
    for i in range(1, n + 1):
        graph[i] = []
    for u, v, w in times:
        graph[u].append((w, v))

    costs = {}
    priority_q = []
    
    heapq.heappush(priority_q, (0, k))

    while priority_q:
        cur_cost, cur_node = heapq.heappop(priority_q)
        if cur_node not in costs:
            costs[cur_node] = cur_cost
            for cost, node in graph[cur_node]:
                heapq.heappush(priority_q, (cost + cur_cost, node))
    
    if len(costs) != n:
        return -1
    return max(costs.values())


times1, n1, k1 = [[2, 1, 2], [2, 3, 5], [2, 4, 1], [4, 3, 3]], 4, 2
times2, n2, k2 = [[2, 1, 2], [2, 3, 5], [2, 4, 1], [4, 3, 3]], 4, 3

print(get_network_delay_time(times1, n1, k1))
print(get_network_delay_time(times2, n2, k2))