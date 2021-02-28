package lec2;

/**
 * Пример producer/reader с использованием wait/notifyAll
 */
public class Example3E {

    private static int result = 0;
    private static boolean ready = false;

    public static void main(String[] args) {
        Object lock = new Object();
        Runnable producer = () -> {
            try {
                Thread.sleep(100);
                synchronized (lock) {
                    result = 42;
                    ready = true;
                    lock.notifyAll();
                }
            } catch (InterruptedException ex) {
                // завершение потока
            }
        };
        Runnable reader = () -> {
            try {
                synchronized (lock) {
                    while (!ready) {
                        lock.wait();
                    }
                    System.out.println(result);
                }
            } catch (InterruptedException ex) {
                // завершение потока
            }
        };
        new Thread(reader).start();
        new Thread(producer).start();
    }
}
