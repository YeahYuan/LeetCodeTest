package dynamicProgramming;

/**打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，
 * 计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * Created by lll on 19/8/4.
 */
public class Rob {


    /*
    方法一:动态规划
    和爬楼梯类似
     */
    public int rob1(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];

        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i<len; i++){
            dp[i] = Math.max(dp[i-1], //不偷这家
                    nums[i]+dp[i-2]); //偷这家
        }
        return dp[len-1];
    }
    /*
    方法二:动态规划(不用数组)
    和爬楼梯类似
     */
    public int rob2(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);
        for (int i = 2; i<len; i++){
            int third = Math.max(second, //不偷这家
                    nums[i]+first); //偷这家
            first = second;
            second = third;
        }
        return second;
    }

    /*
    官方版(简化2)
     */
    public int rob(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;

    }
}
