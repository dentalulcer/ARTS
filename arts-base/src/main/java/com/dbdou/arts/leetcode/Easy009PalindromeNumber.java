package com.dbdou.arts.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=9 lang=java
 *
 * [9] Palindrome Number
 *
 * https://leetcode.com/problems/palindrome-number/description/
 *
 * algorithms
 * Easy (41.71%)
 * Total Accepted:    525.4K
 * Total Submissions: 1.2M
 * Testcase Example:  '121'
 *
 * Determine whether an integer is a palindrome. An integer is a palindrome
 * when it reads the same backward as forward.
 *
 * Example 1:
 *
 *
 * Input: 121
 * Output: true
 *
 *
 * Example 2:
 *
 *
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it
 * becomes 121-. Therefore it is not a palindrome.
 *
 *
 * Example 3:
 *
 *
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a
 * palindrome.
 *
 *
 * Follow up:
 *
 * Coud you solve it without converting the integer to a string?
 *
 */
public class Easy009PalindromeNumber {

    public boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        if (x%10 == 0) {
            return false;
        }

        List<Integer> list = new ArrayList<>();
        while (true) {
            list.add(x%10);
            if (x < 10) {
                break;
            }
            x = x / 10;
        }

        for (int i = 0; i < list.size()/2; i++) {
            if (!list.get(i).equals(list.get(list.size()-1-i))) {
                return false;
            }
        }
        return true;
    }

}
