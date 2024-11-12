
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoeWarehouse {
    public static final String[] ITEM_TYPES = {"Sneakers", "Boots", "Sandals", "Loafers", "Heels"};
    private static final int MAX_ORDERS = 10; // Максимальный размер очереди заказов
    private final List<Order> orders = new ArrayList<>();

    public synchronized void receiveOrder(Order order) throws InterruptedException { //ключевое слово synchronized гарантирует, что в данный момент времени только один поток имеет доступ к блоку кода или методу, помеченному как synchronized.
        while (orders.size() >= MAX_ORDERS) {
            wait(); // Ждем, пока не освободится место в списке заказов
        }
        orders.add(order);
        log.info("Received order: " + order);
        notifyAll(); // Уведомляем другие потоки(потребителей) о новом заказе
    }

    // Метод для выполнения заказа потребителем
    public synchronized Order fulfillOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait(); // Ждем, пока не появится новый заказ
        }
        Order order = orders.remove(0);
        log.info("Fulfilled order: " + order);
        notifyAll(); // Уведомляем производителей о том, что место освободилось
        return order;
    }
}
