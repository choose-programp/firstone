package test;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author conrad
 * @date 2021/07/18
 */
public class Test11 {
    public static void main(String[] args) {
        int[] init = new int[100000];
        Random random = new SecureRandom();
        for (int i = 0; i < init.length; i++) {
            int i1 = random.nextInt(1000000);
            init[i] = i1;
        }
        System.out.println(new Date().getTime());
        insertSort(init);
        //Arrays.sort(init);
        System.out.println(new Date().getTime());
        checkSort(init, init);
        print(init);

    }

    private static int[] copyInitArray(int[] init) {
        return Arrays.copyOf(init, init.length);
    }

    private static void checkSort(int[] hasSort, int[] initArray) {
        int[] copyInitArray = copyInitArray(initArray);
        Arrays.sort(copyInitArray);
        for (int i = 0; i < copyInitArray.length; i++) {
            if (hasSort[i] != copyInitArray[i]) {
                System.out.println(false);
                break;
            }
        }
        System.out.println(true);
    }

    private static void insertSort(int[] ints) {
        for (int i = 1; i < ints.length; i++) {
            for (int j = i; j > 0; j--) {
                if (ints[j] < ints[j - 1]) {
                    swap(ints, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    private static void insertSort2(int[] ints) {
        for (int i = 1; i < ints.length; i++) {
            for (int j = i; j > 0 && ints[j] < ints[j - 1]; j--) {
                swap(ints, j, j - 1);
            }
        }
    }

    public static void swap(int[] x, int y, int z) {
        int temp = x[y];
        x[y] = x[z];
        x[z] = temp;
    }

    public static void print(int[] x) {
        for (int j : x) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
