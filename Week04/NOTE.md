## 第四周学习总结
### 第九课：深度优先搜索和广度优先搜索

#### 1、遍历搜索（在树、图中寻找特定结点）

- 每个节点都要访问一次
- 每个节点仅仅要访问一次
- 对于节点的访问顺序不限

深度优先：depth frist search

广度优先：breadth frist search

#### 2、深度优先算法

**DFS 代码模板**

递归写法

```java
visited = set() 
def dfs(node, visited):
    if node in visited: # terminator
    	# already visited 
    	return 
	visited.add(node) 
	# process current node here. 
	...
	for next_node in node.children(): 
		if next_node not in visited: 
			dfs(next_node, visited)
```

非递归写法

```java
def DFS(self, tree): 
	if tree.root is None: 
		return [] 
	visited, stack = [], [tree.root]
	while stack: 
		node = stack.pop() 
		visited.add(node)
		process (node) 
		nodes = generate_related_nodes(node) 
		stack.push(nodes) 
	# other processing work 
	...
```

#### 3、广度优先算法

队列实现

```java
# Python
def BFS(graph, start, end):
    visited = set()
	queue = [] 
	queue.append([start]) 
	while queue: 
		node = queue.pop() 
		visited.add(node)
		process(node) 
		nodes = generate_related_nodes(node) 
		queue.push(nodes)
	# other processing work 
	...
```



```java
public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            list.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        res.add(list);
        return res;
    }
```

### 第十课：贪心算法Greedy

贪心算法是一种在每一步选择中都采取在当前状态下最好或最优的选择，从而希望导致结果是全局最好或最优的算法。

* 贪心算法：当前做局部最优判断，不能回退

* 回溯：能够回退

* 动态规划：最有判断+回退

贪心法能解决一些最优化问题，如：求图中的最小生成树、求哈弗曼编码等。

适用贪心算法的场景：问题能分解成子问题来解决，子问题的最优解能递推到最终问题的最优解。这种子问题最优解称为最优子结构。

贪心算法和动态递归的不同在于他对每个子问题的的解决方案都做出选择，不能回退。动态规划会保存之前的运算结果，并根据以前的结果对当前进行选择，有回退功能

### 第十一课：二分查找

二分查找的前提

1、目标函数单调性（单调递增或递减）

2、存在上下界

3、能够通过索引访问

代码模板

```java
int left = 0, right = array.length - 1;
while(left <= right) {
    int mid = (left + right) / 2;
    if (array[mid] == target) {
        // find the target
    } else if (array[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
```