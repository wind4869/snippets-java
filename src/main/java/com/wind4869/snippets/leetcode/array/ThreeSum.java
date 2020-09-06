package com.wind4869.snippets.leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ThreeSum, https://leetcode.com/problems/3sum/
 * <p>
 * Time: O(n^2), Space: O(1)
 *
 * @author wind4869
 * @since 1.0.0
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int low = i + 1, high = nums.length - 1, target = -nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == target) {
                    result.add(Arrays.asList(nums[i], nums[low++], nums[high--]));
                    while (low < high && nums[low] == nums[low - 1]) low++;
                    while (low < high && nums[high] == nums[high + 1]) high--;
                } else if (nums[low] + nums[high] < target) low++;
                else high--;
            }
        }
        return result;
    }
}
