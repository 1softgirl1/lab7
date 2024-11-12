public class Consumer implements Runnable {
    private final ShoeWarehouse warehouse;
    private final int ordersToFulfill;

    public Consumer(ShoeWarehouse warehouse, int ordersToFulfill) {
        this.warehouse = warehouse;
        this.ordersToFulfill = ordersToFulfill;
    }

    @Override
    public void run() {
        for (int i = 0; i < ordersToFulfill; i++) {
            try {
                Order order = warehouse.fulfillOrder();
                Thread.sleep(2000); // времени на выполнение заказа
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
