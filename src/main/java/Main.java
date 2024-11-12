
class NewThread extends Thread {
    public void run() {
        try {
            for (int i = 0; i < 10; i += 2)  {
                System.out.println("Поток 1: " + i + " - четное ");
                Thread.sleep(1000);
            }

        } catch (InterruptedException е) {
            System.out.println("Поток 1 прерван.");
        }
        System.out.println("Поток 1 завершен.");
    }
}


class RunnableCode implements Runnable {
    public void run() {
        try {
            for (int i = 1; i < 10; i += 2){
                    System.out.println("Поток 2: " + i + " - нечетное ");
                    Thread.sleep(5000);
            }

        } catch (InterruptedException е) {
            System.out.println("Поток 2 завершен");
        }
        System.out.println("Поток 2 завершен.");
    }
}


public class Main {
    public static void main(String[] args) {
        NewThread t = new NewThread();
        t.start();
        Thread t1 = new Thread(new RunnableCode());
        t1.start();

    }
}
