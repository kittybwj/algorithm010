//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 校验
        if (preorder.length != inorder.length || preorder.length == 0) {
            return null;
        }

        for (int i = 0; i < inorder.length; i++) {
            // key 值 value 坐标
            map.put(inorder[i] , i);
        }
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int pStart, int pEnd,
                                     int[] inorder, int iStart, int iEnd) {
        if (pStart > pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int leftLength = map.get(preorder[pStart]) - iStart;
        root.left = buildTreeHelper(preorder, pStart + 1, pStart + leftLength, inorder, iStart, map.get(preorder[pStart]) - 1);
        root.right = buildTreeHelper(preorder, pStart + leftLength + 1, pEnd, inorder, map.get(preorder[pStart]) + 1, iEnd);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

