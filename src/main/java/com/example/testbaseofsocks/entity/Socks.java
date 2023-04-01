package com.example.testbaseofsocks.entity;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Entity
@RequiredArgsConstructor
@Table(name = "socks")
public class Socks {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socks_id", nullable = false)
    Long id;

    @Column(name = "color")
    String color;
    @Column(name = "cottonPart")
    Double cottonPart;

    @Column(name = "quantity")
    Integer quantity;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Double cottonPart) {
        this.cottonPart = cottonPart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return Objects.equals(id, socks.id) && Objects.equals(color, socks.color) && Objects.equals(cottonPart, socks.cottonPart) && Objects.equals(quantity, socks.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, cottonPart, quantity);
    }

    @Override
    public String toString() {
        return "Socks{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
