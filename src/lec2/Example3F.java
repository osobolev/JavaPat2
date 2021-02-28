package lec2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Пример producer/reader с использованием ReentrantLock/Condition
 */
public class Example3F {

    private static int result = 0;
    private static boolean ready = false;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition isReady = lock.newCondition();
        Runnable producer = () -> {
            try {
                Thread.sleep(100);
                lock.lock();
                try {
                    result = 42;
                    ready = true;
                    isReady.signalAll();
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException ex) {
                // завершение потока
            }
        };
        Runnable reader = () -> {
            try {
                lock.lock();
                try {
                    while (!ready) {
                        isReady.await();
                    }
                    System.out.println(result);
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException ex) {
                // завершение потока
            }
        };
        new Thread(reader).start();
        new Thread(producer).start();
    }
}
