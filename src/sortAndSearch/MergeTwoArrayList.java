package sortAndSearch;

import org.junit.Test;

import java.util.Arrays;


/**合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，
 * 将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * Created by lll on 19/7/28.
 */
public class MergeTwoArrayList {

    /*
    方法一:合并后排序
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    /*
    方法二:双指针(从前往后)
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] nums = nums1.clone();
        int x=0, y=0, z=0;
        for (; z<m+n; z++){
            if (x<m && y<n) {
                if (nums[x] <= nums2[y]) {
                    nums1[z] = nums[x];
                    x++;
                } else {
                    nums1[z] = nums2[y];
                    y++;
                }
            } else if (x < m){
                nums1[z] = nums[x];
                x++;
            } else if (y < n){
                nums1[z] = nums2[y];
                y++;
            } else
                break;
        }

    }

    @Test
    public void test(){
        merge22(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
    }
    /*
    方法二简化:双指针(从前往后)
     */
    public void merge22(int[] nums1, int m, int[] nums2, int n) {
        int[] nums = nums1.clone();
        int x=0, y=0, z=0;
        while (x<m && y<n){
            nums1[z++] = nums[x] <= nums2[y] ? nums[x++] : nums2[y++];
        }
        if (x<m){
            System.arraycopy(nums, x, nums1, z, m-x);
        }
        if (y<n){
            System.arraycopy(nums2, y, nums1, z, n-y);
        }
    }

    /*
    方法三:双指针(从后往前)
    不需要额外空间
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int x=m-1, y=n-1, z =m+n-1;
        while (x>=0 && y>=0) {
            nums1[z--] = nums1[x] >= nums2[y] ? nums1[x--] : nums2[y--];
        }
        //如果nums1有多不需要复制,本来就在数组中
//        if (x>=0){
//            System.arraycopy(nums1, 0, nums1, 0, x+1);
//        }
//        if (y>=0){
            System.arraycopy(nums2, 0, nums1, 0, y+1);
//        }
    }
}
