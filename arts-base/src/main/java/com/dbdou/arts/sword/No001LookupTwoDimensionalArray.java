package com.dbdou.arts.sword;

public class No001LookupTwoDimensionalArray {

    public static void main(String[] args) {

        System.out.println(find(7, new int[][]{{4, 7, 10, 13}, {6, 8, 11, 15}}));
        System.out.println(find2(7, new int[][]{{4, 7, 10, 13}, {6, 8, 11, 15}}));

    }

    public static boolean find2(int target, int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }

        for (int[] ints : array) {
            int high = ints.length - 1;
            int low = 0;
            if (target == ints[0] || target == ints[high]) {
                return true;
            }
            if (target < ints[0] && target < ints[high]) {
                return false;
            }
            int center = high / 2;
            while (center > low && center < high) {
                if (target < ints[center]) {
                    high = center;
                    System.out.println("less: low=" + low + ", high=" + high);
                } else if (target > ints[center]) {
                    low = center;
                    System.out.println("more: low=" + low + ", high=" + high);
                } else {
                    return true;
                }
                center = (high + low) / 2;
            }
        }
        return false;
    }

    public static boolean find(int target, int[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        int i = rows - 1, j = 0;
        while (i >= 0 && j < cols) {
            if (target < array[i][j]) {
                i--;
            } else if (target > array[i][j]) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
