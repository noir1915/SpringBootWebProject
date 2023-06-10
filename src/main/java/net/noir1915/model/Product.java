package net.noir1915.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_title")
    @NotBlank
    private String productTitle;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", columnDefinition = "enum")
    private ProductType productType;

    @Column(name = "written_program")
    private boolean isProgramWritten;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "preparation", columnDefinition = "enum")
    private Preparation preparation;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "technologist_id")
    private Technologist technologist;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productTitle='" + productTitle + '\'' +
                ", productType=" + productType +
                ", isProgramWritten=" + isProgramWritten +
                ", material=" + material.getType() +
                ", endDate=" + endDate +
                ", preparation=" + preparation +
                ", orderItems=" + orderItems +
                ", technologist=" + technologist +
                '}';
    }
}
