package com.wind4869.snippets.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * ValidParentheses, https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @author wind4869
 * @since 1.0.0
 */
public class ValidParentheses {
    private final static Map<Character, Character> MAPPING = new HashMap();
    static {
        MAPPING.put(')', '(');
        MAPPING.put('}', '{');
        MAPPING.put(']', '[');
    }
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (Character c: s.toCharArray()) {
            if (MAPPING.containsKey(c)) {
                Character expected = MAPPING.get(c);
                Character actual = stack.pollFirst();
                if (!expected.equals(actual)) {
                    return false;
                }
            } else {
                stack.offerFirst(c);
            }
        }
        return stack.isEmpty();
    }
}
