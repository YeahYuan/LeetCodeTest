package dynamicProgramming;

import org.junit.Test;

/**爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。
 * 你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 斐波那契数求和
 * Created by lll on 19/8/4.
 */
public class ClimbStairs {
    @Test
    public void test(){
        System.out.println(climbStairs1(3));
    }


    //超出时间限制
    //暴力递归,时间复杂度2的n次方(倒着算,有递归树)
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        //先爬1阶或先爬2阶
        return climbStairs(n-1) + climbStairs(n-2);
    }

    /*
    方法一:动态规划
    不难发现，这个问题可以被分解为一些包含最优子结构的子问题，
    即它的最优解可以从其子问题的最优解来有效地构建，我们可以使用动态规划来解决这一问题。
    第 i 阶可以由以下两种方法得到：
    在第 (i-1) 阶后向上爬 1 阶。
    在第 (i-2) 阶后向上爬 2 阶。
    所以到达第 i 阶的方法总数就是到第 (i-1) 阶和第 (i-2) 阶的方法数之和。

    正着算,时间复杂度n.
     */
    public int climbStairs1(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /*
    没有用数组,空间复杂度为o(1),常量级空间
     */
    public int climbStairs2(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int first = 1;
        int second = 2;
        for (int i = 3; i<=n; i++){
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
