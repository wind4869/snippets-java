package com.wind4869.snippets.leetcode;

/**
 * BestTimeToBuyAndSellStock, https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author wind4869
 * @since 1.0.0
 */
public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int prevMax = prices[1] - prices[0];
        int finalMax = prevMax;
        for (int i = 2; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            prevMax = prevMax > 0 ? prevMax + profit : profit;
            if (prevMax > finalMax) {
                finalMax = prevMax;
            }
        }
        return Math.max(finalMax, 0);
    }
}
