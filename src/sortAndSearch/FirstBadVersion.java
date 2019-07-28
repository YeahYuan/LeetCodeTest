package sortAndSearch;

/**第一个错误的版本
 * 你是产品经理，目前正在带领一个团队开发新的产品。
 * 不幸的是，你的产品的最新版本没有通过质量检测。
 * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
 * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * Created by lll on 19/7/28.
 */
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
public class FirstBadVersion {
    boolean isBadVersion(int version) {
        return true;
    }

    /*
    二分查找
     */
    public int firstBadVersion(int n) {
        if (n == 1 || isBadVersion(1) == true)
            return 1;
        return firstBadVersion(1, n);
    }
    private int firstBadVersion(int start, int end){
        if (start +1 == end)
            return end;
        /*
        求中点技巧
        假设变量都是 int 值。
        二分查找求中点 int mid = (start + end) / 2
        但这样有缺点,如果 start = 2147483645，end = 2147483645，
        虽然都没有超出最大值，但加起来会造成溢出，导致mid计算错误。
            解法一:我们可以加一个 start 再减一个 start 将公式变形。
                (start + end) / 2 = (start + end + start - start) / 2 = start + (end - start) / 2
            解法二:位运算
                int mid = (start + end) >>> 1
                补码 的知识。
                >>为有符号右移，右移以后最高位保持原来的最高位。
                >>>无符号右移最高位补 0。
                利用整数的补码形式，最高位其实是符号位
                当 start + end溢出的时候，只是符号位收到了进位，
                而>>>这个右移可以带着符号位右移，所以之前的信息没有丢掉。

         */
//        int mid = start + (end-start)/2;
        int mid = (start + end) >>> 1;
        if (isBadVersion(mid) == true){
            return firstBadVersion(start, mid);
        } else
            return firstBadVersion(mid, end);
    }


    /*
    二分查找简化版
     */
    public int firstBadVersion1(int n) {
        int left = 1;
        int right = n;
        while (left < right){
            int mid = (left + right) >>> 1;
            if (isBadVersion(mid) == true){
                right = mid;
            } else {
                left = mid +1;
            }
        }
        return left;
    }
}
