package zeyracakes.co.tz.Models.Entities.Orders;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zeyracakes.co.tz.Common.Dtos.Address;
import zeyracakes.co.tz.Common.Dtos.OrderContactPerson;
import zeyracakes.co.tz.Models.Entities.Users.Customer;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table ( name = "orders")
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private  Long id;
    private String orderNumber;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(
            name = "order_item", // join table
            joinColumns = @JoinColumn(name = "order_id"), // foreign key to Order
            inverseJoinColumns = @JoinColumn(name = "item_id") // foreign key to Item
    )
    private Set<OrderItem> orderItems = new HashSet<>();
    private Long  totalItemsCount;
    private Long totalAmount;
    @JdbcTypeCode(SqlTypes.JSON)
    private OrderContactPerson contactPerson;
    @JdbcTypeCode(SqlTypes.JSON)
    private Address shippingAddress;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    public Order() {
    }

    public Order(String orderNumber, Long totalItemsCount, Long totalAmount, OrderContactPerson contactPerson, Address shippingAddress) {
        this.orderNumber = orderNumber;
        this.totalItemsCount = totalItemsCount;
        this.totalAmount = totalAmount;
        this.contactPerson = contactPerson;
        this.shippingAddress = shippingAddress;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getTotalItemsCount() {
        return totalItemsCount;
    }

    public void setTotalItemsCount(Long totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(OrderContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addOrderItem(OrderItem orderItem)
    {
        orderItems.add(orderItem);
    }
}
