package array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * Created by lll on 19/6/12.
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};

        System.out.println(singleNumber1(nums));
        System.out.println(singleNumber2(nums));
        System.out.println(singleNumber3(nums));
        System.out.println(singleNumber4(nums));
    }

    /*
    数组先排序
    比较法
    思路：先对数组进行排序，然后对 nums[i] 和 nums[i + 1]进行比较，如相等，i += 2，继续下一组比较，直到取到不相等的一组。
　　注意：首先这个数组的长度肯定是奇数（目标数字只出现一次，其他所有数字出现两次），所以如果上述步骤没有找到不相等的一组数，那么肯定是数组的最后一个数字是单独出现的。
     */
    public static int singleNumber1(int[] nums) {
        Arrays.sort(nums);
        for (int x = 0; x < nums.length - 1; x += 2) {
            if (nums[x] != nums[x + 1]) {
                return nums[x];
            }
        }
        return nums[nums.length - 1];
    }

    /*
    去重法
    HashSet除重
    思路：利用HashSet的特性，删除重复的数组元素，最后剩下一个单独的元素，返回即可。
     */
    public static int singleNumber2(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                set.remove(nums[i]);
            }
        }
        return set.iterator().next();
    }

    /*
    求差法
    思路：先对数组排序，显而易见的，单独出现一次的数据必然是出现在数组下标为偶数的位置（下标从0开始），那么所有奇数下标的元素之和减去偶数下标的元素之和，就是需要求得的结果。
     */
    public static int singleNumber3(int[] nums) {
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            result = (i % 2 == 0) ? result + nums[i] : result - nums[i];
        }
        return result;


//        不够简洁
//        Arrays.sort(nums);
//        int big = 0;
//        int small = 0;
//        for (int x = 0; x < nums.length; x = x+2){
//            big += nums[x];
//        }
//        for (int y = 1; y < nums.length; y = y+2){
//            small += nums[y];
//        }
//        return big - small;
    }

    /*
    异或法
    思路：根据异或运算的特点，相同的数字经过异或运算后结果为0，除单独出现一次的数字外，其他数字都是出现两次的，那么这些数字经过异或运算后结果一定是0。而任何数字与0进行异或运算都是该数字本身。所以对数组所有元素进行异或运算，运算结果就是题目的答案。
     */
    public static int singleNumber4(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

}

