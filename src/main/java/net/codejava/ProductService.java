package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public Product get(String code) {
		return repo.findById(code).get();
	}
	
	/*public void delete(long id) {
		repo.deleteById(id);
	}*/
	public void delete(String code) {
		repo.deleteById(code);
	}
	
	public Product find(String code) {
		return repo.findByCodeorName(code);
	}
	
	public Product findCode(String code) {
		return repo.findByCode(code);
	}
}
