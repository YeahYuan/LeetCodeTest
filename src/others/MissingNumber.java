package others;

import java.util.Arrays;

/**缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，
 * 找出 0 .. n 中没有出现在序列中的那个数。
 * 说明:
     你的算法应具有线性时间复杂度。
     你能否仅使用额外常数空间来实现?
 * Created by lll on 19/8/17.
 */
public class MissingNumber {
    /*
    方法一:排序后看数值与角标是否一一对应
     */
    public int missingNumber1(int[] nums){
        Arrays.sort(nums);
        for (int i =0; i<nums.length; i++){
            if (nums[i] != i){//排序之后一一对应,不相同的即为missingNumber
                return i;
            }
        }
        return nums.length;//均一一对应,则缺少n
    }
    /*
    方法二:1-n的和减去nums的和就是缺失的数字
     */
    public int missingNumber2(int[] nums){
        int n = nums.length;
        int standard = (1+n)*n/2;
        int sum = 0;
        for (int i:nums){
            sum += i;
        }
        return standard - sum;
    }

    /*
    方法三:
    角标与数值一起异或,最后剩下的就是缺失的数字
    符合说明(线性时间+常数空间)
     */
    public int missingNumber3(int[] nums){
        int res = nums.length;//n值
        for (int i =0; i<nums.length; i++){
            res = res^i^nums[i];
//            res += i - nums[i];//求和的另一种方法
        }
        return res;
    }
}
