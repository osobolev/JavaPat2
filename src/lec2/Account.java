package lec2;

public class Account {

    private int amount = 100;
    private final Object lock = new Object();

    public boolean withdraw(int sum) {
        synchronized (lock) {
            if (amount >= sum) {
                amount -= sum;
                return true;
            } else {
                return false;
            }
        }
    }

    public void deposit(int sum) {
        synchronized (lock) {
            amount += sum;
        }
    }

    public static void transfer(Account from, Account to, int sum) {
        synchronized (from.lock) {
            sleep(100);
            synchronized (to.lock) {
                if (from.withdraw(sum)) {
                    to.deposit(sum);
                }
            }
        }
    }

    public static void main(String[] args) {
        Account a1 = new Account();
        Account a2 = new Account();
        new Thread(() -> transfer(a1, a2, 10)).start();
        new Thread(() -> transfer(a2, a1, 10)).start();
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            // игнорируем для простоты
        }
    }
}
