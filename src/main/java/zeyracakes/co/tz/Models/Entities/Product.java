package zeyracakes.co.tz.Models.Entities;

import jakarta.persistence.*;
import zeyracakes.co.tz.Models.Entities.Orders.OrderItem;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private Long price;
    @Column(unique = true)
    private String imagePath;
    @OneToOne(mappedBy = "product", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private OrderItem orderItem;

    public Product() {
    }

    public Product(String name, String description, Long price, String imagePath) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
    }

    public void updateMetaData(String name, String description, Long price, String imagePath)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
}
