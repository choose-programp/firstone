package test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author conrad
 * @date 2021/07/17
 */
public class Test9 {
    public static void main(String[] args) {

       new ConcurrentHashMap<>();
        HashMap<Integer, Integer> map = new HashMap<>(8);
        for (int i = 1; i <= 7; i++) {
            int sevenSlot = i * 8 + 7;
            map.put(sevenSlot, sevenSlot);
        }
    }
}
