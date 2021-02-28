package lec2;

/**
 * Пример producer/reader с использованием synchronized
 */
public class Example3B {

    private static int result = 0;
    private static boolean ready = false;

    public static void main(String[] args) {
        Object lock = new Object();
        Runnable producer = () -> {
            sleep(100);
            synchronized (lock) {
                result = 42;
                ready = true;
            }
        };
        Runnable reader = () -> {
            while (true) {
                synchronized (lock) {
                    if (ready) {
                        System.out.println(result);
                        break;
                    }
                }
            }
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
