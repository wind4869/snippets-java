package com.wind4869.snippets.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * FourSumCount, https://leetcode.com/problems/4sum-ii/
 * <p>
 * Time: O(n^2), Space: O(n^2)
 *
 * @author wind4869
 * @since 1.0.0
 */
public class FourSumCount {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A)
            for (int b : B)
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
        int count = 0;
        for (int c : C)
            for (int d : D)
                count += map.getOrDefault(-(c + d), 0);
        return count;
    }
}
