package lec2;

public class Example2 {

    private static int counter = 0;

    public static void main(String[] args) throws Exception {
        Runnable code = () -> {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        };
        Thread t1 = new Thread(code);
        Thread t2 = new Thread(code);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter);
    }
}
