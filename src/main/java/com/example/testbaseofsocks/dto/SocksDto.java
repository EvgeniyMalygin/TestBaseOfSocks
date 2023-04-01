package com.example.testbaseofsocks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class SocksDto {
    @JsonProperty("color")
    String color;
    @JsonProperty("cottonPart")
    Double cottonPart;

    @JsonProperty("quantity ")
    Integer quantity ;

    public SocksDto(String color, Double cottonPart, Integer quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocksDto socksDto = (SocksDto) o;
        return Objects.equals(color, socksDto.color) && Objects.equals(cottonPart, socksDto.cottonPart) && Objects.equals(quantity, socksDto.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, cottonPart, quantity);
    }

    @Override
    public String toString() {
        return "SocksDto{" +
                "color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                '}';
    }
}
