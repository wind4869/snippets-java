package com.wind4869.snippets.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * TwoSum, https://leetcode.com/problems/two-sum/
 * <p>
 * Time: O(n), Space: O(n)
 *
 * @author wind4869
 * @since 1.0.0
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
