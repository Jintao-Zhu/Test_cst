package testthread;

public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        Your your = new Your();

        Thread thread = new Thread(god);
        thread.setDaemon(true);  // 默认是false，表示是用户线程，正常的线程都是用户线程...
        thread.start(); // 上帝守护线程启动，只要用户线程结束，上帝守护线程就结束

        new Thread(your).start(); // 你的线程启动
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("God bless you.");
        }
    }
}

class Your implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("Live happily.");
        }
        System.out.println("Goodbye, world.");
    }
}
