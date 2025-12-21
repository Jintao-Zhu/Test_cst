package testthread;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    public static void main(String[] args) {
        MakeUp girl1 = new MakeUp(0, "girl1");
        MakeUp girl2 = new MakeUp(1, "girl2");
        girl1.start();
        girl2.start();
    }
}


class Lipstick {

}

class Mirror {

}

class MakeUp extends Thread {

    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;
    String girlName;

    public MakeUp(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {
        makeUp();
    }

    private void makeUp() {
        if (choice == 0) {
            synchronized (lipstick) {
                System.out.println(this.girlName + " gets lipstick.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (mirror) {
                System.out.println(this.girlName + " gets mirror.");
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.girlName + " gets mirror.");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lipstick) {
                System.out.println(this.girlName + " gets lipstick.");
            }
        }
    }
}