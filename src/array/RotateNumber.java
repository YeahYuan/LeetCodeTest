package array;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的原地算法。
 * Created by lll on 19/6/15.
 */
public class RotateNumber {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        rotate2(nums, k);

    }


    /*
    不符合要求,需要直接修改原数组
     */
    public static void rotate0(int[] nums, int k) {

        int[] newNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int index = i+k;
            while (index >= nums.length){
                index -= nums.length;
            }
            newNums[index] = nums[i];
        }
        System.out.println(Arrays.toString(newNums));
    }

    /*
    常规方法,用一个数组中转
    这个比2快哎
     */
    public static void rotate1(int[] nums, int k) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int index = i+k;
            while (index >= nums.length){
                index -= nums.length;
            }
            temp[index] = nums[i];
        }
        System.out.println(Arrays.toString(temp));
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }
    //与1一样,用取模替代循环判断
    public static void rotate11(int[] nums, int k) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //用取模替代循环判断
            int index = (i+k) % nums.length;
            temp[index] = nums[i];
        }
        System.out.println(Arrays.toString(temp));
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }

    /*
    每次右移一位
    双重循环
    比较慢
     */
    public static void rotate2(int[] nums, int k) {
        //判断不需要移动的情况
        if(k==0 || k%nums.length ==0){
            System.out.println(Arrays.toString(nums));
            return;
        }
        for (int i =0; i<k%nums.length; i++){
            int temp = nums[nums.length-1] ;
            for (int j = nums.length-1 ; j >0; j--) {
                nums[j] = nums[j-1];
            }
            nums[0] = temp;
        }
        System.out.println(Arrays.toString(nums));
    }



}
