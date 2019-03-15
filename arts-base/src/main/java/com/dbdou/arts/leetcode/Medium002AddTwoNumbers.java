package com.dbdou.arts.leetcode;

/*
 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
 *
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (30.48%)
 * Total Accepted:    754.2K
 * Total Submissions: 2.5M
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 *
 * Example:
 *
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * 需要当作 字符 处理
 */

import java.util.ArrayList;
import java.util.List;

public class Medium002AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        String targetString;
        do {
            list1.add(l1.val);
            l1 = l1.next;
        } while (l1 != null);

        do {
            list2.add(l2.val);
            l2 = l2.next;
        } while (l2 != null);

        int maxLength = Math.max(list1.size(), list2.size());
        if (maxLength == 1) {
            int tmp = list1.get(0) + list2.get(0);
            if (tmp < 10) {
                return new ListNode(tmp);
            } else {
                targetString = tmp % 10 + "" + tmp / 10;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            boolean grow = false;
            for (int i = 0; i < maxLength; i++) {
                int tmp = grow ? 1 : 0;
                grow = false;
                if (list1.size() > i && list2.size() > i) {
                    tmp += list1.get(i) + list2.get(i);
                } else if (list1.size() > i) {
                    tmp += list1.get(i);
                } else {
                    tmp += list2.get(i);
                }
                if (tmp < 10) {
                    sb.append(tmp);
                } else {
                    sb.append(tmp % 10);
                    grow = true;
                }
            }
            targetString = grow ? sb.append(1).toString() : sb.toString();
        }
        List<ListNode> list = new ArrayList<>();
        for (int i = 0; i < targetString.length(); i++) {
            list.add(new ListNode(Integer.parseInt(String.valueOf(targetString.charAt(i)))));
            if (list.size() > 1) {
                list.get(list.size()-2).next = list.get(list.size()-1);
            }
        }
        list.get(list.size()-2).next = list.get(list.size()-1);
        return list.get(0);
    }


}

