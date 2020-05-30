package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import net.javaguides.springboot.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springboot.springsecurity.model.Product;
import net.javaguides.springboot.springsecurity.repository.ProductRepository;


@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository repo;
	
	public List<Product> listAll() {
		return repo.findAll();
	}
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public Product get(long id) {
		return repo.findById(id).orElse(null);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}

	public List<Product> getByUserEmail(String email) {
		return repo.getByUser_Email(email);
	}
}
