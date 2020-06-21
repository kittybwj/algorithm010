## HashMap总结

#### 哈希表
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
