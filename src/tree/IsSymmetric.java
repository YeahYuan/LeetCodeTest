package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 如果一个树的左子树与右子树镜像对称，那么这个树是对称的。
 * 两个树在什么情况下互为镜像？
 * 如果同时满足下面的条件，两个树互为镜像：
 * 它们的两个根结点具有相同的值。
 * 每个树的右子树都与另一个树的左子树镜像对称。
 * Created by lll on 19/7/28.
 */
public class IsSymmetric {

    /*
    递归
     */
    public boolean isSymmetric1(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }
    private boolean isMirror(TreeNode t1, TreeNode t2){
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    /*
    迭代
    队列先进先出
    队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像。
    最初，队列中包含的是 root 以及 root。
    该算法的工作原理类似于 BFS(左右上下)，但存在一些关键差异。
    每次提取两个结点并比较它们的值。
    然后，将两个结点的左右子结点按相反的顺序插入队列中。
    当队列为空时，或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null)
                continue;
            if (t1 == null || t2 == null)
                return false;
            if (t1.val != t2.val)
                return false;
            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }
        return  true;
    }





    List<Integer> list = new ArrayList<Integer>();
    /*
    中序遍历是对称的
    本来想用中序遍历把二叉树转为数组,再对比数组是否对称
    但是!!!数组根本不知道哪个是根节点,二叉树左右数量都不一定对但有可能数组对称
    所以放弃哈哈哈
     */
    public boolean isSymmetric9(TreeNode root) {
        stToArray(root);
        for(int start = 0, end = list.size()-1; start<end; start++,end--){
            if(list.get(start) != list.get(end)){
                return false;
            }
        }
        return true;
    }
    //中序遍历
    private void stToArray(TreeNode root){
        if(root == null){
            return;
        }else {
            if (root.left == null){
                list.add(null);
            } else {
                stToArray(root.left);
            }
            list.add(root.val);
            if (root.right == null){
                list.add(null);
            } else {
                stToArray(root.right);
            }
        }
    }
}
