//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
// 
//
// 示例: 
//
// 输入: 
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"] 
//
// 说明: 
//你可以假设所有输入都由小写字母 a-z 组成。 
//
// 提示: 
//
// 
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？ 
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。 
// 
// Related Topics 字典树 回溯算法

// 思路：trie
// 1、all words -->Trie 构建起prefix
// 2、board DFS
//leetcode submit region begin(Prohibit modification and deletion)

class TrieNode{
    public String val;
    public TrieNode[] child = new TrieNode[26];
    public boolean isLeaf = false;
    TrieNode(){ }
}

class WordTrie{
    public TrieNode root = new TrieNode();
    public void insert(String s) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            if (cur.child[c - 'a'] == null) {
                cur.child[c - 'a'] = new TrieNode();
                cur = cur.child[c - 'a'];
            } else {
                cur = cur.child[c - 'a'];
            }
        }
        cur.isLeaf = true;
        cur.val = s;
    }
}

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        // 构建字典树
        WordTrie myTrie = new WordTrie();
        TrieNode root = myTrie.root;
        for(String s : words) {
            myTrie.insert(s);
        }

        Set<String> result =new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        boolean [][]visited=new boolean[m][n];
        //遍历整个二维数组
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                find(board, visited, i, j, m, n, result, root);
            }
        }
        return new LinkedList<String>(result);
    }

    private void find(char[][] board, boolean[][]visited, int i, int j, int m, int n, Set<String> result, TrieNode cur) {
        if(i < 0 || i >= m || j < 0|| j >=n || visited[i][j])
            return;
        cur = cur.child[board[i][j] - 'a'];
        visited[i][j] = true;
        if (cur == null) {
            visited[i][j] = false;
            return;
        }
        if(cur.isLeaf) {
            result.add(cur.val);
        }
        find(board, visited,i+1, j, m, n, result, cur);
        find(board, visited, i,j+1, m, n, result, cur);
        find(board, visited, i,j-1, m, n, result, cur);
        find(board, visited,i-1, j, m, n, result, cur);
        //最后要回退，因为下一个起点可能会用到上一个起点的字符
        visited[i][j] = false;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

