package array;

/**
 * 从排序数组中删除重复项
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 不需要考虑数组中超出新长度后面的元素。
 * Created by lll on 19/8/31.
 */
public class RemoveDuplicates {
    /*
    双指针法
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length < 2) return nums.length;
        int slow = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[slow] == nums[fast]) {
                fast++;
            } else {
                slow++;
                nums[slow] = nums[fast];
                fast++;
            }
        }
        return slow + 1;
    }

    /*
    双指针法代码优化
     */
    public int removeDuplicates1(int[] nums) {
        if (nums.length < 2) return nums.length;
        int slow = 0, fast = 1;
        for (; fast < nums.length; fast++) {//fast++作为循环条件
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
