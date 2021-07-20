package test;

import java.util.LinkedList;

/**
 * @author conrad
 * @date 2021/07/14
 */
public class Test6 {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.offerFirst("1");
        list.offerFirst("2");
        list.offerFirst("3");


        for (String s : list) {
            System.out.println(s);
        }
    }
}
