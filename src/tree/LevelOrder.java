package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

/**二叉树的层次遍历
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * Created by lll on 19/7/21.
 */
public class LevelOrder {

    /*
    方法 1：递归
    算法
    最简单的解法就是递归，首先确认树非空，然后调用递归函数 helper(node, level)，参数是当前节点和节点的层次。程序过程如下：

    输出列表称为 levels，当前最高层数就是列表的长度 len(levels)。
    比较访问节点所在的层次 level 和当前最高层次 len(levels) 的大小，如果前者更大就向 levels 添加一个空列表。
    将当前节点插入到对应层的列表 levels[level] 中。
    递归非空的孩子节点：helper(node.left / node.right, level + 1)。

     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List levels = new ArrayList();
        if(root == null){
            return levels;
        } else {
            helper(root, 0, levels);
        }
        return levels;
    }

    private void helper(TreeNode node, int lever, List<List<Integer>> levels){
        if (lever == levels.size()){
            levels.add(new ArrayList<Integer>());
        }
        levels.get(lever).add(node.val);
        if (node.left != null){
            helper(node.left, lever+1, levels);
        }
        if (node.right != null){
            helper(node.right, lever+1, levels);
        }

    }

    /*
    方法 1.1：递归
    把List<List<Integer>>放到类中,直接共享数据,就不需要在方法中传递了
     */
    private List<List<Integer>> levels = new ArrayList();
    public List<List<Integer>> levelOrder1(TreeNode root) {
        if(root != null){
            helper1(root, 0);
        }
        return levels;
    }

    private void helper1(TreeNode node, int lever){
        if (lever == levels.size()){
            levels.add(new ArrayList<Integer>());
        }

        levels.get(lever).add(node.val);

        if (node.left != null){
            helper1(node.left, lever+1);
        }
        if (node.right != null){
            helper1(node.right, lever+1);
        }

    }

    /*
    方法 2:迭代 queue
    我们将树上顶点按照层次依次放入队列结构中，队列中元素满足 FIFO（先进先出）的原则。
    第 0 层只包含根节点 root ，算法实现如下：
    初始化队列只包含一个节点 root 和层次编号 0 ： level = 0。
    当队列非空的时候：
    在输出结果 levels 中插入一个空列表，开始当前层的算法。
    计算当前层有多少个元素：等于队列的长度。
    将这些元素从队列中弹出，并加入 levels 当前层的空列表中。
    将他们的孩子节点作为下一层压入队列中。
    进入下一层 level++。
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while ( !queue.isEmpty() ) {
            // start the current level
            levels.add(new ArrayList<Integer>());

            // number of elements in the current level
            int level_length = queue.size();
            for(int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(level).add(node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }



}
