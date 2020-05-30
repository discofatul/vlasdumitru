package net.javaguides.springboot.springsecurity.web.dto;

import net.javaguides.springboot.springsecurity.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDto {

    private String name;
    private String brand;
    private String madein;
    private float price;

    public static List<ProductDto> toDtos(List<Product> products) {
        return products.stream().map(ProductDto::toDto).collect(Collectors.toList());
    }

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getName(),
                product.getBrand(),
                product.getMadein(),
                product.getPrice()
        );
    }

    public ProductDto(String name, String brand, String madein, float price) {
        this.name = name;
        this.brand = brand;
        this.madein = madein;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadein() {
        return madein;
    }

    public void setMadein(String madein) {
        this.madein = madein;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
