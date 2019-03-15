package com.dbdou.arts.leetcode;

/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 *
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (26.42%)
 * Total Accepted:    471.7K
 * Total Submissions: 1.8M
 * Testcase Example:  '"babad"'
 *
 * Given a string s, find the longest palindromic substring in s. You may
 * assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 *
 * Example 2:
 *
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 *
 */
public class Medium005LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        String targetStr = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length()-1; j >= i ; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 判断从i~j是否回文
                    if (i == j) {
                        if (targetStr.length() < 1) {
                            targetStr = String.valueOf(s.charAt(i));
                        }
                        break;
                    }
                    if (i == j-1) {
                        if (targetStr.length() < 2) {
                            targetStr = new String(new char[]{ s.charAt(i), s.charAt(i) });
                        }
                        break;
                    }
                    boolean palinFlag = true;
                    for (int k = i+1, l = j-1; l > k; k++, l--) {
                        if (s.charAt(k) != s.charAt(l)) {
                            // 不是回文
                            palinFlag = false;
                            break ;
                        }
                    }
                    if (palinFlag) {
                        if (targetStr.length() < j-i+1) {
                            targetStr = s.substring(i, j+1);
                        }
                    }
                }
            }
            if (targetStr.length() >= s.length()-i) {
                break;
            }
        }
        return targetStr;
    }

}
