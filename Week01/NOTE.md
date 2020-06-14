## 第一周学习总结

### 一、高效学习方法

#### 1、三分看视频理解，七分练习
* 看视频：1.5-2倍速看，重点难点暂停理解，反复观看，一定要掌握消化视频内容
* 刷题：LeetCode中文站+国际站，五遍刷题法

#### 2、摒弃旧的刷题习惯，学习新方法
* 不要死磕，五分钟想不出来就去看题解和高票代码
* 五毒神掌，敢于死记硬背
* 不要AC了事，一定要多看高手的代码，不要懒

#### 3、切题四件套
* Clarification 明确问题
* Possible Solutions 想多种可能的解法，比较时间、空间复杂度
* Coding 多写
* Test cases 测试用例，注意特殊值、边界值

#### 4、五遍刷题法
* 第一遍（当天）：五分钟读题加思考，没有思路直接看解法，比较解法优劣。背诵、默写好的解法。
* 第二遍（当天）：马上自己默写，LeetCode提交。看中文站、国际站的多种解法，体会，优化。
* 第三遍（一天后）：第二天再重复做题，训练不同解法的熟练度。
* 第四遍（一周后）：过了一周之后，练习相同的题目。
* 第五遍（面试前）：面试前一周或两周，专项练习。

#### 5、自我学习目标
* 学习视频：消化每一个知识点，要100%掌握。
* 所有讲到的习题，用五遍刷题法练习。
* 所有的扩展知识点，都要去了解。
* 多归纳总结，多举一反三。

### 二、第三课：数组、链表、调表
#### 1、数组（ArrayList）

时间复杂度：(面试常问！)

* 查找:O(1)
* 插入：O(n)
* 删除：O(n)

#### 2、链表(LinkedList)

时间复杂度：(面试常问！)

* 查找: O(n)
* 插入：O(1)
* 删除：O(1)

#### 3、跳表(skip list)

**只能用于元素有序的情况**

时间复杂度：

* 查找:O(log n)
* 插入：O(log n)
* 删除：O(log n)

空间复杂度：O(n)

热门项目应用：Redis、LevelDB

核心思想：

* 升维思想
* 空间换时间

#### 4、习题中的解题思想及常用方法

* 升维，空间换时间
* 左右夹逼法
* 找最近重复子问题
* 无论是多么复杂的算法，最后都是用if-else、loop、递归完成的

### 三、第四课：栈、队列、优先队列、双端队列

#### 1、栈（Stack）
* 先入后出（FILO）
* 添加、删除为 O(1)，查询 O(n)因为是无序的

#### 2、队列（Queue）
* 先入先出（FIFO）
* 添加、删除为 O(1)，查询 O(n)因为是无序的

#### 3、双端队列（Deque：Double-End Queue）
* 栈和队列的结合，两端都可以进出的Queue
* 添加、删除为 O(1)，查询 O(n)因为是无序的。

#### 4、优先队列（Priority Queue）
* 插入操作：O(1)
* 取出操作：O(log n) - 按照元素的优先级取出
* 底层具体实现的数据结构较为多样和复杂：heap（堆）

#### 5、文档及源码

* 学会查Java中类和接口的文档
* 学会看Java中实现的源码，自己分析

```
Queue源码分析（主要分析add方法）

//Queue是一个接口，继承与Collection
public interface Queue<E> extends Collection<E> 

//包含的方法
1、插入元素
（插入失败抛exception）（Throws exception）
boolean add(E e);
LinkedList的实现：记录链表最后一个元素，将需要插入的元素的前一个节点设为last，后面的节点设为null，last节点设为新插入的元素。如果l为null，说明链表为空，则新加入的元素为头结点；如果不为null，则l的下一个节点设为新加入的元素。链表的size+1.
void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
}
（插入失败返回false，如果是容量受限的队列，推荐这个方法插入）（Returns special value）
boolean offer(E e);

2、移除元素
（Throws exception：NoSuchElementException if this queue is empty）
E remove();
（Returns special value：{@code null} if this queue is empty）
E poll();

3、查找元素
（Throws exception：NoSuchElementException if this queue is empty）
E element();

（Returns special value：{@code null} if this queue is empty）
E peek();

```

```
Priority Queue源码分析

// PriorityQueue类继承于AbstractQueue
public class PriorityQueue<E> extends AbstractQueue<E>

// Comparator接口实现，判断优先队列优先级的方法
private final Comparator<? super E> comparator;

// 元素插入方法：add，调用offer方法
public boolean add(E e) {
        return offer(e);
}
// 如果元素为null，空指针异常；如果队列长度不够，调用grow方法扩容；如果i为0，说明原队列为空，直接把元素放在第一个，如果不为0，调用shifUp插入，位置为i。
public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++;
        int i = size;
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            siftUp(i, e);
        return true;
    }
// k:插入位置，x：插入元素。依次比较，放到相应位置。
private void siftUp(int k, E x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }

```

#### 6、习题中的解题思想及常用方法
* 洋葱型：栈
* 先来后到，先进先出：队列
* 用栈来实现队列、用队列来实现栈：2个栈或2个队列