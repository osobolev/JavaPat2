package lec2;

/**
 * Пример producer/reader с использованием volatile
 */
public class Example3C {

    private static int result = 0;
    private static volatile boolean ready = false;

    public static void main(String[] args) {
        Runnable producer = () -> {
            sleep(100);
            result = 42;
            ready = true;
        };
        Runnable reader = () -> {
            while (!ready);
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
