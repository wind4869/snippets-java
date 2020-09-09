package com.wind4869.snippets.leetcode.array;

/**
 * SearchMatrix, https://leetcode.com/problems/search-a-2d-matrix-ii/
 * <p>
 * Time: O(m + n), Space: O(1)
 *
 * @author wind4869
 * @since 1.0.0
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else row++;
        }
        return false;
    }
}
