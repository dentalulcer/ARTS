package com.dbdou.arts.leetcode;

/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (26.62%)
 * Total Accepted:    762.4K
 * Total Submissions: 2.9M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string, find the length of the longest substring without repeating
 * characters.
 *
 *
 * Example 1:
 *
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 *
 *
 * Example 2:
 *
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 *
 *
 * Example 3:
 *
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * ⁠            Note that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.
 *
 *
 *
 *
 *
 */
public class Medium003LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int maxLength = 1;
        for (int i = 0; i < s.length()-1; i++) {
            int length = 1;
            door:
            for (int j = i+1; j < s.length(); j++) {
                for (int k = i; k < j; k++) {
                    if (s.charAt(j) == s.charAt(k)) {
                        break door;
                    }
                }
                length++;
            }
            maxLength = Math.max(maxLength, length);
            if (maxLength > (s.length() - i)) {
                break;
            }
        }
        return maxLength;
    }

}
