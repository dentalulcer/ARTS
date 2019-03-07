package com.dbdou.arts.array.easy;

/**
 * Created by gaozhili on 01/07/2018.
 */
public class Solution {


    public static void main(String[] args) {

        // int[] nums = new int[]{1,1,2,2,3,4,5};
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int length = removeDuplicates(nums);

        // System.out.println(length);
        for (int i=0; i<length; i++) {
            System.out.println(nums[i]);
        }

    }

    /**
     * 从排序数组中删除重复项
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {

        int length = nums.length;

        for (int i=1; i<length; i++) {
            if(nums[i] == nums[i-1]) {
                System.out.println(i + "-" + nums[i] + "-" + nums[i-1]);
                // 后面的元素往前移一位
                for (int k=i+1; k<nums.length; k++) {
                    System.err.println(k + "-" + nums[k] + "-" + nums[k-1]);
                    nums[k-1] = nums[k];
                }
                i -= 1;
                length -= 1;
            }
        }
        return length;
    }

}
