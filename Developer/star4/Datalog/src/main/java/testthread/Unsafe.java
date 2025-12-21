package testthread;

public class Unsafe {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket, "A").start();
        new Thread(buyTicket, "B").start();
        new Thread(buyTicket, "C").start();
    }
}

class BuyTicket implements Runnable {
    private int ticketNum = 10;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void buy() throws InterruptedException {
        if (ticketNum <= 0) {
            flag = false;
            return;
        }

        // 模拟延时
        Thread.sleep(100);

        System.out.println(Thread.currentThread().getName() + " buys ticket " + ticketNum--);
    }

    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket, "A").start();
        new Thread(buyTicket, "B").start();
        new Thread(buyTicket, "C").start();
    }
}