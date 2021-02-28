package lec2;

/**
 * Пример producer/reader, не работающий из-за отсутствия синхронизации
 */
public class Example3A {

    private static int result = 0;
    private static boolean ready = false;

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
