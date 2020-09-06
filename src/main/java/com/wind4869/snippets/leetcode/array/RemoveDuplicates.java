package com.wind4869.snippets.leetcode.array;

/**
 * RemoveDuplicates, https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * <p>
 * Time: O(n), Space: O(1)
 *
 * @author wind4869
 * @since 1.0.0
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (num != nums[i]) {
                nums[++i] = num;
            }
        }
        return i + 1;
    }
}
