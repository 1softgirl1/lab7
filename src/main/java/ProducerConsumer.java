
public class ProducerConsumer {
    public static void main(String[] args) {
        ShoeWarehouse warehouse = new ShoeWarehouse();
        int n = 10; // Общее количество заказов

        // Создание и запуск потока Producer
        Thread producerThread = new Thread(new Producer(warehouse, n));
        producerThread.start();

        // Создание и запуск n / 5 потоков Consumer
        int numberOfConsumers = n / 5;
        for (int i = 0; i < numberOfConsumers; i++) {
            Thread consumerThread = new Thread(new Consumer(warehouse, 5));
            consumerThread.start();
        }

        // Ожидание завершения потока Producer
        try {
            producerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
