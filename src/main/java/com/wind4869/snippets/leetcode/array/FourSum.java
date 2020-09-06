package com.wind4869.snippets.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FourSum, https://leetcode.com/problems/4sum/
 * <p>
 * Time: O(n^3), Space: O(1)
 *
 * @author wind4869
 * @since 1.0.0
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int low = j + 1, high = nums.length - 1;
                while (low < high) {
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[low++], nums[high--]));
                        while (low < high && nums[low] == nums[low - 1]) low++;
                        while (low < high && nums[high] == nums[high + 1]) high--;
                    } else if (sum < target) low++;
                    else high--;
                }
            }
        }
        return result;
    }
}
