package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.springsecurity.model.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getByUser_Email(String email);
}
