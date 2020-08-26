package com.wind4869.snippets.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ClimbingStairs, https://leetcode-cn.com/problems/climbing-stairs/
 *
 * @author wind4869
 * @since 1.0.0
 */
public class ClimbingStairs {
    private final Map<Integer, Integer> cache = new HashMap<>();

    public int climbStairs(int n) {
        cache.put(1, 1);
        cache.put(2, 2);
        return f(n);
    }

    private int f(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int y = f(n - 1) + f(n - 2);
        cache.put(n, y);
        return y;
    }
}
