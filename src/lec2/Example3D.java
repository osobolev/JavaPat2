package lec2;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Пример producer/reader с использованием AtomicBoolean
 */
public class Example3D {

    private static int result = 0;
    private static AtomicBoolean ready = new AtomicBoolean(false);

    public static void main(String[] args) {
        Runnable producer = () -> {
            sleep(100);
            result = 42;
            ready.set(true);
        };
        Runnable reader = () -> {
            while (!ready.get());
            System.out.println(result);
        };
        new Thread(reader).start();
        new Thread(producer).start();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            // игнорируем для простоты
        }
    }
}
