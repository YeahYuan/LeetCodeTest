package tree;

/**验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。!!!只包含!!!
 * 节点的右子树只包含大于当前节点的数。!!!只包含!!!
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * Created by lll on 19/6/23.
 */
public class IsValidBST {

    /*递归一
    中序遍历（LDR）是二叉树遍历的一种，也叫做中根遍历、中序周游。
    在二叉树中，中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。
     */
    // 记录遍历的前一个节点的值
    private Integer pre = null;
    public boolean isValidBST1(TreeNode root) {
        if (root == null){
            return true;
        }
        if (isValidBST1(root.left)){//左节点
            //当前节点
            if (pre !=null && pre >= root.val){
                return false;
            }
            pre = root.val;
            //右节点
            return isValidBST1(root.right);
        }
        return false;
    }

    /*递归二
    利用最大值最小值
     */
    public boolean isValidBST2(TreeNode root) {
        return isBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    public boolean isBST(TreeNode root, long max, long min) {
        if (root == null){
            return true;
        }
        if (root.val >= max || root.val <= min){
            return false;
        }
        return isBST(root.left, root.val, min) && isBST(root.right, max, root.val);
    }


    /*
    不对啊,没仔细读题!
    当前节点左边的都要比它小,右边的都要比他大!
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }
        if (root.left == null && root.right == null){//左右都空
            return true;
        } else if (root.left == null){//左空右不空
            if(root.val < root.right.val) {//有效
                return isValidBST(root.right);
            } else //无效
                return false;
        } else if (root.right == null){//右空左不空
            if(root.left.val < root.val) {//有效
                return isValidBST(root.left);
            } else//无效
                return false;
        } else {//左右都有
            if (root.left.val < root.val && root.val < root.right.val) {//有效
                return isValidBST(root.left) && isValidBST(root.right);
            } else //无效
                return false;
        }

    }
}
