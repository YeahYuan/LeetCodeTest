package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * Created by lll on 19/8/31.
 */
public class ContainsDuplicate {
    /*
    方法一:双层循环
    超出时间限制

    即官方题解的朴素线性查找,不同的是二次循环用前i-1个的,另外还有"循环不变式"的概念:
    为了证明算法的正确性，我们定义了循环不变式。循环不变式是指在每次迭代前和后均保持不变的性质。
    下面就是循环不变式:
    在下一次搜索之前,搜索过的整数中没有重复的整数。

    循环不变式在循环之前为真，因为还没有搜索过的整数。每次循环，我们查找当前元素的任何可能重复。如果发现重复项,则函数返回 True 退出；如果没有发现，则不变式仍然成立。

     */
    public boolean containsDuplicate1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

    /*
    方法二:排序+比较
     */
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1])
                return true;
        }
        return false;
    }

    /*
    方法三:哈希表(set)
     */
    public boolean containsDuplicate3(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int n : nums){
            if (!set.add(n)) return true;//set.add不成功返回false,表示有重复int
        }
        return false;
    }
}
