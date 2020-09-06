package com.wind4869.snippets.leetcode.array;

import java.util.Arrays;

/**
 * ThreeSumClosest, https://leetcode.com/problems/3sum-closest/
 * <p>
 * Time: O(n^2), Space: O(1)
 *
 * @author wind4869
 * @since 1.0.0
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == target) return sum;
                else if (sum < target) low++;
                else high--;
                if (Math.abs(sum - target) < Math.abs(result - target)) result = sum;
            }
        }
        return result;
    }
}
