package zeyracakes.co.tz.Models.Entities.Orders;

import jakarta.persistence.*;
import zeyracakes.co.tz.Models.Entities.Product;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orderItems")
public class OrderItem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "product_id")
    private Product product;
    private Long totalPrice;
    @ManyToMany(mappedBy = "orderItems", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Order> orders = new HashSet<>();

    public OrderItem() {
    }

    public OrderItem(Long quantity, Product product, Long totalPrice) {
        this.quantity = quantity;
        this.product = product;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
