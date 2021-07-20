package test;

/**
 * @author conrad
 * @date 2021/07/09
 */
public class Test3 {
    public static void main(String[] args) {
        int[] x = new int[]{3, 30, 324, 5, 9};
        //3593034
        int[] y = new int[x.length * 5];
        int count = 0;
        for (int k : x) {
            if (k < 10) {
                y[count++] = k;
            } else if (k > 10 && k < 100) {
                y[count++] = k % 10;
                y[count++] = k / 10;
            } else if (k > 100 && k < 1000) {
                y[count++] = k % 100 % 10;
                y[count++] = k % 100 / 10;
                y[count++] = k / 100;
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.println(y[i]);
        }

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (y[j] < y[j+1]){
                    swap(y, j, j+1);
                }
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.print(y[i]);
        }
    }

    public static void swap(int[] array, int x,int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
