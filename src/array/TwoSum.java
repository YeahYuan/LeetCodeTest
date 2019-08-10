package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * (以前做过)
 * Created by lll on 19/6/15.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println(Arrays.toString(twoSum3(nums, target)));

    }


    /*
    法一:
    常规方法,遍历所有元素
     */
    public static int[] twoSum1(int[] nums, int target) {
        int[] arr = new int[2];
        for (int x = 0; x < nums.length - 1; x++) {
            for (int y = x + 1; y < nums.length; y++) {
                if (nums[x] + nums[y] == target) {
                    arr[0] = x;
                    arr[1] = y;
                }
            }
        }
        return arr;
    }

    /*
    法二:
    两遍哈希
    需要一种更有效的方法来检查数组中是否存在目标元素。
    如果存在，我们需要找出它的索引。
    保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。

    一个简单的实现使用了两次迭代。
    在第一次迭代中，我们将每个元素的值和它的索引添加到表中。
    然后，在第二次迭代中，我们将检查每个元素所对应的目标元素（target-nums[i]）是否存在于表中。
    注意，该目标元素不能是 nums[i] 本身！
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i<nums.length; i++){
            map.put(nums[i], i);
        }
        for (int i = 0; i<nums.length; i++){
            int x = target - nums[i];
            if (map.containsKey(x) && map.get(x)!=i){
                return new int[] {i, map.get(x)};
            }
        }
        return null;
    }

    /*
    法三:
    一遍哈希
    在进行迭代并将元素插入到表中的同时，
    我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
    如果它存在，那我们已经找到了对应解，并立即将其返回。
     */
    public static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i<nums.length; i++){
            int x = target - nums[i];
            if (map.containsKey(x)){
                return new int[] {map.get(x), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
