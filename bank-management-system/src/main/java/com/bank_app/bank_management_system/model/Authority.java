package com.bank_app.bank_management_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Entity
@Getter
@Setter
@Table(name="authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
