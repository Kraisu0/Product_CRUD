package com.kraisu.products.model;

import jakarta.persistence.*;
import jakarta.validation.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(unique = true, nullable = false)
    @Size(min = 2, max = 50, message = "Name Name must be between 2 and 50 characters long!")
    String name;

    @Min(value = 0, message = "Points must be worth at least 1!")
    Integer points;

    @NotBlank(message = "Unit type cannot be empty!")
    String unitType;

    @NotBlank(message = "Membership cannot be empty!")
    String membership;

    @NotBlank(message = "Price cannot be empty!")
    Double price;

    @CreationTimestamp
    Timestamp createdAt;

}
