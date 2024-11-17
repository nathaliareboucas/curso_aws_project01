package br.com.reboucas.nathalia.aws_project01.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false, unique = true)
    private String invoiceNumber;

    @Column(length = 32, nullable = false)
    private String customerName;

    private BigDecimal totalValue;

    private Long productId;

    private Integer quantity;
}
