## 第七周学习总结
### 第十三课：字典树和并查集

#### 一、字典树（Trie）

1、字典树的数据结构

* 字典树，即Trie树，又称单词查找树或键树，是一种树形数据结构。
* 典型应用是用于统计和排序大量的字符串，常被搜索引擎用于文本词频统计。
* 优点：最大限度地减少无谓的字符串比较，查询效率比哈希表高。
* 不是一棵二叉树，是多叉树。

2、字典树的核心思想

* 空间换时间
* 利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的

3、字典树的基本性质

* 结点本身不存完整单词
* 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串。
* 每个结点的所有子节点路径代表的字符都不相同。

4、Tire 树代码模板

```
class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
}

```

#### 二、并查集

1、使用场景

* 组团、配对问题
* 是否在一个集合当中

2、基本操作

* makeSet(s)：新建一个并查集，其中包括s个单元素集合
* unionSet(x, y)：把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并
* find(x)：找到x所在的集合的代表，可用于判断两个元素是否在一个集合当中。

3、并查集代码模板

```
class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
		count = n; 
		parent = new int[n]; 
		for (int i = 0; i < n; i++) { 
			parent[i] = i;
		}
	} 
	public int find(int p) { 
		while (p != parent[p]) { 
			parent[p] = parent[parent[p]]; 
			p = parent[p]; 
		}
		return p; 
	}
	public void union(int p, int q) { 
		int rootP = find(p); 
		int rootQ = find(q); 
		if (rootP == rootQ) return; 
		parent[rootP] = rootQ; 
		count--;
	}
}

```

### 第十四课：高级搜索

1、朴素搜索

2、优化方向：不重复、剪枝

3、搜索方向：

* BFS
* DFS
* 双向搜索
* 启发式搜索（估价函数）


### 第十五课：红黑树和AVL树

#### 一、AVL树

1、平衡因子

* Balance Factor（平衡因子）：他的左子树高度减去右子树的高度。
* Balance Factor = {-1,0,1}

2、通过旋转操作来进行平衡（四种）

* 左旋
* 右旋
* 左右旋
* 右左旋

3、子树形态

* 右右子树：左旋
* 左左子树：右旋
* 左右子树：左右旋
* 右左子树：右左旋
* 注意：带有子树的旋转

4、不足：节点需要存储额外信息，且调整次数频繁

#### 二、红黑树

1、红黑树是一种**近似平衡**的二叉搜索树，它能够确保任何一个节点的左右子树的**高度差小于两倍**。

2、红黑树是满足以下条件的二叉搜索树：

* 每个结点要么是红色，要么是黑色
* 根节点是黑色
* 每个叶子节点（NIL节点，空节点）是黑色
* 不能有相邻的两个红色节点
* 从任意节点到其每个叶子节点的所有路径都包含相同数目的黑色节点


