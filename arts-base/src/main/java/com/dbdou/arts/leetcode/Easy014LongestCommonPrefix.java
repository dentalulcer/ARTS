package com.dbdou.arts.leetcode;

/*
 * @lc app=leetcode id=14 lang=java
 *
 * [14] Longest Common Prefix
 *
 * https://leetcode.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (32.92%)
 * Total Accepted:    422.3K
 * Total Submissions: 1.3M
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 *
 *
 * Example 2:
 *
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 *
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 *
 */
public class Easy014LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs == null || strs.length == 0) {
            return result;
        }
        if (strs[0].length() == 0) {
            return result;
        }
        result = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].length() == 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder("");
            for (int j = 0; j < strs[i].length() && j < result.length(); j++) {
                if (strs[i].charAt(j) != result.charAt(j)) {
                    break;
                }
                sb.append(result.charAt(j));
            }
            if (sb.length() == 0) {
                return "";
            }
            result = sb.toString();
        }
        return result;
    }

}
