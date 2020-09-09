package com.wind4869.snippets.leetcode.array;

/**
 * MaxSubArray, https://leetcode.com/problems/maximum-subarray/submissions/
 * <p>
 * Time: O(n), Space: O(1)
 *
 * @author wind4869
 * @since 1.0.0
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int maxFinal = nums[0], maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxFinal = Math.max(maxFinal, maxEndingHere);
        }
        return maxFinal;
    }
}
