package lec2;

/**
 * Пример параллельной работы потоков
 */
public class Example1 {

    public static void main(String[] args) {
        Runnable code = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.printf("Hello %s from %s\n", i, Thread.currentThread().getName());
            }
        };
        new Thread(code).start(); // выполнение в отдельном потоке
        code.run(); // выполнение в главном потоке
    }
}
