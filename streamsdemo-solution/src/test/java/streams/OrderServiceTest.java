package streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    OrderService ordersService = new OrderService() ;
    @BeforeEach
    public void init(){
        Product p1 = new Product("Tv","IT",2000);
        Product p2 = new Product("Laptop","IT",2400);
        Product p3 = new Product("Phone","IT",400);
        Product p4 = new Product("Lord of The Rings","Book",20);
        Product p5 = new Product("Harry Potter Collection","Book",120);
        Order o1 = new Order("pending", LocalDate.of(2021,06,07));
        o1.addProduct(p1);
        o1.addProduct(p2);
        o1.addProduct(p5);
        Order o2 = new Order("on delivery", LocalDate.of(2021,06,01));
        o2.addProduct(p3);
        o2.addProduct(p1);
        o2.addProduct(p2);
        ordersService.saveOrder(o1);
        ordersService.saveOrder(o2);
    }
    @Test
    void countOrderByStatusTest() {
        long result = ordersService.countOrderByStatus("pending");
        assertEquals(1, result);
    }
    @Test
    void countOrderByStatusZeroTest() {
        long result = ordersService.countOrderByStatus("pendingd");
        assertEquals(0, result);
    }
    @Test
    void collectOrdersWithProductCategoryTest() {
        List<Order> result = ordersService.collectOrdersWithProductCategory("Book");
        assertEquals(1, result.size());
    }
    @Test
    void productsOverAmountPrice() {
        List<Product> result = ordersService.productsOverAmountPrice(1999);
        assertEquals(2, result.size());
    }
}
