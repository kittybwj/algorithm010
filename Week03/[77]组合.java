//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        recur(1, n, k, new Stack<>());
        return list;
    }

    private void recur(int index, int n, int k, Stack<Integer> stack) {
        if (stack.size() == k) {
            list.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i <= n - (k - stack.size()) + 1; i++) {
            stack.push(i);
            recur(i + 1, n, k, stack);
            stack.pop();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

