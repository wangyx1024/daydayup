package algorithm.sort;

import algorithm.util.Checker;
import algorithm.util.P;
import algorithm.util.U;

import java.util.Arrays;

public class Code04_FastSort {

    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 9, 8, 5};
        P.print(arr);

        fastSort(arr);
        P.print(arr);

        P.divider();

        check();
    }

    public static void check() {
        int times = 100000;
        while (times-- > 0) {
            int[] arr = Checker.generate(100, 100);
            int[] arr1 = Checker.copy(arr);
            int[] arr2 = Checker.copy(arr);

            try {
                fastSort(arr1);
                Arrays.sort(arr2);
//                System.out.println("arr1==" + Arrays.toString(arr1));
//                System.out.println("arr2==" + Arrays.toString(arr2));
//                P.divider();

                Checker.compare(arr1, arr2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                P.print(arr);
                P.print(arr1);
                P.print(arr2);
                break;
            }
        }

        System.out.println("成功!!!!");
    }

    static void fastSort(int[] arr) {
        // 两个指针分别标记<、>区，<、>区在两边，=区在中间
        // 把arr[r]选为num
        // i从l开始遍历，遇到的元素有三种情况：
        //     arr[i]<num，说明元素应该放到小于区，将i和小于区右侧的元素交换，小于区右扩，i++
        //     arr[i]>num，说明元素应该放到大于区，将i和大于区左侧的元素交换，大于区左扩，i不动（因为换过来的数还没看）
        //     arr[i]==num，说明元素应该放到等于区，i++就好
        // 遍历结束后，得到小于、大于区的两个指针，对l~less和more~r之间分别再递归
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    /**
     * 对于[left, right]的元素做partition
     * 取某个i作对比元素，从左到右遍历，比它小的放左边，比它大的放右边，和它一样大的放中间
     */
    private static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int flag = arr[right];
        int less = left - 1;
        int more = right + 1;

        int i = left;
        while (i < more) {
            if (arr[i] < flag) {
                U.swap(arr, i++, ++less);
            } else if (arr[i] > flag) {
                U.swap(arr, i, --more);
            } else {
                i++;
            }
        }

        process(arr, left, less);
        process(arr, more, right);
    }
}
