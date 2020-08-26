package com.wind4869.snippets.leetcode;

/**
 * MaximumSubarray, https://leetcode-cn.com/problems/maximum-subarray/
 *
 * @author wind4869
 * @since 1.0.0
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int prevMax = 0;
        int finalMax = nums[0];
        for (int num : nums) {
            prevMax = prevMax > 0 ? prevMax + num : num;
            if (prevMax > finalMax) {
                finalMax = prevMax;
            }
        }
        return finalMax;
    }
}
