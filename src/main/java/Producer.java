class Producer implements Runnable {
    private final ShoeWarehouse warehouse;
    private int orderCount;

    public Producer(ShoeWarehouse warehouse, int orderCount) {
        this.warehouse = warehouse;
        this.orderCount = orderCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < orderCount; i++) {
            try {
                int orderId = i + 1;
                String shoeType = ShoeWarehouse.ITEM_TYPES[(int) (Math.random() * ShoeWarehouse.ITEM_TYPES.length)];
                int quantity = (int) (Math.random() * 10) + 1; // Заказ от 1 до 10 штук
                Order order = new Order(orderId, shoeType, quantity);
                warehouse.receiveOrder(order);
                Thread.sleep(1000); //  время на создание заказа
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}