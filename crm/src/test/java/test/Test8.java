package test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author conrad
 * @date 2021/07/15
 */
public class Test8 {
    public static void main(String[] args) {
        int[] array = new int[]{0, 2, 5, 8, 9, 8, 8, 8, 9, 5, 6, 8, 2, 5, 8, 9, 2, 2, 6, 5, 2, 8, 8, 5, 6, 9};
        int[] nums = new int[10];

        int[] init = new int[10000];
        Random random = new SecureRandom();
        for (int i = 0; i < init.length; i++) {
            int i1 = random.nextInt(10);
            init[i] = i1;
        }
        System.out.println(new Date().getTime());
        int[] ints = countSort(init, nums);
        System.out.println(new Date().getTime());
        System.out.println(new Date().getTime());
        Arrays.sort(init);
        System.out.println(new Date().getTime());
        for (int i = 0; i < init.length; i++) {
            if (ints[i] != init[i]) {
                System.out.println(false);
                break;
            }
        }
    }

    private static int[] countSort(int[] array, int[] nums) {
        int[] sortArray = new int[array.length];
        for (int j : array) {
            nums[j]++;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + nums[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            sortArray[--nums[array[i]]] = array[i];
        }
        return sortArray;
    }

}
