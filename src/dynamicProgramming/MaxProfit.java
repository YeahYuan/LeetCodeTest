package dynamicProgramming;

/**买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * 注意你不能在买入股票前卖出股票。
 * 动态规划:n天的最大收益 = max(n-1天的最大收益,n天-前n-1天的最低价)
 * Created by lll on 19/8/4.
 */
public class MaxProfit {

    /*
    方法一:暴力法 双层循环
     */
    public int maxProfit1(int[] prices) {
        if (prices.length <= 1)
            return 0;

        int max = 0;
        for (int x = prices.length-1; x>=0; x--){
            for (int y = 0; y<x; y++){
                max = prices[x]-prices[y] > max ? prices[x]-prices[y] : max;
            }
        }
        return max;
    }

    /*
    方法二:一次遍历
    最低价和最大利润
     */
    public int maxProfit2(int[] prices) {
        if (prices.length <= 1)
            return 0;

        int minPrice = prices[0];
        int maxProfit = 0;
        for (int i =0; i < prices.length; i++){
            if (prices[i] < minPrice){
                minPrice  = prices[i];
            }else if (prices[i] - minPrice > maxProfit){
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    /*
    方法二:一次遍历(简化书写)
     */
    public int maxProfit22(int[] prices) {
        if (prices.length <= 1)
            return 0;

        int buy = -prices[0];
        int maxProfit = 0;
        for (int i =0; i < prices.length; i++){
            buy = Math.max(buy,prices[i]);
            //最大利润=max{前一天最大利润, 今天的价格 - 之前最低价格}
            maxProfit = Math.max(maxProfit, prices[i]+buy);
        }
        return maxProfit;
    }
}
