package tree;

import org.junit.Test;

/**将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * Created by lll on 19/7/28.
 */
public class SortedArrayToBST {

    /*
    方法一:递归
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums);
    }

    private TreeNode helper(int[] nums){
        if(nums == null || nums.length == 0){
            return null;
        }
        int mid = nums.length/2;
        TreeNode root = new TreeNode(nums[mid]);

        int leftLength = mid;
        if (leftLength > 0){
            int[] left = new int[leftLength];
            for (int i=0; i<leftLength; i++){
                left[i] = nums[i];
            }
            root.left = helper(left);
        }

        int rightLength = nums.length-mid-1;
        if (rightLength > 0){
            int[] right = new int[rightLength];
            for (int i=0; i<rightLength; i++){
                right[i] = nums[mid+1+i];
            }
            root.right = helper(right);
        }
        return root;
    }

    /*
    方法二:递归的简单方法(不需要新的数组,TreeNode的传递也简化了)
    二叉搜索树的中序遍历刚好可以输出一个升序数组。
    题目给出的升序数组就是二叉搜索树的中序遍历。
    通过中序遍历加前序遍历或者中序遍历加后序遍历来还原一棵树。
    前序（后序）遍历提供根节点！然后根据根节点，就可以递归的生成左右子树。
    平衡二叉树，把根节点选为数组的中点即可。
    找到了根节点，然后把数组一分为二，进入递归即可。注意这里的边界情况，包括左边界，不包括右边界。
     */
    public TreeNode sortedArrayToBST1(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        TreeNode root = sortedArrayToBST(nums, 0, nums.length);
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start >= end)
            return null;
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid);
        root.right = sortedArrayToBST(nums, mid+1, end);
        return  root;
    }

}
