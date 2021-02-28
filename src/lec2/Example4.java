package lec2;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Пример разрушения не-потокобезопасных структур данных при обращении из нескольких потоков
 */
public class Example4 {

    public static void main(String[] args) {
        Collection<Integer> c = new TreeSet<>();
        Runnable code = () -> {
            for (int i = 0; i < 5000; i++) {
                c.add(i);
            }
        };
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(code);
            t.start();
        }
    }
}
