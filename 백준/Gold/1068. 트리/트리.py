import sys
from collections import deque

input = lambda: sys.stdin.readline().rstrip()

N = int(input())
tree = {}
for i in range(N):
    tree[i] = set()

parents = list(map(int, input().split()))
root = 0

for child, parent in enumerate(parents):
    if parent == -1:
        root = child
        continue
    
    tree[parent].add(child)

deleted_child = int(input())

if deleted_child == root:
    print(0)
    exit()

for parent in tree:
    if deleted_child in tree[parent]:
        tree[parent].remove(deleted_child)

leaf = 0
visited = [False] * N
q = deque()

q.append(root)
visited[root] = True

while q:
    cur = q.popleft()

    if len(tree[cur]) == 0:
        leaf += 1
    
    for next_node in tree[cur]:
        if not visited[next_node]:
            q.append(next_node)
            visited[next_node] = True

print(leaf)