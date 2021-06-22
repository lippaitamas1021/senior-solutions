package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();
    public void saveOrder(Order order){
        if (order.getProducts().size() != 0) {
            orders.add(order);
        }
    }

    public long countOrderByStatus(String status) {
        return orders.stream()
                .filter(order -> order.getStatus().equals(status)).count();
    }

    public List<Order> collectOrdersWithProductCategory(String category) {
        return orders.stream()
                .filter(order -> order.getProducts()
                        .stream()
                        .anyMatch(product -> product.getCategory().equals(category)))
                .collect(Collectors.toList());
    }

    public List<Product> productsOverAmountPrice(int amount) {
        return orders.stream()
                .flatMap(order -> order.getProducts()
                        .stream())
                .filter(product -> product.getPrice() > amount)
                .distinct()
                .collect(Collectors.toList());
    }

    public double sumBetweenDates(LocalDate start, LocalDate end) {
        return orders.stream()
                .filter(order -> !order.getOrderDate().isAfter(end))
                .filter(order -> !order.getOrderDate().isBefore(start))
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(Product::getPrice).sum();
    }

    public Optional<Product> findByName(String name) {
        return orders.stream()
                .flatMap(order -> order.getProducts()
                        .stream())
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }

    public Optional<Order> findOrderWithMaxProduct() {
        return orders.stream()
                .max((o1, o2) -> (int)(o1.getProducts().stream().mapToDouble(Product::getPrice).max().getAsDouble()
                        - o2.getProducts().stream().mapToDouble(Product::getPrice).max().getAsDouble()));
    }
}
