package com.dbdou.arts.leetcode;

/**
 * Created by 002192 .
 */
public class Easy007ReverseInteger {

    public int reverse(int x) {
        if (x == 0) return 0;
        while (x % 10 == 0) {
            x = x/10;
        }
        boolean flag = true;
        if (x < 0) {
            flag = false;
            x = -x;
        }
        String str = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        for (int i = str.length()-1; i >= 0 ; i--) {
            sb.append(str.charAt(i));
        }
        try {
            x = Integer.valueOf(sb.toString());
        } catch (NumberFormatException e) {
            x = 0;
        }
        return flag ? x : -x;
    }

}
