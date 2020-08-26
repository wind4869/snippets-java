package com.wind4869.snippets.leetcode;

/**
 * ReverseInteger, https://leetcode-cn.com/problems/reverse-integer/
 *
 * <p>
 * 1 负数取模的结果还是负数：-1 % 2 == -1
 * 2 溢出的判断还有两种情况, 因为一定不会成立，故省略：
 * (1) result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10
 * (2) result == Integer.MIN_VALUE / 10 && digit < Integer.MIN_VALUE % 10
 *
 * @author wind4869
 * @since 1.0.0
 */
class ReverseInteger {
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10;
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            result = result * 10 + digit;
            x /= 10;
        }
        return result;
    }
}
