package dynamicProgramming;

/**最大子序和
 * 给定一个整数数组 nums ，
 * 找到一个具有最大和的连续子数组（子数组最少包含一个元素），
 * 返回其最大和。
 * Created by lll on 19/8/4.
 */
public class MaxSubArray {

    /*
    动态规划的是首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans
    如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
    如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
    每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
     */
    public int maxSubArray(int[] nums) {
        int sum = 0;//当前最大连续子序列和
        int ans = nums[0];//最终结果
        for (int num : nums){
            //这里有一点难懂(正数增益)
            if (sum > 0)//if (sum+num > num)
                sum += num;
            else
                sum = num;
            ans = Math.max(ans,sum);
        }
        return ans;
    }

    //暴力遍历
    //超出时间限制,但应该也能算出来
    public int maxSubArray0(int[] nums) {
        int max = nums[0];
        if (nums.length <= 1)
            return max;

        for (int i =1; i < nums.length; i++){
            for (int j =0; j <= i; j++){
                max = Math.max(max, sum(nums, j, i));
            }
        }
        return max;
    }
    private int sum(int[] nums, int start, int end){
        int sum = 0;
        for ( ; start <= end ; start++){
            sum += nums[start];
        }
        return sum;
    }
}
