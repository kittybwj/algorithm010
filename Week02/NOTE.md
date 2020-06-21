## 第二周学习总结

### 一、第5课：哈希表、映射、集合

#### 1、哈希表
* 哈希表：又称散列表，是根据关键码值（key-value）而直接进行访问的数据结构。
* 映射函数叫做哈希函数，存放记录的数组叫做哈希表。
* 工程实践：根据关键信息存储数据如电话号码簿、用户信息表；缓存（LRU Cache）;键值对存储（Redis）
* 时间复杂度：在正常情况下，查询、添加、删除操作都是O(1)。
* 在Java中的实现：Map（key-value，key不重复，可以为null），Set(不重复元素集合)
* Java中的底层数据结构实现：数组+链表（1.8之后加入红黑树）

Java中HashMap源码分析

```
// put方法
public V put(K key, V value) {
		// 计算key的hash值，调用putVal方法
        return putVal(hash(key), key, value, false, true);
}

// putVal方法
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
           boolean evict) {
	Node<K,V>[] tab; 
	Node<K,V> p; 
	int n, i;
	// 如果数组为空，调用resize进行扩容操作
	if ((tab = table) == null || (n = tab.length) == 0)
	    n = (tab = resize()).length;
	// tab[i]为空，直接将元素存储在数组tab[i]位置上。p = tab[i]
	if ((p = tab[i = (n - 1) & hash]) == null)
	    tab[i] = newNode(hash, key, value, null);
	else {
	// 如果tab[i]不为空，则要存到链表或者红黑树节点上
	    Node<K,V> e; K k;
	    //如果首个元素与要插入元素相同，则覆盖
	    if (p.hash == hash &&
	        ((k = p.key) == key || (key != null && key.equals(k))))
	        e = p;
	    // 当前节点为TreeNode，在红黑树中进行插入
	    else if (p instanceof TreeNode)
	        e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
	    //
	    else {
	    //遍历 tab[i]，key已经存在，更新旧的value 值，否则进行插入操作，插入后链表长度大于8，将链表转换为红黑树
	        for (int binCount = 0; ; ++binCount) {
	            if ((e = p.next) == null) {
	            //插入链表的尾部
	                p.next = newNode(hash, key, value, null);
	                // TREEIFY_THRESHOLD为8，大于8转换为红黑树
	                if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
	                    treeifyBin(tab, hash);
	                break;
	            }
	            // key已经存在
	            if (e.hash == hash &&
	                ((k = e.key) == key || (key != null && key.equals(k))))
	                break;
	            p = e;
	        }
	    }
	    if (e != null) { // existing mapping for key
	        V oldValue = e.value;
	        if (!onlyIfAbsent || oldValue == null)
	            e.value = value;
	        afterNodeAccess(e);
	        return oldValue;
	    }
	}
	++modCount;
	//实际键值对数量超过threshold，进行扩容操作
	if (++size > threshold)
	    resize();
	afterNodeInsertion(evict);
	return null;
}

```

#### 2、 刷题方法

1）养成收藏精选代码的习惯

2）自己的方法：

* 建立五遍刷题法表格，记录每道题目的基本信息、刷题时间和思想  
* 将每道题的思考过程、优秀解法记录下来，方便回顾


### 二、第6课：树、二叉树、二叉搜索树
#### 1、树（Tree）

* 根节点、父节点、子节点、叶子节点
* 树和图的根本区别：有没有环
* Linked List是特殊化的Tree
* Tree是特殊化的Graph

#### 2、二叉树

1）二叉树实例代码（Java）

```
public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null; 
     }
 }
```
2）二叉树遍历

* 前序遍历（Pre-order）:根节点-左子树-右子树（记忆方法：根节点在前）
* 中序遍历（In-order）:左子树-根节点-右子树（记忆方法：根节点在中）
* 后序遍历（Post-order）:左子树-右子树-根节点（记忆方法：根节点在后）

#### 3、二叉搜索树（Binary Search Tree）

* 左子树上**所有节点**都小于它的根节点的值
* 右子树上**所有节点**都大于它的根节点的值
* 以此推断：左子树、右子树也是二叉搜索树**（重复性）**
* 中序遍历：升序遍历（因为中序遍历是左-根-右，对应小-中-大）
* 时间复杂度：查询、插入、删除都是 O(logN),类似二分查询
* 查询（O(logN)）：从根节点开始比较，小于根节点往左子树继续查询，大于根节点往右子树继续查询
* 插入（O(logN)）：创建新节点，比较插入
* 删除（O(logN)）：取要删除的节点的右子树最小的节点代替当前节点

### 三、第6课：堆和二叉堆、图
#### 1、堆（Heap）

* Heap：可以迅速找到一堆数中的最大值和最小值的数据结构
* 将根节点为最大值的堆称为大根堆、大顶堆，将根节点为最小值的堆称为小根堆、小顶堆。
* find-max：O(1)
* delete-max：O(logN)
* insert：O(logN) or O（1）
* 堆是一种抽象的数据结构，有很多种实现形式，二叉堆是一种相对常见且实现简单的形式，并不是最优的。

#### 2、二叉堆

1）概念

* 通过完全二叉树实现（根和每一级节点都是满的，最下面一层叶子节点可以不满）

2）性质

* 完全二叉树 
* 根节点大于（大顶）等于儿子节点

3）实现细节

* 二叉堆一般通过数组来实现
* 若根节点为a[0]，则索引为i的左孩子的索引为（2i+1），则索引为i的右孩子的索引为（2i+2），则索引为i的父节点的索引为floor（(i-1)/2）
* 插入操作 O(logN)：先插入到堆的尾部，依次向上调整（HeapifyUp）
* 删除操作 O(logN)：将堆尾元素替换到顶部，依次向下调整（HeapifyDown）

#### 3、图

1）概念

* Graph(V ,E)
* V(Vertex) ：点

    度-入度和出度；
    
    点与点之间-是否连通
* E(Edge) : 边

    有向和无向；
    
    权重

2）基于图的常见算法

* DFS（代码模板要熟练！）
* BFS（代码模板要熟练！）
* Dijkstra

### 四、学习方法的改进
* 每天都要抽时间刷题和看视频，如果集中在周末来不及
* 记录刷题次数、收集每道题的优秀解法
* 坚持！