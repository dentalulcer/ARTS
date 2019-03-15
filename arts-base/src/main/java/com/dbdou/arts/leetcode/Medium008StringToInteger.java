package com.dbdou.arts.leetcode;

/*
 * @lc app=leetcode id=8 lang=java
 *
 * [8] String to Integer (atoi)
 *
 * https://leetcode.com/problems/string-to-integer-atoi/description/
 *
 * algorithms
 * Medium (14.45%)
 * Total Accepted:    324.4K
 * Total Submissions: 2.2M
 * Testcase Example:  '"42"'
 *
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this
 * character, takes an optional initial plus or minus sign followed by as many
 * numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of
 * this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid
 * integral number, or if no such sequence exists because either str is empty
 * or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *
 *
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical
 * value is out of the range of representable values, INT_MAX (231 − 1) or
 * INT_MIN (−231) is returned.
 *
 *
 * Example 1:
 *
 *
 * Input: "42"
 * Output: 42
 *
 *
 * Example 2:
 *
 *
 * Input: "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus
 * sign.
 * Then take as many numerical digits as possible, which gets 42.
 *
 *
 * Example 3:
 *
 *
 * Input: "4193 with words"
 * Output: 4193
 * Explanation: Conversion stops at digit '3' as the next character is not a
 * numerical digit.
 *
 *
 * Example 4:
 *
 *
 * Input: "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a
 * numerical
 * digit or a +/- sign. Therefore no valid conversion could be performed.
 *
 * Example 5:
 *
 *
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit
 * signed integer.
 * Thefore INT_MIN (−231) is returned.
 *
 */
public class Medium008StringToInteger {

    public int myAtoi(String str) {
        int strLen, result, signNum = 0, start = -1, end = -1;
        if(str != null && (strLen = str.length()) != 0) {
            end = strLen;
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    if (start == -1) {
                        start = i;
                        if ('-' == str.charAt(i)) {
                            signNum = 1;
                        } else if ('+' != str.charAt(i) && !Character.isDigit(str.charAt(i))) {
                            return 0;
                        }
                    } else {
                        if (!Character.isDigit(str.charAt(i))) {
                            end = i;
                            break;
                        }
                    }
                } else {
                    if (start > -1) {
                        end = i;
                        break;
                    }
                }
            }
        }
        if (start == -1 || start == end) return 0;

        String substring = str.substring(start, end);
        if ("-".equals(substring) || "+".equals(substring)) return 0;
        try {
            result = Integer.parseInt(substring);
        } catch (Exception e) {
            result = signNum == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return result;
    }

}
