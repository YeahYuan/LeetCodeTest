package tree;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * Created by lll on 19/6/23.
 */
public class MaxDepth {

    /*
    递归
     */
    //代码有冗余,有判断root==null再对左右判断无意义,修改见下
    public int maxDepth2(TreeNode root) {
        if (root == null){
            return 0;
        } else if (root.left == null && root.right == null){
            return 1;
        } else if (root.left == null){
            return maxDepth2(root.right) + 1;
        } else if (root.right == null){
            return maxDepth2(root.left) + 1;
        } else {
            return Math.max(maxDepth2(root.right), maxDepth2(root.left))+1;
//            会超时
//            return maxDepth(root.right) > maxDepth(root.left) ? maxDepth(root.right) + 1 : maxDepth(root.left) + 1;
        }
    }
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(maxDepth(root.right), maxDepth(root.left)) + 1;
        }
        //极端简洁
//        return root == null ? 0: Math.max(maxDepth(root.right), maxDepth(root.left)) + 1;
    }


    /*
    迭代(做不出来)
    todo
    我们还可以在栈的帮助下将上面的递归转换为迭代。
    我们的想法是使用 DFS 策略访问每个结点，同时在每次访问时更新最大深度。
    所以我们从包含根结点且相应深度为 1 的栈开始。然后我们继续迭代：
    将当前结点弹出栈并推入子结点。每一步都会更新深度。
     */
    public int maxDepth1(TreeNode root) {

        Queue<Pair<TreeNode, Integer>> stack = new LinkedList();
        if (root != null) {
            stack.add(new Pair(root, 1));
        }

        int depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            int current_depth = current.getValue();
            if (root != null) {
                depth = Math.max(depth, current_depth);
                stack.add(new Pair(root.left, current_depth + 1));
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return depth;
    }
}
