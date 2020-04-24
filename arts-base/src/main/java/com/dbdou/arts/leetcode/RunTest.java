package com.dbdou.arts.leetcode;

/**
 * Created by 002192 .
 */
public class RunTest {

    public static void main(String[] args) {

//        Easy001TwoSum run001 = new Easy001TwoSum();
//        int[] nums = {2, 7, 11, 15};
//        int target = 9;
//        int[] result = run001.twoSum(nums, target);
//        System.out.println(result[0] + " " + result[1]);

        // (2 -> 4 -> 3) + (5 -> 6 -> 4)
//        Medium002AddTwoNumbers run002 = new Medium002AddTwoNumbers();
//        ListNode l1First = new ListNode(5);
//        ListNode l2First = new ListNode(5);
//        ListNode listNode = run002.addTwoNumbers(l1First, l2First);
//
//        System.out.println(listNode.val);
//        System.out.println(listNode.next.val);
//        System.out.println(listNode.next.next.val);

//        Medium003LongestSubstringWithoutRepeatingCharacters run003 = new Medium003LongestSubstringWithoutRepeatingCharacters();
//        int result003 = run003.lengthOfLongestSubstring(" ");
//        System.out.println(result003);

//        Medium005LongestPalindromicSubstring run005 = new Medium005LongestPalindromicSubstring();
//        String result005 = run005.longestPalindrome("aaabaaaa");
//        System.out.println(result005);

//        Medium006ZigZagConversion run006 = new Medium006ZigZagConversion();
//        String result006 = run006.convert("ABCDE", 2);
//        System.out.println(result006);

//        Easy007ReverseInteger run007 = new Easy007ReverseInteger();
//        int result007 = run007.reverse(0);
//        System.out.println(result007);

//        Medium008StringToInteger run008 = new Medium008StringToInteger();
//        int result008 = run008.myAtoi("-");
//        System.out.println(result008);

//        Easy009PalindromeNumber run009 = new Easy009PalindromeNumber();
//        boolean result009 = run009.isPalindrome(1012332101);
//        System.out.println(result009);

        Easy014LongestCommonPrefix run014 = new Easy014LongestCommonPrefix();
        String result014 = run014.longestCommonPrefix(new String[]{"flower","flow","flight"});
        System.out.println(result014);

    }

}
