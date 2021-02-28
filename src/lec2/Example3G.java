package lec2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Пример producer/reader с использованием BlockingQueue
 */
public class Example3G {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        Runnable producer = () -> {
            try {
                Thread.sleep(100);
                queue.offer(42);
            } catch (InterruptedException ex) {
                // завершение потока
            }
        };
        Runnable reader = () -> {
            try {
                Integer result = queue.take();
                System.out.println(result);
            } catch (InterruptedException ex) {
                // завершение потока
            }
        };
        new Thread(reader).start();
        new Thread(producer).start();
    }
}
