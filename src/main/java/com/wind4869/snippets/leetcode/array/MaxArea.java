package com.wind4869.snippets.leetcode.array;

/**
 * MaxArea, https://leetcode.com/problems/container-with-most-water/
 * <p>
 * Time: O(n), Space: O(1)
 *
 * @author wind4869
 * @since 1.0.0
 */
public class MaxArea {
    public int maxArea(int[] height) {
        int low = 0, high = height.length - 1, maxArea = 0;
        while (low < high) {
            maxArea = Math.max(maxArea, Math.min(height[low], height[high]) * (high - low));
            if (height[low] < height[high]) low++;
            else high--;
        }
        return maxArea;
    }
}
