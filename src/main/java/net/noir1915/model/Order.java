


package net.noir1915.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order", schema = "public")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "completion")
    private LocalDateTime completion;

    @Column(name = "order_title")
    private String orderTitle;

    @Column(name = "is_complected")
    private boolean isComplected;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order")
    private List<Payment> payments;

    @PrePersist
    public void onCreate() {
        created = LocalDateTime.now();
        modified = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        modified = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", created=" + created +
                ", modified=" + modified +
                ", totalCost=" + totalCost +
                ", completion=" + completion +
                ", orderTitle='" + orderTitle + '\'' +
                ", isComplected=" + isComplected +
                ", orderItems=" + orderItems +
                ", payments=" + payments +
                '}';
    }
}