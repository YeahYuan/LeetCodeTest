package array;

/**
 * 买卖股票的最佳时机 II
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * Created by lll on 19/8/31.
 */
public class MaxProfit {
    /*方法一
    我的思路:
    1.buy记录当前最低值
    2.sell记录当前的最高值(大于buy)
    3.sell后面的值出现下降,表示需要卖出,累加profit,重置buy和sell
     */
    public int maxProfit1(int[] prices) {
        int profit = 0;
        if (prices.length < 2) return profit;
        int buy=Integer.MAX_VALUE, sell=-1;
        for (int i = 0; i<prices.length; i++){
            if (prices[i] < buy) {
                buy = prices[i];
            }
            if (prices[i] >= sell && prices[i] > buy) {
                sell = prices[i];
                if ((i+1<prices.length && prices[i] > prices[i+1]) || i+1==prices.length) {
                    profit += (sell - buy);
                    buy=Integer.MAX_VALUE;
                    sell=-1;
                }
            }
        }
        return profit;
    }

    /*方法一优化:峰谷法
    */
    public int maxProfit2(int[] prices) {
        int i = 0, profit = 0, valley, peak;
        while (i+1 < prices.length){
            while (i+1 < prices.length && prices[i] >= prices[i+1])//跳过下降,遇到上升保存
                i++;
            valley = prices[i];//找低谷
            while (i+1 < prices.length && prices[i] <= prices[i+1])//跳过上升,遇到下降保存
                i++;
            peak = prices[i];//找高峰
            profit += (peak-valley);
        }
        return profit;
    }


    /*方法三
    贪心算法?上坡路程?
    [7, 1, 5, 6] 第二天买入，第四天卖出，收益最大（6-1），所以一般人可能会想，怎么判断不是第三天就卖出了呢?
    这里就把问题复杂化了，根据题目的意思，当天卖出以后，当天还可以买入，
    所以其实可以第三天卖出，第三天买入，第四天又卖出（（5-1）+ （6-5） === 6 - 1）。
    所以算法可以直接简化为只要今天比昨天大，就卖出。

    代码简化了,但是效率不如方法一高哈哈哈哈!!!
     */
    public int maxProfit3(int[] prices) {
        int profit = 0;
        if (prices.length < 2) return profit;
        for (int i = 0; i<prices.length-1; i++){
            if (prices[i] <= prices[i+1])
                profit += (prices[i+1]-prices[i]);
        }
        return profit;
    }

    /*
    还有一个暴力法
    用递归+循环,遍历所有可能的收益比较最大值
    没意思
     */
}
