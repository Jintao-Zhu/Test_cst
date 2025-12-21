package testthread;

public class StaticProxy {

    public static void main(String[] args) {
        new Factory(new You()).marry();

        new Thread(() -> System.out.println("Thread is running.")).start();
    }
}

interface Marry {
    void marry();
}

class You implements Marry {
    @Override
    public void marry() {
        System.out.println("You and your wife are getting married.");
    }
}

class Factory implements Marry {
    private Marry target;

    public Factory(Marry target) {
        this.target = target;
    }

    @Override
    public void marry() {
        before();
        System.out.println("The wedding company is preparing for the wedding.");
        this.target.marry();
        System.out.println("The wedding company is cleaning up after the wedding.");
        after();
    }

    private void before() {
        System.out.println("Before the wedding.");
    }

    private void after() {
        System.out.println("After the wedding.");
    }
}